package com.example.StackOverflow;

import java.time.Instant;
import java.util.*;

public class StackOverflowService {
    Map<UUID, Person> persons;
    Map<String, Set<UUID>> searchMap;
    Map<UUID, Question> questions;

    public StackOverflowService() {
        persons = new HashMap<>();
        searchMap = new HashMap<>();
        questions = new HashMap<>();
    }

    Person searchPerson(UUID personId)
    {
        if(personId == null || !persons.containsKey(personId))
            return null;
        return persons.get(personId);
    }

    Person createPerson(String name, String phonenumber)
    {
        UUID personId = UUID.randomUUID();
        Person person = new Person(name, personId, phonenumber);
        persons.put(personId, person);
        System.out.println("You are added successfully");
        System.out.println("Your userId: "+personId);
        return person;
    }

    Question createQuestion(String questionText, UUID personId)
    {
        UUID id = UUID.randomUUID();
        Person person=null;
        if(personId!=null && persons.containsKey(personId))
        {
            person = persons.get(personId);
        }
        if(person==null)
        {
            System.out.println("person doesnt exist");
            return null;
        }
        Set<UUID> upvotes = new HashSet<>();
        Set<UUID> downvotes = new HashSet<>();
        List<Answer> answers = new ArrayList<>();
        Question question = new Question(id,questionText, person, new Date(),upvotes, downvotes, answers);

        String tokens[] = questionText.split(" ");
        for(int i=0;i<tokens.length;i++)
        {
            if(searchMap.containsKey(tokens[i]))
            {
                searchMap.get(tokens[i]).add(question.getId());
            }
            else
            {
                Set<UUID> questionIds = new HashSet<>();
                questionIds.add(question.getId());
                searchMap.put(tokens[i], questionIds);
            }
        }

        questions.put(question.id, question);
        printQuestion(question);
        return question;
    }

    Answer addAnswerToQuestion(String answerText, UUID questionId, UUID personId) {
        Answer answer = null;
        if(personId==null || !persons.containsKey(personId)) {
            System.out.println("Person doesnt exist");
            return answer;
        }
        if(questionId== null || !questions.containsKey(questionId)) {
            System.out.println("question doesnt exist");
            return answer;
        }
        UUID answerId = UUID.randomUUID();
        List<Comment> comments = new ArrayList<>();
        Set<UUID> upvotes = new HashSet<>();
        Set<UUID> downvotes = new HashSet<>();
        answer = new Answer(answerId, questions.get(questionId), answerText, persons.get(personId), new Date(), comments,upvotes,downvotes);
        Question question = questions.get(questionId);
        question.answers.add(answer);
        printQuestion(question);
        return answer;
    }

    int upvoteQuestion(UUID questionId, UUID personId)
    {
        if(questionId==null || !questions.containsKey(questionId)) {
            System.out.println("question doesnt exist");
            return 0;
        }
        Question question = questions.get(questionId);
        if(personId==null || !persons.containsKey(personId)) {
            System.out.println("Person doesnt exist");
            return question.getUpVotedUserIds().size();
        }

        if(question.upVotedUserIds.contains(personId))
        {
            System.out.println("You have already upvoted");
            return question.getUpVotedUserIds().size();
        }
        question.upVotedUserIds.add(personId);
        if(question.downVotedUserIds.contains(personId))
        {
            System.out.println("you have previously downvoted, removing your downvote");
            question.downVotedUserIds.remove(personId);
            return question.getUpVotedUserIds().size();
        }
        printQuestion(question);
        return question.getUpVotedUserIds().size();
    }

    int downvoteQuestion(UUID questionId, UUID personId)
    {
        if(questionId == null || !questions.containsKey(questionId)) {
            System.out.println("question doesnt exist");
            return 0;
        }

        Question question = questions.get(questionId);
        if(personId==null || !persons.containsKey(personId)) {
            System.out.println("Person doesnt exist");
            return question.getDownVotedUserIds().size();
        }

        if(question.downVotedUserIds.contains(personId))
        {
            System.out.println("You have already downvoted");
            return question.getDownVotedUserIds().size();
        }

        question.downVotedUserIds.add(personId);
        if(question.upVotedUserIds.contains(personId))
        {
            System.out.println("you have previously upvoted, removing your vote");
            question.upVotedUserIds.remove(personId);
            return question.getDownVotedUserIds().size();
        }
        printQuestion(question);
        return question.getDownVotedUserIds().size();
    }

