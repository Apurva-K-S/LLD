package com.example.StackOverflow_advanced.Service;

import com.example.StackOverflow_advanced.Entity.Answer;
import com.example.StackOverflow_advanced.Entity.Comment;
import com.example.StackOverflow_advanced.Entity.Question;
import com.example.StackOverflow_advanced.Entity.User;

import java.util.Set;

public class VoteService {
    QuestionService questionService;
    AnswerService answerService;
    UserService userService;
    CommentService commentService;
    public VoteService(QuestionService questionService1, AnswerService answerService1, UserService userService1, CommentService commentService1){
        questionService = questionService1;
        answerService = answerService1;
        userService = userService1;
        commentService = commentService1;
    }

    public void upvoteQuestion(String questionId, String upvotedBy){
        Question question = questionService.getQuestionById(questionId);
        if(question==null)
        {
            System.out.println("no such question exists");
            return;
        }
        User user = userService.getByEmail(upvotedBy);
        if(user==null)
        {
            System.out.println("user doesnt exist");
            return;
        }
        if(question.getUpvotedBy().contains(user))
        {
            System.out.println("already upvoted");
            return;
        }
        if(question.getDownvotedBy().contains(user))
        {
            System.out.println("converting downvote to upvote");
            question.setDownvotes(question.getDownvotes()-1);
            question.getDownvotedBy().remove(user);

            Set<User> currentUpvotes = question.getUpvotedBy();
            currentUpvotes.add(user);
            question.setUpvotedBy(currentUpvotes);
            question.setUpvotes(question.getUpvotes()+1);
        }
        else {
            Set<User> currentUpvotes = question.getUpvotedBy();
            currentUpvotes.add(user);
            question.setUpvotedBy(currentUpvotes);
            question.setUpvotes(question.getUpvotes()+1);
        }
    }

    public void downvoteQuestion(String questionId, String upvotedBy){
        Question question = questionService.getQuestionById(questionId);
        if(question==null)
        {
            System.out.println("no such question exists");
            return;
        }
        User user = userService.getByEmail(upvotedBy);
        if(user==null)
        {
            System.out.println("user doesnt exist");
            return;
        }
        if(question.getDownvotedBy().contains(user))
        {
            System.out.println("already downvoted");
            return;
        }
        if(question.getUpvotedBy().contains(user))
        {
            System.out.println("converting upvote to down");
            question.setUpvotes(question.getUpvotes()-1);
            question.getUpvotedBy().remove(user);

            Set<User> currentDownvotes = question.getDownvotedBy();
            currentDownvotes.add(user);
            question.setDownvotedBy(currentDownvotes);
            question.setDownvotes(question.getDownvotes()+1);
        }
        else {
            Set<User> currentDownvotes = question.getDownvotedBy();
            currentDownvotes.add(user);
            question.setDownvotedBy(currentDownvotes);
            question.setDownvotes(question.getDownvotes()+1);
        }
    }

    public void upvoteAnswer(String answerId, String upvotedBy){
        Answer answer = answerService.getAnswerById(answerId);
        if(answer==null)
        {
            System.out.println("no such answer exists");
            return;
        }
        User user = userService.getByEmail(upvotedBy);
        if(user==null)
        {
            System.out.println("user doesnt exist");
            return;
        }
        if(answer.getUpvotedBy().contains(user))
        {
            System.out.println("already upvoted");
            return;
        }
        if(answer.getDownvotedBy().contains(user))
        {
            System.out.println("converting downvote to upvote");
            answer.setDownvotes(answer.getDownvotes()-1);
            answer.getDownvotedBy().remove(user);

            Set<User> currentUpvotes = answer.getUpvotedBy();
            currentUpvotes.add(user);
            answer.setUpvotedBy(currentUpvotes);
            answer.setUpvotes(answer.getUpvotes()+1);
        }
        else {
            Set<User> currentUpvotes = answer.getUpvotedBy();
            currentUpvotes.add(user);
            answer.setUpvotedBy(currentUpvotes);
            answer.setUpvotes(answer.getUpvotes()+1);
        }
    }

    public void downvoteAnswer(String answerId, String upvotedBy){
        Answer answer= answerService.getAnswerById(answerId);
        if(answer==null)
        {
            System.out.println("no such answer exists");
            return;
        }
        User user = userService.getByEmail(upvotedBy);
        if(user==null)
        {
            System.out.println("user doesnt exist");
            return;
        }
        if(answer.getDownvotedBy().contains(user))
        {
            System.out.println("already upvoted");
            return;
        }
        if(answer.getUpvotedBy().contains(user))
        {
            System.out.println("converting upvote to down");
            answer.setUpvotes(answer.getUpvotes()-1);
            answer.getUpvotedBy().remove(user);

            Set<User> currentDownvotes = answer.getDownvotedBy();
            currentDownvotes.add(user);
            answer.setDownvotedBy(currentDownvotes);
            answer.setDownvotes(answer.getDownvotes()+1);
        }
        else {
            Set<User> currentDownvotes = answer.getDownvotedBy();
            currentDownvotes.add(user);
            answer.setDownvotedBy(currentDownvotes);
            answer.setDownvotes(answer.getDownvotes()+1);
        }
    }

    public void upvoteComment(String commentId, String upvotedBy){
        Comment comment = commentService.getCommentById(commentId);
        if(comment==null)
        {
            System.out.println("no such comment exists");
            return;
        }
        User user = userService.getByEmail(upvotedBy);
        if(user==null)
        {
            System.out.println("user doesnt exist");
            return;
        }
        if(comment.getUpvotedBy().contains(user))
        {
            System.out.println("already upvoted");
            return;
        }
        if(comment.getDownvotedBy().contains(user))
        {
            System.out.println("converting downvote to upvote");
            comment.setDownvotes(comment.getDownvotes()-1);
            comment.getDownvotedBy().remove(user);

            Set<User> currentUpvotes = comment.getUpvotedBy();
            currentUpvotes.add(user);
            comment.setUpvotedBy(currentUpvotes);
            comment.setUpvotes(comment.getUpvotes()+1);
        }
        else {
            Set<User> currentUpvotes = comment.getUpvotedBy();
            currentUpvotes.add(user);
            comment.setUpvotedBy(currentUpvotes);
            comment.setUpvotes(comment.getUpvotes()+1);
        }
    }

    public void downvoteComment(String commentId, String upvotedBy){
        Comment comment = commentService.getCommentById(commentId);
        if(comment==null)
        {
            System.out.println("no such comment exists");
            return;
        }
        User user = userService.getByEmail(upvotedBy);
        if(user==null)
        {
            System.out.println("user doesnt exist");
            return;
        }
        if(comment.getDownvotedBy().contains(user))
        {
            System.out.println("already downvoted");
            return;
        }
        if(comment.getUpvotedBy().contains(user))
        {
            System.out.println("converting upvote to down");
            comment.setUpvotes(comment.getUpvotes()-1);
            comment.getUpvotedBy().remove(user);

            Set<User> currentDownvotes = comment.getDownvotedBy();
            currentDownvotes.add(user);
            comment.setDownvotedBy(currentDownvotes);
            comment.setDownvotes(comment.getDownvotes()+1);
        }
        else {
            Set<User> currentDownvotes = comment.getDownvotedBy();
            currentDownvotes.add(user);
            comment.setDownvotedBy(currentDownvotes);
            comment.setDownvotes(comment.getDownvotes()+1);
        }
    }

}
