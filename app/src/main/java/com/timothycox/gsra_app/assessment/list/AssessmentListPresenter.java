package com.timothycox.gsra_app.assessment.list;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.model.Assessment;
import com.timothycox.gsra_app.model.Question;
import com.timothycox.gsra_app.model.User;
import com.timothycox.gsra_app.util.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class AssessmentListPresenter implements AssessmentListContract.Presenter {

    private AssessmentListContract.View view;
    private User user;
    private Firebase firebase;


    public AssessmentListPresenter(AssessmentListContract.View view, User user) {
        this.view = view;
        this.user = user;
        firebase = Firebase.getInstance();
    }

    @Override
    public void create() {
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(user.getUid())
                .child("examinees");

        firebase.access(true, databaseReference, new Firebase.OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                String assessmentLevel = "", examinee = "", timestamp = "";
                List<Assessment> assessmentList = new ArrayList<>();
                List<Question> questionList = new ArrayList<>();
                Iterable<DataSnapshot> examinees = dataSnapshot.getChildren();
                Iterable<DataSnapshot> savedAssessments;
                Iterable<DataSnapshot> savedQuestionAnswers;

                for (DataSnapshot currentExaminee : examinees) {
                    examinee = currentExaminee.child("name").getValue(String.class);
                    savedAssessments = currentExaminee.child("savedAssessments").getChildren();
                    for (DataSnapshot currentSavedAssessment : savedAssessments) {
                        timestamp = currentSavedAssessment.child("timestamp").getValue(String.class);
                        assessmentLevel = currentSavedAssessment.child("assessmentLevel").getValue(String.class);
                        switch (assessmentLevel) {
                            case "12-month": {
                                savedQuestionAnswers = currentSavedAssessment.child("12-month").getChildren();
                                for (DataSnapshot questionAnswer : savedQuestionAnswers) {
                                    questionList.add(questionAnswer.getValue(Question.class));
                                }
                            }
                            case "24-month": {
                                savedQuestionAnswers = currentSavedAssessment.child("24-month").getChildren();
                                for (DataSnapshot questionAnswer : savedQuestionAnswers) {
                                    questionList.add(questionAnswer.getValue(Question.class));
                                }
                            }
                            case "36-month": {
                                savedQuestionAnswers = currentSavedAssessment.child("36-month").getChildren();
                                for (DataSnapshot questionAnswer : savedQuestionAnswers) {
                                    questionList.add(questionAnswer.getValue(Question.class));
                                }
                            }
                            case "60-month": {
                                savedQuestionAnswers = currentSavedAssessment.child("60-month").getChildren();
                                for (DataSnapshot questionAnswer : savedQuestionAnswers) {
                                    questionList.add(questionAnswer.getValue(Question.class));
                                }
                            }
                        }

                        savedQuestionAnswers = currentSavedAssessment.child("common").getChildren();
                        for (DataSnapshot questionAnswer : savedQuestionAnswers) {
                            HashMap<String, String> map = new HashMap<>();
                            map = (HashMap<String, String>) questionAnswer.getValue();
                            Question question = new Question();

                            question.setId(Integer.parseInt(map.get("id")));
                            question.setImportance(map.get("importance"));
                            question.setQuestionText(map.get("questionText"));

                            questionList.add(question);
                        }

                        assessmentList.add(new Assessment(questionList, assessmentLevel, examinee, timestamp));
                    }
                }
                view.setRecyclerViewAdapter(new AssessmentRecyclerViewAdapter(assessmentList));
            }


            @Override
            public void onFailure(DatabaseError databaseError) {

            }
        });
    }
}
