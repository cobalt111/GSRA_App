package com.timothycox.gsra_app.examinees;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.User;
import com.timothycox.gsra_app.profile.ExamineeProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamineesActivity extends AppCompatActivity implements ExamineesContract.View {

    private ExamineesPresenter presenter;
    private ExamineesNavigator navigator;
    private ExamineesRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;

    @BindView(R.id.examinees_add_button) Button addExamineeButton;
    @BindView(R.id.examineesRecyclerView) RecyclerView examineesRecyclerView;

    @Override
    @OnClick(R.id.examinees_add_button)
    public void onClickAddExaminee() {
        presenter.onAddExaminee();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinees);
        ButterKnife.bind(this);
        presenter = new ExamineesPresenter(this,
                (User) getIntent().getBundleExtra("userBundle").getSerializable("user"));
        navigator = new ExamineesNavigator(this);
        presenter.create();

        examineesRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        examineesRecyclerView.setLayoutManager(layoutManager);
        examineesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        examineesRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                examineesRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent openExamineeProfileIntent = new Intent(getApplicationContext(), ExamineeProfileActivity.class);
                openExamineeProfileIntent.putExtra("userBundle", getIntent().getBundleExtra("userBundle"));
                openExamineeProfileIntent.putExtra("selectedExaminee", adapter.examineeList.get(position));
                startActivity(openExamineeProfileIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    //todo change strings
    @Override
    public void showTutorial(boolean retry) {
        ShowcaseView introSV = new ShowcaseView.Builder(this)
                .setContentTitle("Examinees")
                .setContentText("This screens shows the list of examinees registered to your account.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase()
                .hideOnTouchOutside()
                .build();
        ShowcaseView.Builder listItemSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.examineesRecyclerView,this))
                .setContentTitle("Examinee list")
                .setContentText("Each examinee will appear in a list here.")
                .setStyle(R.style.CustomShowcaseThemeNext)
                .withHoloShowcase()
                .hideOnTouchOutside();
        ShowcaseView.Builder addExamineeSvBuilder = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.examinees_add_button,this))
                .setContentTitle("Add new examinee")
                .setContentText("To add a new examinee, click this button.")
                .setStyle(R.style.CustomShowcaseThemeDone)
                .withHoloShowcase()
                .hideOnTouchOutside();
        if (!introSV.isShowing()) introSV.show();
        introSV.overrideButtonClick((View view) -> {
            introSV.hide();
            introSV.hideButton();
            ShowcaseView listSV = listItemSvBuilder.build();
            listSV.show();
            listSV.overrideButtonClick((View anotherView) -> {
                listSV.hide();
                listSV.hideButton();
                ShowcaseView addExamineeSV = addExamineeSvBuilder.build();
                addExamineeSV.show();
                addExamineeSV.overrideButtonClick((View thirdView) -> {addExamineeSV.hide(); addExamineeSV.hideButton();
                });
            });
        });
        if (!retry) presenter.onTutorialSeen();
    }

    @Override
    public void setRecyclerViewAdapter(ExamineesRecyclerViewAdapter adapter) {
        this.adapter = adapter;
        examineesRecyclerView.setAdapter(adapter);
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
    public void navigateToExamineeCreator() {
        navigator.itemClicked(ExamineesNavigator.EXAMINEE_CREATOR_ACTIVITY, getIntent().getBundleExtra("userBundle"));
    }

    interface ExamineesScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }
}
