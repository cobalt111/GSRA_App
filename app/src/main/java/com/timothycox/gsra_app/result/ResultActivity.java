package com.timothycox.gsra_app.result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.Examinee;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity implements ResultContract.View {

    private ResultPresenter presenter;

    @BindView(R.id.resultBoyFace) ImageView boyFace;
    @BindView(R.id.resultGirlFace) ImageView girlFace;
    @BindView(R.id.resultNeutralFace) ImageView neutralFace;
    @BindView(R.id.resultAgeText) TextView ageText;
    @BindView(R.id.resultAgeLabelText) TextView ageTextLabel;
    @BindView(R.id.resultDateText) TextView dateText;
    @BindView(R.id.resultDateLabelText) TextView dateLabelText;
    @BindView(R.id.resultNameText) TextView nameText;
    @BindView(R.id.resultExplanationText) TextView explanationText;
    @BindView(R.id.resultScoreText) TextView scoreText;
    @BindView(R.id.resultScoreLabelText) TextView scoreLabelText;
    @BindView(R.id.resultLearnMoreButton) Button learnMoreButton;
    @BindView(R.id.resultTakeNewTestButton) Button takeNewTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        presenter = new ResultPresenter(this, (Examinee) getIntent().getSerializableExtra("selectedExaminee"));
    }

    //todo finish this tutorial
    @Override
    public void showTutorial(boolean retry) {
        ShowcaseView introSV = new ShowcaseView.Builder(this)
                .setContentTitle("Assessment Results")
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

    interface ResultScreenEvents {
        void itemClicked(final int id);
    }
}
