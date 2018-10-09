package com.timothycox.gsra_app.examinees;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.timothycox.gsra_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamineesActivity extends AppCompatActivity implements ExamineesContract.View {

    private ExamineesPresenter presenter;
    private ExamineesNavigator navigator;

    @BindView(R.id.examinee_name_text)
    TextView nameText;
    @BindView(R.id.examinee_age_text)
    TextView ageText;
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
