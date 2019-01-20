package com.timothycox.gsra_app.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.Examinee;

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
    @BindView(R.id.profileNeutralImage) ImageView profileNeutralImage;
    @BindView(R.id.profileRecyclerView) RecyclerView profileRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        presenter = new ExamineeProfilePresenter(this,
                (Examinee) getIntent().getSerializableExtra("selectedExaminee"));
        navigator = new ExamineeProfileNavigator(this);
        presenter.create();
    }

    // todo finish this tutorial
    @Override
    public void showTutorial(boolean retry) {
        ShowcaseView introSV = new ShowcaseView.Builder(this)
                .setContentTitle("Examinee Profile")
                .setContentText("This application is intended for people who want to take assessments for those who may have Autism Spectrum Disorders. This is the home screen.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase()
                .build();
        ShowcaseView.Builder infoSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.main_test_button,this))
                .setContentTitle("Take a new test")
                .setContentText("This button will take you to the examinee select screen. You can choose an examinee to take an assessment for and/or create a new examinee.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase();
        ShowcaseView.Builder assessmentsTakenSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.main_previous_assessments_button,this))
                .setContentTitle("Previous assessments")
                .setContentText("This button will show you previous assessments that you have taken.")
                .setStyle(R.style.CustomShowcaseThemeDone)
                .withHoloShowcase();
        if (!introSV.isShowing()) introSV.show();
        introSV.overrideButtonClick((View view) -> {
            introSV.hide();
            introSV.hideButton();
            ShowcaseView infoSV = infoSvBuilder.build();
            infoSV.show();
            infoSV.overrideButtonClick((View anotherView) -> {
                infoSV.hide();
                infoSV.hideButton();
                ShowcaseView assessmentTakenSV = assessmentsTakenSvBuilder.build();
                assessmentTakenSV.show();
                assessmentTakenSV.overrideButtonClick((View thirdView) -> { assessmentTakenSV.hide(); assessmentTakenSV.hideButton();
                });
            });
        });
        if (!retry) presenter.onTutorialSeen();
    }

    @Override
    public void populateUIWithData(Examinee examinee) {
        profileNameText.setText(examinee.getName());
        profileAgeText.setText(String.valueOf(examinee.getAgeAsString()));

        if (examinee.getGender().equals("Male")) {
            profileGirlImage.setVisibility(View.GONE);
            profileNeutralImage.setVisibility(View.GONE);
        }
        else if (examinee.getGender().equals("Female")) {
            profileBoyImage.setVisibility(View.GONE);
            profileNeutralImage.setVisibility(View.GONE);
        }
        else {
            profileGirlImage.setVisibility(View.GONE);
            profileBoyImage.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_retry_tutorial) {
            presenter.retryTutorial();
        }
        return super.onOptionsItemSelected(item);
    }

    interface ProfileClickEvents {
        void itemClicked(final int id);
    }
}
