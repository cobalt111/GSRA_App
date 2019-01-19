package com.timothycox.gsra_app.model;

import java.io.Serializable;
import java.util.List;

public class Examinee implements Serializable {

    private String name;
    private int age;
    private List<Assessment> assessments;
    private String gender;

    public Examinee(String name, int age, String gender/*, List<Assessment> assessments */) {
        this.name = name;
        this.age = age;
        this.gender = gender;
//        this.assessments = assessments;
    }

    public String getAgeAsString() {
        int years = age / 12;
        int months = age % 12;
        String monthTerm = "", yearsTerm = "";

        if (months == 1)
            monthTerm = "month";
        else if (months > 1)
            monthTerm = "months";

        if (years == 1)
            yearsTerm = "year";
        else if (months > 1)
            yearsTerm = "years";

        if (years == 0)
            return new StringBuilder(String.valueOf(age))
                    .append(" ")
                    .append(monthTerm)
                    .toString();
        else {
            StringBuilder builder = new StringBuilder(String.valueOf(years))
                .append(" ")
                .append(yearsTerm);
            if (months > 0) {
                builder.append(", ")
                        .append(months)
                        .append(" ")
                        .append(monthTerm);
            }
            return builder.toString();
        }
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
