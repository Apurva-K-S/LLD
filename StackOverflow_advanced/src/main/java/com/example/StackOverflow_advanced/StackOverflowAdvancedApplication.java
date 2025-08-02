package com.example.StackOverflow_advanced;

import com.example.StackOverflow_advanced.Entity.Answer;
import com.example.StackOverflow_advanced.Entity.Question;
import com.example.StackOverflow_advanced.Entity.User;
import com.example.StackOverflow_advanced.Enums.CommentFor;
import com.example.StackOverflow_advanced.Service.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class StackOverflowAdvancedApplication {

	static UserService userService = new UserService();
 	static QuestionService questionService = new QuestionService(userService);
	static AnswerService answerService = new AnswerService(userService);
	static SearchService searchService = new SearchService(questionService, answerService);
	static CommentService commentService = new CommentService(userService);
	static VoteService voteService = new VoteService(questionService, answerService, userService, commentService);


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String email="", password="", searchTerm="";
		int option;
		boolean loginSuccessful = false;
		while(!loginSuccessful) {
			System.out.println("Select: 1. Register User | " +
					"2. Login User |" +
					"3. Search |" +
					"4. exit");
			option = sc.nextInt();
			sc.nextLine();

			switch (option) {
				case 1:
					System.out.println("register");
					System.out.println("enter user email: ");
					email = sc.next();
					System.out.println("enter password: ");
					password = sc.next();
					userService.registerUser(email, password);
					break;
				case 2:
					System.out.println("login");
					System.out.println("enter user email: ");
					email = sc.next();
					System.out.println("enter password: ");
					password = sc.next();
					loginSuccessful = userService.login(email, password);
					break;
				case 3:
					System.out.println("text to search: ");
					searchTerm = sc.next();
					searchService.searchResults(searchTerm);
					break;
				default:
					break;
			}
		}

		System.out.println("Welcome");
		User user = userService.getByEmail(email);
		System.out.println(user);

		while(true)
		{
			System.out.println("Select: " +
					"1. Add question |" +
					"2. Add answer for question |" +
					"3. Add comment for question or answer |" +
					"4. upvote question |" +
					"5. downvote question |" +
					"6. upvote answer |" +
					"7. downvote answer |" +
					"8. upvote comment |" +
					"9. downvote comment |" +
					"10. Search |" +
					"11. see all questions |" +
					"12. See all Answers |" +
					"13. see all comments | " +
					"14. Exit");
			option = sc.nextInt();
			sc.nextLine();

			switch (option) {
				case 1:
					createQuestion(sc, email);
					break;
				case 2:
					createAnswer(sc, email);
					break;
				case 3:
					createComment(sc, email);
					break;
				case 4:
					upvoteQuestion(sc, email);
					break;
				case 5:
					downvoteQuestion(sc, email);
					break;
				case 6:
					upvoteAnswer(sc, email);
					break;
				case 7:
					downvoteAnswer(sc, email);
					break;
				case 8:
					upvoteComment(sc, email);
					break;
				case 9:
					downvoteComment(sc, email);
					break;
				case 10:
					System.out.println("text to search: ");
					searchTerm = sc.next();
					searchService.searchResults(searchTerm);
					break;
				case 11:
					questionService.seeAllQuestions();
					break;
				case 12:
					answerService.seeAllAnswers();
					break;
				case 13:
					commentService.seeAllComments();
					break;
				default:
					break;
			}
		}
	}

	public static void createQuestion(Scanner sc, String email) {
		System.out.println("Enter Title: ");
		String title = sc.nextLine(); // Change from sc.next()
		System.out.println("Enter text: ");
		String text = sc.nextLine(); // Change from sc.next()
		Question question = questionService.addQuestion(title, text, email);
		if (question != null) {
			searchService.addToSearchBase(question.getText(), question.getId());
			searchService.addToSearchBase(question.getTitle(), question.getId());
		}
	}

	public static void createAnswer(Scanner sc, String email) {
		System.out.println("Questions:\n");
		questionService.seeAllQuestions();

		System.out.println("Enter questionId for which you want to answer: ");
		String questionId = sc.nextLine();

		System.out.println("Enter answer title: ");
		String title = sc.nextLine();

		System.out.println("Enter answer text: ");
		String text = sc.nextLine();
		Answer answer = answerService.addAnswer(title, text, email, questionId);
		if (answer != null) {
			searchService.addToSearchBase(answer.getText(), answer.getId());
			searchService.addToSearchBase(answer.getTitle(), answer.getId());
		}
	}

	public static void createComment(Scanner sc, String email){
		System.out.println("Questions: \n");
		questionService.seeAllQuestions();
		System.out.println("Answers: \n");
		answerService.seeAllAnswers();

		System.out.println("For which you want to add comment? Question or Answer ");
		String commentFor = sc.nextLine();

		System.out.println("Enter questionId/answerId : ");
		String parentId = sc.nextLine();

		System.out.println("Enter comment text: ");
		String text = sc.nextLine();

		CommentFor parent = CommentFor.valueOf(Strings.toRootUpperCase(commentFor));
		commentService.addComment(text, email, parent, parentId);
	}

	public static void upvoteQuestion(Scanner sc, String email){
		questionService.seeAllQuestions();
		System.out.println("Enter questionId to upvote : ");
		String questionId = sc.next();
		voteService.upvoteQuestion(questionId, email);
	}

	public static void downvoteQuestion(Scanner sc, String email){
		questionService.seeAllQuestions();
		System.out.println("Enter questionId to downvote : ");
		String questionId = sc.next();
		voteService.downvoteQuestion(questionId, email);
	}

	public static void upvoteAnswer(Scanner sc, String email){
		answerService.seeAllAnswers();
		System.out.println("Enter answerId to upvote : ");
		String answerId = sc.next();
		voteService.upvoteAnswer(answerId, email);
	}

	public static void downvoteAnswer(Scanner sc, String email){
		answerService.seeAllAnswers();
		System.out.println("Enter answerId to downvote : ");
		String answerId = sc.next();
		voteService.downvoteAnswer(answerId, email);
	}

	public static void upvoteComment(Scanner sc, String email){
		commentService.seeAllComments();
		System.out.println("Enter commentId to upvote : ");
		String commentId = sc.next();
		voteService.upvoteComment(commentId, email);
	}

	public static void downvoteComment(Scanner sc, String email){
		commentService.seeAllComments();
		System.out.println("Enter commentId to downvote : ");
		String commentId = sc.next();
		voteService.downvoteComment(commentId, email);
	}

}
