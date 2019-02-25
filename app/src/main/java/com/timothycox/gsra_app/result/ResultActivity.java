package com.timothycox.gsra_app.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.timothycox.gsra_app.model.Assessment;
import com.timothycox.gsra_app.model.Examinee;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity implements ResultContract.View {

    private ResultPresenter presenter;
    private ResultNavigator navigator;

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
        presenter = new ResultPresenter(this,
                (Examinee) getIntent().getSerializableExtra("selectedExaminee"),
                (Assessment) getIntent().getSerializableExtra("assessment"));
        navigator = new ResultNavigator(this);
        presenter.create();
    }

    //todo finish this tutorial
    @Override
    public void showTutorial(boolean retry) {
        ShowcaseView introSV = new ShowcaseView.Builder(this)
                .setContentTitle("Assessment results")
                .setContentText("This screen shows the results of the assessment for the examinee.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase()
                .hideOnTouchOutside()
                .build();
        ShowcaseView.Builder infoSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.resultScoreText,this))
                .setContentTitle("Final score")
                .setContentText("This is the score the examinee has been given for the assessment.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase()
                .hideOnTouchOutside();
        ShowcaseView.Builder assessmentsTakenSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.resultExplanationText,this))
                .setContentTitle("Score explanation")
                .setContentText("This will provide more information about the next steps to take based on the score given to your examinee.")
                .setStyle(R.style.CustomShowcaseThemeDone)
                .withHoloShowcase()
                .hideOnTouchOutside();
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
    public void populatedUIWithData(Examinee examinee, Assessment assessment) {
        nameText.setText(examinee.getName());
        ageText.setText(examinee.getAgeAsHumanReadable());
        dateText.setText(assessment.getTimestamp());
        scoreText.setText(String.valueOf(assessment.getResult()));
        switch (examinee.getGender()) {
            case "Male": {
                girlFace.setVisibility(View.GONE);
                neutralFace.setVisibility(View.GONE);
                break;
            }
            case "Female": {
                boyFace.setVisibility(View.GONE);
                neutralFace.setVisibility(View.GONE);
                break;
            }
            default: {
                girlFace.setVisibility(View.GONE);
                boyFace.setVisibility(View.GONE);
                break;
            }
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

    interface ResultScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }

    @Override
    @OnClick(R.id.resultTakeNewTestButton)
    public void onClickNewTest() {
        presenter.onNewTest();
    }

    @Override
    public void navigateToNewTest(Bundle bundle) {
        navigator.itemClicked(ResultNavigator.ASSESSMENT_ACTIVITY, bundle);
        finish();
    }

    @Override
    @OnClick(R.id.resultLearnMoreButton)
    public void onClickLearnMore() {
        presenter.onLearnMore();
    }

    @Override
    public void navigateToLearnMore(Bundle bundle) {
        navigator.itemClicked(ResultNavigator.INFORMATION_ACTIVITY, bundle);
    }
}