    int upvoteAnswer(UUID questionId, UUID answerId, UUID personId)
    {
        if(questionId==null || !questions.containsKey(questionId)) {
            System.out.println("question doesnt exist");
            return 0;
        }
        Question question = questions.get(questionId);
        if(personId==null || !persons.containsKey(personId)) {
            System.out.println("Person doesnt exist");
            return question.getUpVotedUserIds().size();
        }

        List<Answer> answers = question.getAnswers();
        for(Answer ans: answers)
        {
            if(ans.getId().equals(answerId))
            {
                if(ans.upVotedUserIds.contains(personId))
                {
                    System.out.println("You have already upvoted");
                    return ans.getUpVotedUserIds().size();
                }
                ans.upVotedUserIds.add(personId);
                if(ans.downVotedUserIds.contains(personId))
                {
                    System.out.println("you have previously downvoted, removing your downvote");
                    ans.downVotedUserIds.remove(personId);
                    return ans.getUpVotedUserIds().size();
                }
            }
        }

        printQuestion(question);
        return question.getUpVotedUserIds().size();
    }

    int downvoteAnswer(UUID questionId, UUID answerId, UUID personId)
    {
        if(questionId == null || !questions.containsKey(questionId)) {
            System.out.println("question doesnt exist");
            return 0;
        }

        Question question = questions.get(questionId);
        if(personId==null || !persons.containsKey(personId)) {
            System.out.println("Person doesnt exist");
            return question.getDownVotedUserIds().size();
        }

        List<Answer> answers = question.getAnswers();
        for(Answer ans: answers)
        {
            if(ans.getId().equals(answerId))
            {
                if(ans.downVotedUserIds.contains(personId))
                {
                    System.out.println("You have already downvoted");
                    return ans.getDownVotedUserIds().size();
                }
                ans.downVotedUserIds.add(personId);
                if(ans.upVotedUserIds.contains(personId))
                {
                    System.out.println("you have previously upvoted, removing your upvote");
                    ans.upVotedUserIds.remove(personId);
                    return ans.getUpVotedUserIds().size();
                }
            }
        }

        printQuestion(question);
        return question.getDownVotedUserIds().size();
    }

    Comment addCommentToAnswer(String commentText, UUID personId, UUID answerId, UUID questionId)
    {
        if(questionId == null || !questions.containsKey(questionId))
        {
            System.out.println("question doesnt exist");
            return null;
        }
        Question question = questions.get(questionId);
        if(personId==null || !persons.containsKey(personId))
        {
            System.out.println("person doesnt exist");
            return null;
        }

        List<Answer> answers = question.getAnswers();
        for(int i=0;i<answers.size();i++)
        {
            if(answers.get(i).getId().equals(answerId))
            {
                Answer answer = answers.get(i);
                UUID commentId = UUID.randomUUID();
                Comment comment = new Comment(commentId, commentText, answer, persons.get(personId), new Date());
                answer.comments.add(comment);
                return comment;
            }
        }
        printQuestion(question);
        return null;
    }

    void searchForQuestion(String text)
    {
        Set<Question> totalQuestions = new HashSet<>();
        String queries[]=text.split(" ");
        for(int i=0;i<queries.length;i++)
        {
            if(searchMap.containsKey(queries[i]))
            {
                Set<UUID> questionIDs = searchMap.get(queries[i]);
                for(UUID qId: questionIDs)
                {
                    if(questions.containsKey(qId))
                    {
                        totalQuestions.add(questions.get(qId));
                    }
                }
            }
        }
        if(totalQuestions.size()==0)
        {
            System.out.println("No questions with this text");
            return;
        }
        System.out.println("Searched Questions are ");
        for(Question question: totalQuestions)
        {
            printQuestion(question);
        }
        return;
    }

    void printQuestion(Question question)
    {
        System.out.println("------------------------------------------------------------");
        System.out.println("Q) " + question.getText());
        System.out.println("question id: "+ question.getId());
        System.out.println("upvotes "+ question.getUpVotedUserIds().size() + " , downvotes " + question.getDownVotedUserIds().size());
        System.out.println("question was posted by " + question.getPostedBy().getName() + ", on " + question.getPostedAt());
        List<Answer> answers = question.getAnswers();
        for(int i=0;i<answers.size();i++)
        {
            Answer answer = answers.get(i);
            System.out.println("A) "+ answer.getText());
            System.out.println("answer id: "+ answer.getId());
            System.out.println("answered by "+ answer.getPostedBy().getName() + ", answered on " + answer.getPostedAt());
            List<Comment> comments = answers.get(i).getComments();
            for(int j=0;j<comments.size();j++)
            {
                Comment comment = comments.get(j);
                System.out.println("C) "+ comment.getText());
                System.out.println("comment id: "+ comment.getId());
                System.out.println("commented by "+ comment.getPostedBy().getName() + ", commented on "+ comment.getPostedAt());
            }
        }
        System.out.println("---------------------------------------------------------------");

    }
}
/*
UV
bronze
normal tank
14000
 */