package com.timothycox.gsra_app.assessment.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.assessment.AssessmentActivity;
import com.timothycox.gsra_app.model.Assessment;
import com.timothycox.gsra_app.model.Question;
import com.timothycox.gsra_app.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssessmentListActivity extends AppCompatActivity implements AssessmentListContract.View {

    private AssessmentRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private AssessmentListPresenter presenter;
    private AssessmentListNavigator navigator;

    @BindView(R.id.assessmentRecyclerView) RecyclerView assessmentRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        ButterKnife.bind(this);
        presenter = new AssessmentListPresenter(this,
                (User) getIntent().getBundleExtra("userBundle").get("user"));
        navigator = new AssessmentListNavigator(this);
//        presenter.create();

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

        adapter = new AssessmentRecyclerViewAdapter(assessments);
        assessmentRecyclerView.setAdapter(adapter);

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

    @Override
    public void setRecyclerViewAdapter(AssessmentRecyclerViewAdapter adapter) {
        assessmentRecyclerView.setAdapter(adapter);
    }

    interface AssessmentListScreenEvents {

    }
}
