package com.timothycox.gsra_app.result;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timothycox.gsra_app.R;

public class ResultActivity extends AppCompatActivity implements ResultView {

    private ResultPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        presenter = new ResultPresenter(this);
    }
}
