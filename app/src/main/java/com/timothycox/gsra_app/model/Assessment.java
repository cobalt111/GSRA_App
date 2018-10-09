package com.timothycox.gsra_app.model;

import java.util.List;

public class Assessment {
    private List<Question> questions;
    private AssessmentCategory category;

    public Assessment(List<Question> questions, AssessmentCategory category) {
        this.questions = questions;
        this.category = category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public AssessmentCategory getCategory() {
        return category;
    }

    public void setCategory(AssessmentCategory category) {
        this.category = category;
    }
}
