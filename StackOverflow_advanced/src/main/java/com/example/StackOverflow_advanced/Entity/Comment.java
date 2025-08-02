package com.example.StackOverflow_advanced.Entity;

import com.example.StackOverflow_advanced.Enums.CommentFor;

import java.util.Set;
import java.time.LocalTime;

public class Comment {
    String id;
    String text;
    CommentFor commentFor;
    String parentId;
    LocalTime postedAt;
    User postedBy;
    Set<User> upvotedBy;
    Set<User> downvotedBy;
    int upvotes;
    int downvotes;

    public Comment(String id, String text, CommentFor commentFor, String parentId, LocalTime postedAt, User postedBy, Set<User> upvotedBy, Set<User> downvotedBy, int upvotes, int downvotes) {
        this.id = id;
        this.text = text;
        this.commentFor = commentFor;
        this.parentId = parentId;
        this.postedAt = postedAt;
        this.postedBy = postedBy;
        this.upvotedBy = upvotedBy;
        this.downvotedBy = downvotedBy;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CommentFor getCommentFor() {
        return commentFor;
    }

    public void setCommentFor(CommentFor commentFor) {
        this.commentFor = commentFor;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public LocalTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalTime postedAt) {
        this.postedAt = postedAt;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public Set<User> getUpvotedBy() {
        return upvotedBy;
    }

    public void setUpvotedBy(Set<User> upvotedBy) {
        this.upvotedBy = upvotedBy;
    }

    public Set<User> getDownvotedBy() {
        return downvotedBy;
    }

    public void setDownvotedBy(Set<User> downvotedBy) {
        this.downvotedBy = downvotedBy;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public String toString(){
        return "id = " + id+"\n"+"title = "+
                "text = "+text + "\n"+"upvotes = "+upvotes+"\n"+
                "downvotes = "+downvotes +"\n"+ "createdBy"+postedBy+"\n";
    }
}
