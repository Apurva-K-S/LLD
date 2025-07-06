package com.example.StackOverflow;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Question {
    UUID id;
    String text;
    Person postedBy;
    Date postedAt;
    Set<UUID> upVotedUserIds;
    Set<UUID> downVotedUserIds;
    Status questionStatus;
    List<Answer> answers;

    public Question(UUID id, String text, Person postedBy, Date postedAt, Set<UUID> upVotedUserIds, Set<UUID> downVotedUserIds, List<Answer> answers) {
        this.id = id;
        this.text = text;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
        this.upVotedUserIds = upVotedUserIds;
        this.downVotedUserIds = downVotedUserIds;
        this.questionStatus = Status.OPEN;
        this.answers = answers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Status getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(Status questionStatus) {
        this.questionStatus = questionStatus;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
