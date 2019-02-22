package com.timothycox.gsra_app.model;

import java.io.Serializable;
import java.util.List;

public class Assessment implements Serializable {
    private List<Question> questions;
    private String category;
    private String examinee;
    private String timestamp;
    private boolean completed;
    private int result;

    // todo convert to builder pattern
    public Assessment(/*List<Question> questions, String category, String examinee, String timestamp, boolean completed, int result*/) {
//        this.questions = questions;
//        this.category = category;
//        this.examinee = examinee;
//        this.timestamp = timestamp;
//        this.completed = completed;
//        this.result = result;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
