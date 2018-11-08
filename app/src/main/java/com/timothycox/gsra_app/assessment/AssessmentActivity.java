package com.timothycox.gsra_app.assessment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.timothycox.gsra_app.R;

public class AssessmentActivity extends AppCompatActivity implements AssessmentContract.View {

    private AssessmentPresenter presenter;
    private AssessmentNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        presenter = new AssessmentPresenter(this);
        navigator = new AssessmentNavigator(this);
    }

    interface AssessmentScreenEvents {
        void itemClicked(final int id);
    }
}
