package com.timothycox.gsra_app.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.timothycox.gsra_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamineeProfileActivity extends AppCompatActivity implements ExamineeProfileContract.View {

    private ExamineeProfilePresenter presenter;
    private ExamineeProfileNavigator navigator;

    @BindView(R.id.profileNameText) TextView profileNameText;
    @BindView(R.id.profileAgeLabel) TextView profileAgeLabel;
    @BindView(R.id.profileAgeText) TextView profileAgeText;
    @BindView(R.id.profilePreviousAssessmentsLabel) TextView profilePreviousAssessmentLabel;
    @BindView(R.id.profileBoyImage) ImageView profileBoyImage;
    @BindView(R.id.profileGirlImage) ImageView profileGirlImage;
    @BindView(R.id.profileRecyclerView) RecyclerView profileRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        presenter = new ExamineeProfilePresenter(this);
        navigator = new ExamineeProfileNavigator(this);
    }

    interface ProfileClickEvents {
        void itemClicked(final int id);
    }
}
