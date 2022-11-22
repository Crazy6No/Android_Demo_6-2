package com.example.demo6_1.bean;

import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String QUERY = "query";
    public static final String INSERT = "insert";
    public static final String UPDATE = "update";

    private int questionId;

    private String questionContent;
    private String questionTime;
    private String answerContent;
    private String answerTime;
    private String questionStatus;


    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(String questionTime) {
        this.questionTime = questionTime;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", questionContent="
                + questionContent + ", questionTime=" + questionTime
                + ", answerContent=" + answerContent  + ", answerTime="
                + answerTime  + ", questionStatus=" + questionStatus  + ']';
    }
}
