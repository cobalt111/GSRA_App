package com.timothycox.gsra_app.respondents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.timothycox.gsra_app.R;

public class RespondentsActivity extends AppCompatActivity implements RespondentsContract.View {

    private RespondentsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respondents);
        presenter = new RespondentsPresenter(this);
    }

    interface RespondentsScreenEvents {
        void itemClicked(final int id);
    }
}
