package com.timothycox.gsra_app.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.timothycox.gsra_app.R;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {

    private ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        presenter = new ProfilePresenter(this);
    }

    interface ProfileClickEvents {
        void itemClicked(final int id);
    }
}
