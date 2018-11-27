package com.timothycox.gsra_app.examinees;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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
                startActivity(openExamineeProfileIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void setRecyclerViewAdapter(ExamineesRecyclerViewAdapter adapter) {
        examineesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void navigateToExamineeCreator() {
        navigator.itemClicked(ExamineesNavigator.EXAMINEE_CREATOR_ACTIVITY, getIntent().getBundleExtra("userBundle"));
    }

    interface ExamineesScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }
}
