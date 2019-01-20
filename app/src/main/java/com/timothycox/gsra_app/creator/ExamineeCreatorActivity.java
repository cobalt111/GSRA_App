package com.timothycox.gsra_app.creator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamineeCreatorActivity extends AppCompatActivity implements ExamineeCreatorContract.View {

    private ExamineeCreatorPresenter presenter;
    private ExamineeCreatorNavigator navigator;

    @BindView(R.id.examinee_creator_name_textfield) EditText nameTextField;
    @BindView(R.id.examinee_creator_age_textfield) EditText ageTextField;
    @BindView(R.id.examinee_creator_add_button) Button addExamineeButton;
    @BindView(R.id.examineeCreatorGenderSpinner) Spinner genderSpinner;

    @Override
    @OnClick(R.id.examinee_creator_add_button)
    public void onClickAddExaminee() {
        presenter.onAddExaminee();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinee_creator);
        ButterKnife.bind(this);
        presenter = new ExamineeCreatorPresenter(this,
                (User) getIntent().getBundleExtra("userBundle").getSerializable("user"));
        navigator = new ExamineeCreatorNavigator(this);
    }

    //todo change strings
    @Override
    public void showTutorial(final boolean retry) {
        ShowcaseView introSV = new ShowcaseView.Builder(this)
                .setContentTitle("Examinee Creator")
                .setContentText("This application is intended for people who want to take assessments for those who may have Autism Spectrum Disorders. This is the home screen.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase()
                .build();
        ShowcaseView.Builder nameSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.examinee_creator_name_textfield,this))
                .setContentTitle("Take a new test")
                .setContentText("This button will take you to the examinee select screen. You can choose an examinee to take an assessment for and/or create a new examinee.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase();
        ShowcaseView.Builder ageSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.examinee_creator_age_textfield,this))
                .setContentTitle("Previous assessments")
                .setContentText("This button will show you previous assessments that you have taken.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase();
        ShowcaseView.Builder genderSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.examineeCreatorGenderSpinner, this))
                .setContentTitle("Start tutorials again")
                .setContentText("These three dots will show an options menu if selected. You may choose to view the tutorial for the current screen again at any time by selecting, \"Start Tutorial.\"")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase();
        ShowcaseView.Builder submitSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.examinee_creator_add_button, this))
                .setContentTitle("Start tutorials again")
                .setContentText("These three dots will show an options menu if selected. You may choose to view the tutorial for the current screen again at any time by selecting, \"Start Tutorial.\"")
                .setStyle(R.style.CustomShowcaseThemeDone)
                .withHoloShowcase();
        if (!introSV.isShowing()) introSV.show();
        introSV.overrideButtonClick((View view) -> {
            introSV.hide();
            introSV.hideButton();
            ShowcaseView nameSV = nameSvBuilder.build();
            nameSV.show();
            nameSV.overrideButtonClick((View anotherView) -> {
                nameSV.hide();
                nameSV.hideButton();
                ShowcaseView ageSV = ageSvBuilder.build();
                ageSV.show();
                ageSV.overrideButtonClick((View thirdView) -> {
                    ageSV.hide();
                    ageSV.hideButton();
                    ShowcaseView genderSV = genderSvBuilder.build();
                    genderSV.show();
                    genderSV.overrideButtonClick((View fourthView) -> {
                        genderSV.hide();
                        genderSV.hideButton();
                        ShowcaseView submitSV = submitSvBuilder.build();
                        submitSV.show();
                        submitSV.overrideButtonClick((View fifthView) -> {submitSV.hide(); submitSV.hideButton();});
                    });
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

    @Override
    public Bundle saveEnteredExamineeData() {
        Bundle bundle = new Bundle();
        bundle.putString("name", nameTextField.getText().toString());
        bundle.putInt("age", Integer.parseInt(ageTextField.getText().toString()));
        bundle.putString("gender", genderSpinner.getSelectedItem().toString());
        return bundle;
    }

    @Override
    public void navigateToAssessments(Bundle bundle) {
        navigator.itemClicked(ExamineeCreatorNavigator.ASSESSMENTS_ACTIVITY, bundle);
    }

    interface CreatorScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }
}
