package com.timothycox.gsra_app.assessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timothycox.gsra_app.R;

public class AssessmentActivity extends AppCompatActivity implements AssessmentView {

    private AssessmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        presenter = new AssessmentPresenter(this);
    }
}
