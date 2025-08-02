package com.example.StackOverflow_advanced.Entity;

import java.time.LocalTime;
import java.util.Set;

public class Question {
    String id;
    String title;
    String text;
    Set<User> upvotedBy;
    Set<User> downvotedBy;
    int upvotes;
    int downvotes;
    User postedBy;
    LocalTime postedAt;
    

    public Question(String id, String title, String text, Set<User> upvotedBy, Set<User> downvotedBy, int upvotes, int downvotes, User postedBy, LocalTime postedAt) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.upvotedBy = upvotedBy;
        this.downvotedBy = downvotedBy;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public LocalTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalTime postedAt) {
        this.postedAt = postedAt;
    }

    public String toString(){
        return "id = " + id+"\n"+"title = " +title+"\n"+
                "text = "+text + "\n"+"upvotes = "+upvotes+"\n"+
                "downvotes = "+downvotes +"\n"+ "createdBy"+postedBy+"\n";
    }
}
