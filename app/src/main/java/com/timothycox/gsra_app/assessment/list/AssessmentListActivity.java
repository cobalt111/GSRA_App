package com.timothycox.gsra_app.assessment.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.assessment.AssessmentActivity;
import com.timothycox.gsra_app.model.Assessment;
import com.timothycox.gsra_app.model.Question;
import com.timothycox.gsra_app.util.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssessmentListActivity extends AppCompatActivity implements AssessmentListContract.View {

    private ListingsAdapter adapter;
    private LinearLayoutManager layoutManager;

    @BindView(R.id.assessmentRecyclerView)
    RecyclerView assessmentRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        ButterKnife.bind(this);

        List<Question> questions = new ArrayList<>();
        List<Assessment> assessments = new ArrayList<>();
        Question sampleQuestion = new Question();
        sampleQuestion.setQuestionText("This is sample answer");
        sampleQuestion.setImportance("high");
        sampleQuestion.setId(4);
        questions.add(sampleQuestion);
        Assessment sampleAssessment = new Assessment(questions, "12-month", "Isaac", "11-2-18");
        assessments.add(sampleAssessment);

        sampleQuestion.setQuestionText("This is sample answer");
        sampleQuestion.setImportance("high");
        sampleQuestion.setId(4);
        questions.add(sampleQuestion);
        sampleAssessment = new Assessment(questions, "12-month", "Beth", "12-8-17");
        assessments.add(sampleAssessment);

        sampleQuestion.setQuestionText("This is sample answer");
        sampleQuestion.setImportance("high");
        sampleQuestion.setId(4);
        questions.add(sampleQuestion);
        sampleAssessment = new Assessment(questions, "12-month", "Oscar", "1-8-16");
        assessments.add(sampleAssessment);

        sampleQuestion.setQuestionText("This is sample answer");
        sampleQuestion.setImportance("high");
        sampleQuestion.setId(4);
        questions.add(sampleQuestion);
        sampleAssessment = new Assessment(questions, "12-month", "Penny", "3-8-18");
        assessments.add(sampleAssessment);

        sampleQuestion.setQuestionText("This is sample answer");
        sampleQuestion.setImportance("high");
        sampleQuestion.setId(4);
        questions.add(sampleQuestion);
        sampleAssessment = new Assessment(questions, "12-month", "Wilbur", "11-8-18");
        assessments.add(sampleAssessment);

        sampleQuestion.setQuestionText("This is sample answer");
        sampleQuestion.setImportance("high");
        sampleQuestion.setId(4);
        questions.add(sampleQuestion);
        sampleAssessment = new Assessment(questions, "12-month", "Rachel", "11-8-18");
        assessments.add(sampleAssessment);

        sampleQuestion.setQuestionText("This is sample answer");
        sampleQuestion.setImportance("high");
        sampleQuestion.setId(4);
        questions.add(sampleQuestion);
        sampleAssessment = new Assessment(questions, "12-month", "Harry", "11-8-18");
        assessments.add(sampleAssessment);

        sampleQuestion.setQuestionText("This is sample answer");
        sampleQuestion.setImportance("high");
        sampleQuestion.setId(4);
        questions.add(sampleQuestion);
        sampleAssessment = new Assessment(questions, "12-month", "Joe", "11-8-18");
        assessments.add(sampleAssessment);

        adapter = new ListingsAdapter(assessments);
        assessmentRecyclerView.setAdapter(adapter);

        Firebase firebase = Firebase.getInstance();
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child("uid");

//        firebase.access(true, databaseReference, new Firebase.OnGetDataListener() {
//            @Override
//            public void onSuccess(DataSnapshot dataSnapshot) {
//                String assessmentLevel = "", examinee = "", timestamp = "";
//                List<Assessment> assessmentList = new ArrayList<>();
//                List<Question> questionList = new ArrayList<>();
//                Iterable<DataSnapshot> examinees = dataSnapshot.getChildren();
//                Iterable<DataSnapshot> savedAssessments;
//                Iterable<DataSnapshot> savedQuestionAnswers;
//
//                for (DataSnapshot currentExaminee : examinees) {
//                    examinee = currentExaminee.child("name").getValue(String.class);
//                    savedAssessments = currentExaminee.child("savedAssessments").getChildren();
//                    for (DataSnapshot currentSavedAssessment : savedAssessments) {
//                        timestamp = currentSavedAssessment.child("timestamp").getValue(String.class);
//                        assessmentLevel = currentSavedAssessment.child("assessmentLevel").getValue(String.class);
//                        switch (assessmentLevel) {
//                            case "12-month": {
//                                savedQuestionAnswers = currentSavedAssessment.child("12-month").getChildren();
//                                for (DataSnapshot questionAnswer : savedQuestionAnswers) {
//                                    questionList.add(questionAnswer.getValue(Question.class));
//                                }
//                            }
//                            case "24-month": {
//                                savedQuestionAnswers = currentSavedAssessment.child("24-month").getChildren();
//                                for (DataSnapshot questionAnswer : savedQuestionAnswers) {
//                                    questionList.add(questionAnswer.getValue(Question.class));
//                                }
//                            }
//                            case "36-month": {
//                                savedQuestionAnswers = currentSavedAssessment.child("36-month").getChildren();
//                                for (DataSnapshot questionAnswer : savedQuestionAnswers) {
//                                    questionList.add(questionAnswer.getValue(Question.class));
//                                }
//                            }
//                            case "60-month": {
//                                savedQuestionAnswers = currentSavedAssessment.child("60-month").getChildren();
//                                for (DataSnapshot questionAnswer : savedQuestionAnswers) {
//                                    questionList.add(questionAnswer.getValue(Question.class));
//                                }
//                            }
//                        }
//
//                        savedQuestionAnswers = currentSavedAssessment.child("common").getChildren();
//                        for (DataSnapshot questionAnswer : savedQuestionAnswers) {
//                            HashMap<String, String> map = new HashMap<>();
//                            map = (HashMap<String, String>) questionAnswer.getValue();
//                            Question question = new Question();
//
//                            question.setId(Integer.parseInt(map.get("id")));
//                            question.setImportance(map.get("importance"));
//                            question.setQuestionText(map.get("questionText"));
//
//                            questionList.add(question);
//                        }
//
//                        assessmentList.add(new Assessment(questionList, assessmentLevel, examinee, timestamp));
//                    }
//                }
//
//                adapter = new ListingsAdapter(assessmentList);
//                assessmentRecyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onSuccessfulChange(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onSuccessfulRemoval(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onSuccessfulMove(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onFailure(DatabaseError databaseError) {
//
//            }
//        });

        assessmentRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        assessmentRecyclerView.setLayoutManager(layoutManager);
        assessmentRecyclerView.setItemAnimator(new DefaultItemAnimator());

        assessmentRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                assessmentRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent openAssessmentIntent = new Intent(getApplicationContext(), AssessmentActivity.class);
//                openProfileIntent.putExtra("animalID", adapter.animalList
//                        .get(position)
//                        .getKey());

                startActivity(openAssessmentIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    interface AssessmentListScreenEvents {

    }
}
