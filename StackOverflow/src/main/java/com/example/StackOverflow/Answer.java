package com.example.StackOverflow;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Answer {
    UUID id;
    Question question;
    String text;
    Person postedBy;
    Date postedAt;
    List<Comment> comments;
    Status answerStatus;
    Set<UUID> upVotedUserIds;
    Set<UUID> downVotedUserIds;

    public Answer(UUID id, Question question, String text, Person postedBy, Date postedAt, List<Comment> comments, Set<UUID> upVotedUserIds, Set<UUID> downVotedUserIds) {
        this.id = id;
        this.question = question;
        this.text = text;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
        this.comments = comments;
        this.answerStatus = Status.OPEN;
        this.upVotedUserIds = upVotedUserIds;
        this.downVotedUserIds = downVotedUserIds;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Status getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(Status answerStatus) {
        this.answerStatus = answerStatus;
    }

    public Set<UUID> getUpVotedUserIds() {
        return upVotedUserIds;
    }

    public void setUpVotedUserIds(Set<UUID> upVotedUserIds) {
        this.upVotedUserIds = upVotedUserIds;
    }

    public Set<UUID> getDownVotedUserIds() {
        return downVotedUserIds;
    }

    public void setDownVotedUserIds(Set<UUID> downVotedUserIds) {
        this.downVotedUserIds = downVotedUserIds;
    }
}
