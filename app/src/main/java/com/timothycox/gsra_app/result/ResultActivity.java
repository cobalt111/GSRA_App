package com.timothycox.gsra_app.result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.timothycox.gsra_app.R;

public class ResultActivity extends AppCompatActivity implements ResultContract.View {

    private ResultPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        presenter = new ResultPresenter(this);
    }

    interface ResultScreenEvents {
        void itemClicked(final int id);
    }
}
