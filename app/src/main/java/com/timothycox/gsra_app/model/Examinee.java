package com.timothycox.gsra_app.model;

import java.util.List;

public class Examinee {

    private String name;
    private int age;
    private List<Assessment> assessments;

    public Examinee(String name, int age, List<Assessment> assessments) {
        this.name = name;
        this.age = age;
        this.assessments = assessments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}