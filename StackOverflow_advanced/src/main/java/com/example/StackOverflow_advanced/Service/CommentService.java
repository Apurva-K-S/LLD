package com.example.StackOverflow_advanced.Service;

import com.example.StackOverflow_advanced.Entity.Answer;
import com.example.StackOverflow_advanced.Entity.Comment;
import com.example.StackOverflow_advanced.Entity.Question;
import com.example.StackOverflow_advanced.Entity.User;
import com.example.StackOverflow_advanced.Enums.CommentFor;

import java.time.LocalTime;
import java.util.*;

public class CommentService {
    Map<String, Comment> comments;
    UserService userService;
    public CommentService(UserService userService1) {
        comments = new HashMap<>();
        userService = userService1;
    }

    public void addComment(String text, String postedBy, CommentFor commentFor, String parentId)
    {
        User user = userService.getByEmail(postedBy);
        if(user==null)
        {
            System.out.println("User cant post a question");
            return;
        }
        UUID uuid = UUID.randomUUID();
        LocalTime currentTime = LocalTime.now();

        comments.put(uuid.toString(), new Comment(uuid.toString(),
                text,
                commentFor,
                parentId,
                currentTime,
                user,
                new HashSet<>(),
                new HashSet<>(),
                0,
                0
        ));
    }

    public Comment getCommentById(String id){
        if(comments.containsKey(id))
            return comments.get(id);
        else {
            System.out.println("question doesnt exist with this id");
            return null;
        }
    }

    public List<Comment> getCommentsByParentId(CommentFor commentFor, String parentId){
        List<Comment> result = new ArrayList<>();
        for(Comment cmt: comments.values())
        {
            if(cmt.getCommentFor().equals(commentFor) && cmt.getParentId().equals(parentId))
                result.add(cmt);
        }
        return result;
    }

    public void seeAllComments() {
        List<Comment> result = new ArrayList<>(comments.values());
        for(Comment cmt: result){
            System.out.println(cmt);
        }
    }
}
