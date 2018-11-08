package com.timothycox.gsra_app.model;

import java.io.Serializable;

public class Question implements Serializable {

    private long id;
    private String importance;
    private String questionText;

    public Question() {
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }
}
