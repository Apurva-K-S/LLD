package com.example.StackOverflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.Stack;
import java.util.UUID;

@SpringBootApplication
public class StackOverflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(StackOverflowApplication.class, args);
		startApplication();
	}

	public static void startApplication(){
		StackOverflowService stackOverflowService = new StackOverflowService();
		System.out.println("Welcome to stackoverflow");
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter userId: ");
		String userIdString = sc.next();
		UUID userId = UUIDUtils.safeParseUUID(userIdString);
		Person person = stackOverflowService.searchPerson(userId);
		if(person == null)
		{
			System.out.println("User doesnt exist. Please create user.");
			System.out.println("Enter UserName: ");
			String name = sc.next();
			System.out.println("Enter phoneNumber: ");
			String phoneNumber = sc.next();
			person = stackOverflowService.createPerson(name, phoneNumber);
		}

		while(true)
		{
			System.out.println("1. Search | " +
					"2. Add Question | " +
					"3. Post Answer | " +
					"4. Upvote Question | " +
					"5. Downvote Question | " +
					"6. Upvote Answer | "+
					"7. Upvote Answer | "+
					"8. Add comment | "+
					"9. exit");

			int option = sc.nextInt();
			String questionIdString, answerIdString;
			UUID questionId, answerId;

			if(option == 9) {
				System.out.println("exiting the program");
				break;
			}

			switch(option){
				case 1:
					System.out.println("Enter query to search: ");
					sc.nextLine();
					String searchQuery = sc.nextLine();
					stackOverflowService.searchForQuestion(searchQuery);
					break;
				case 2:
					if(person == null)
					{
						System.out.println("please create account first");
						break;
					}
					System.out.println("Enter your question: ");
					sc.nextLine();
					String question = sc.nextLine();
					stackOverflowService.createQuestion(question, person.getId());
					break;
				case 3:
					if(person == null)
					{
						System.out.println("please create account first");
						break;
					}
					System.out.println("To which question you want to post answer: ");
					questionIdString = sc.next();
					questionId = UUIDUtils.safeParseUUID(questionIdString);
					System.out.println("post answer: ");
					sc.nextLine();
					String answer = sc.nextLine();
					stackOverflowService.addAnswerToQuestion(answer, questionId, person.getId());
					break;
				case 4:
					if(person == null)
					{
						System.out.println("please create account first");
						break;
					}
					System.out.println("Which question you want to upvote ");
					questionIdString = sc.next();
					questionId = UUIDUtils.safeParseUUID(questionIdString);
					stackOverflowService.upvoteQuestion(questionId, person.getId());
					break;
				case 5:
					if(person == null)
					{
						System.out.println("please create account first");
						break;
					}
					System.out.println("Which question you want to downvote ");
					questionIdString = sc.next();
					questionId = UUIDUtils.safeParseUUID(questionIdString);
					stackOverflowService.downvoteQuestion(questionId, person.getId());
					break;
				case 6:
					if(person == null)
					{
						System.out.println("please create account first");
						break;
					}
					System.out.println("Which question's answer you want to upvote ");
					questionIdString = sc.next();
					questionId = UUIDUtils.safeParseUUID(questionIdString);
					System.out.println("Which answer you want to upvote: ");
					answerIdString = sc.next();
					answerId = UUIDUtils.safeParseUUID(answerIdString);
					stackOverflowService.upvoteAnswer(questionId,answerId, person.getId());
					break;
				case 7:
					if(person == null)
					{
						System.out.println("please create account first");
						break;
					}
					System.out.println("Which question's answer you want to downvote ");
					questionIdString = sc.next();
					questionId = UUIDUtils.safeParseUUID(questionIdString);
					System.out.println("Which answer you want to upvote: ");
					answerIdString = sc.next();
					answerId = UUIDUtils.safeParseUUID(answerIdString);
					stackOverflowService.downvoteAnswer(questionId, answerId, person.getId());
					break;
				case 8:
					if(person == null)
					{
						System.out.println("please create account first");
						break;
					}
					System.out.println("Which question you want to add comment to ");
					questionIdString = sc.next();
					questionId = UUIDUtils.safeParseUUID(questionIdString);
					System.out.println("Which answer you want to add comment to ");
					answerIdString = sc.next();
					answerId = UUIDUtils.safeParseUUID(answerIdString);
					System.out.println("add comment to post: ");
					sc.nextLine();
					String commentText = sc.nextLine();
					stackOverflowService.addCommentToAnswer(commentText, person.getId(), answerId, questionId);
					break;
			}
		}
	}

}
