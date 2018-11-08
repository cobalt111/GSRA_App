package com.timothycox.gsra_app.assessment.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.timothycox.gsra_app.R;

public class AssessmentListActivity extends AppCompatActivity implements AssessmentListContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
    }

    interface AssessmentListScreenEvents {

    }
}
