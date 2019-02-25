package com.timothycox.gsra_app.information;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.Examinee;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformationActivity extends AppCompatActivity implements InformationContract.View {

    private InformationPresenter presenter;
    private InformationNavigator navigator;

    @BindView(R.id.informationHeadline) TextView informationHeadlineText;
    @BindView(R.id.firstInformationSiteButton) Button firstInformationSiteButton;
    @BindView(R.id.secondInformationSiteButton) Button secondInformationSiteButton;

    //todo make proper
    @OnClick(R.id.firstInformationSiteButton)
    public void onClickFirstSite() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://www.autismspeaks.org"));
        startActivity(intent);
    }

    @OnClick(R.id.secondInformationSiteButton)
    public void onClickSecondSite() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://www.nimh.nih.gov/health/topics/autism-spectrum-disorders-asd/index.shtml"));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        presenter = new InformationPresenter(this, (Examinee) getIntent().getSerializableExtra("selectedExaminee"));
        navigator = new InformationNavigator(this);
    }

    // todo finish tutorial
    @Override
    public void showTutorial(boolean retry) {
        ShowcaseView introSV = new ShowcaseView.Builder(this)
                .setContentTitle("More Information")
                .setContentText("This application is intended for people who want to take assessments for those who may have Autism Spectrum Disorders. This is the home screen.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase()
                .hideOnTouchOutside()
                .build();
        ShowcaseView.Builder testSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.main_test_button,this))
                .setContentTitle("Take a new test")
                .setContentText("This button will take you to the examinee select screen. You can choose an examinee to take an assessment for and/or create a new examinee.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase()
                .hideOnTouchOutside();
        ShowcaseView.Builder assessmentSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.main_previous_assessments_button,this))
                .setContentTitle("Previous assessments")
                .setContentText("This button will show you previous assessments that you have taken.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase()
                .hideOnTouchOutside();
        ShowcaseView.Builder retryTutorialSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.main_sv_menu_anchor_textview, this))
                .setContentTitle("Start tutorials again")
                .setContentText("These three dots will show an options menu if selected. You may choose to view the tutorial for the current screen again at any time by selecting, \"Start Tutorial.\"")
                .setStyle(R.style.CustomShowcaseThemeDone)
                .withHoloShowcase()
                .hideOnTouchOutside();
        if (!introSV.isShowing()) introSV.show();
        introSV.overrideButtonClick((View view) -> {
            introSV.hide();
            introSV.hideButton();
            ShowcaseView testSV = testSvBuilder.build();
            testSV.show();
            testSV.overrideButtonClick((View anotherView) -> {
                testSV.hide();
                testSV.hideButton();
                ShowcaseView assessmentSV = assessmentSvBuilder.build();
                assessmentSV.show();
                assessmentSV.overrideButtonClick((View thirdView) -> {
                    assessmentSV.hide();
                    assessmentSV.hideButton();
                    ShowcaseView retrySV = retryTutorialSvBuilder.build();
                    retrySV.setShowcaseX(1);
                    retrySV.show();
                    retrySV.overrideButtonClick((View fourthView) -> {retrySV.hide(); retrySV.hideButton();});
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

    interface InformationScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }
}
