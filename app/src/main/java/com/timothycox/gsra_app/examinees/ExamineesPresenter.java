package com.timothycox.gsra_app.examinees;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.model.Examinee;
import com.timothycox.gsra_app.model.User;
import com.timothycox.gsra_app.util.Firebase;

import java.util.ArrayList;
import java.util.List;

class ExamineesPresenter implements ExamineesContract.Presenter {

    private ExamineesContract.View view;
    private User user;
    private Firebase firebase;

    ExamineesPresenter(ExamineesContract.View view, User user) {
        this.view = view;
        this.user = user;
        firebase = Firebase.getInstance();
    }

    @Override
    public void onAddExaminee() {
        view.navigateToExamineeCreator();
    }

    @Override
    public void create() {
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
                view.setRecyclerViewAdapter(new ExamineesRecyclerViewAdapter(examineeList));
            }

            @Override
            public void onFailure(DatabaseError databaseError) {

            }
        });
    }
}
