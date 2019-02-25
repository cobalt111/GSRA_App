package com.timothycox.gsra_app.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.firebase.FirebaseApp;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.util.NetworkStateReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        NetworkStateReceiver.NetworkStateReceiverListener, MainContract.View {

    private MainPresenter presenter;
    private MainNavigator navigator;

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.main_test_button) Button testsButton;
    @BindView(R.id.main_previous_assessments_button) Button assessmentsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);
        presenter = new MainPresenter(this, getIntent().getBundleExtra("userBundle"));
        navigator = new MainNavigator(this);


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        presenter.create();
    }



    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void networkAvailable() {

    }

    @Override
    public void networkUnavailable() {

    }

    @Override
    public void showTutorial(final boolean isRetrySession) {
        ShowcaseView introSV = new ShowcaseView.Builder(this)
                .setContentTitle("Hello!")
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
        if (!introSV.isShowing())
            introSV.show();
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
        if (!isRetrySession) presenter.onTutorialSeen();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        if (id == R.id.action_retry_tutorial)
            presenter.retryTutorial();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    @OnClick(R.id.main_test_button)
    public void onClickTests() {
        presenter.onTests();
    }

    @Override
    public void navigateToTests(Bundle bundle) {
        navigator.itemClicked(MainNavigator.EXAMINEES_ACTIVITY, bundle);
    }


    @Override
    @OnClick(R.id.main_previous_assessments_button)
    public void onClickAssessments() {
        presenter.onAssessments();
    }


    @Override
    public void navigateToAssessments(Bundle bundle) {
        navigator.itemClicked(MainNavigator.ASSESSMENT_LIST_ACTIVITY, bundle);
    }

    interface MainScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }
}
