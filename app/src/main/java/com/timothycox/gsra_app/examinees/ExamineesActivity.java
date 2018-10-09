package com.timothycox.gsra_app.examinees;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.timothycox.gsra_app.R;

public class ExamineesActivity extends AppCompatActivity implements ExamineesContract.View {

    private ExamineesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinees);
        presenter = new ExamineesPresenter(this);
    }

    interface ExamineesScreenEvents {
        void itemClicked(final int id);
    }
}
