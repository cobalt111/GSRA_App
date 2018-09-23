package com.timothycox.gsra_app.respondents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timothycox.gsra_app.R;

public class RespondentsActivity extends AppCompatActivity implements RespondentsView {

    private RespondentsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respondents);
        presenter = new RespondentsPresenter(this);
    }
}
