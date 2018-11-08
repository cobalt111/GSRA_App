package com.timothycox.gsra_app.model;

import java.util.List;

public class Assessment {
    private List<Question> questions;
    private String category;
    private String examinee;
    private String timestamp;

    public Assessment(List<Question> questions, String category, String examinee, String timestamp) {
        this.questions = questions;
        this.category = category;
        this.examinee = examinee;
        this.timestamp = timestamp;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExaminee() {
        return examinee;
    }

    public void setExaminee(String examinee) {
        this.examinee = examinee;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
