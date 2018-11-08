package com.timothycox.gsra_app.examinees;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.Question;
import com.timothycox.gsra_app.util.Firebase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamineesActivity extends AppCompatActivity implements ExamineesContract.View {

    private ExamineesPresenter presenter;
    private ExamineesNavigator navigator;
    Question question;


    @BindView(R.id.examinee_name_text)
    TextView nameText;
    @BindView(R.id.examinee_age_text)
    TextView ageText;
    @BindView(R.id.examinee_result_text)
    TextView resultText;
    @BindView(R.id.examinees_add_button)
    Button addExamineeButton;

    @Override
    @OnClick(R.id.examinees_add_button)
    public void onClickAddExaminee() {
        presenter.onAddExaminee();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinees);
        ButterKnife.bind(this);

        Firebase firebase = Firebase.getInstance();

        Query query = firebase.getDatabaseReference()
                .child("server")
                .child("assessments")
                .child("12-month");
        firebase.access(false, query, new Firebase.OnGetDataListener() {
            @Override
            public void onSuccessfulAdd(DataSnapshot dataSnapshot) {
                question = dataSnapshot.getValue(Question.class);
                nameText.setText(String.valueOf(question.getId()));
                ageText.setText(String.valueOf(question.getImportance()));
                resultText.setText(String.valueOf(question.getQuestionText()));
            }

            @Override
            public void onSuccessfulChange(DataSnapshot dataSnapshot) {
                question = dataSnapshot.getValue(Question.class);
            }

            @Override
            public void onSuccessfulRemoval(DataSnapshot dataSnapshot) {
                question = dataSnapshot.getValue(Question.class);
            }

            @Override
            public void onSuccessfulMove(DataSnapshot dataSnapshot) {
                question = dataSnapshot.getValue(Question.class);
            }

            @Override
            public void onFailure(DatabaseError databaseError) {

            }
        });


        presenter = new ExamineesPresenter(this);
        navigator = new ExamineesNavigator(this);
    }

    @Override
    public void navigateToExamineeCreator() {
        navigator.itemClicked(ExamineesNavigator.EXAMINEE_CREATOR_ACTIVITY, null);
    }

    interface ExamineesScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }
}
