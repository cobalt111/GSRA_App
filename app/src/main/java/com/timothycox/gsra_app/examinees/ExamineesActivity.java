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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.Examinee;
import com.timothycox.gsra_app.model.User;
import com.timothycox.gsra_app.profile.ExamineeProfileActivity;
import com.timothycox.gsra_app.util.Firebase;

import java.util.ArrayList;
import java.util.List;

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

        // todo implement this in presenter instead
        User user = (User) getIntent().getBundleExtra("userBundle").getSerializable("user");

        Firebase firebase = Firebase.getInstance();
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(user.getUid())
                .child("examinees");

        firebase.access(true, databaseReference, new Firebase.OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                List<Examinee> examineeList = new ArrayList<>();
                Examinee examinee;

                for (DataSnapshot currentExaminee : dataSnapshot.getChildren()) {
                    examinee = new Examinee(currentExaminee.child("name").getValue(String.class),
                            currentExaminee.child("age").getValue(Integer.class),
                            currentExaminee.child("gender").getValue(String.class));
                    examineeList.add(examinee);
                }

                adapter = new ExamineesRecyclerViewAdapter(examineeList);
                examineesRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(DatabaseError databaseError) {

            }
        });

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

        presenter = new ExamineesPresenter(this);
        navigator = new ExamineesNavigator(this);
    }

    @Override
    public void navigateToExamineeCreator() {
        navigator.itemClicked(ExamineesNavigator.EXAMINEE_CREATOR_ACTIVITY, getIntent().getBundleExtra("userBundle"));
    }

    interface ExamineesScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }
}
