package com.example.StackOverflow;

import java.util.Date;
import java.util.UUID;

public class Comment {
    UUID id;
    String text;
    Answer answer;
    Person postedBy;
    Date postedAt;

    public Comment(UUID id, String text, Answer answer, Person postedBy, Date postedAt) {
        this.id = id;
        this.text=text;
        this.answer = answer;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Person getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Person postedBy) {
        this.postedBy = postedBy;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }
}
