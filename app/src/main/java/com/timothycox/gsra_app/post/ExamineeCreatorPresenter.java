package com.timothycox.gsra_app.post;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.model.User;
import com.timothycox.gsra_app.util.Firebase;

class ExamineeCreatorPresenter implements ExamineeCreatorContract.Presenter {

    private ExamineeCreatorContract.View view;
    private User user;
    private Firebase firebase;


    public ExamineeCreatorPresenter(ExamineeCreatorContract.View view, User user) {
        this.view = view;
        this.user = user;
        firebase = Firebase.getInstance();
    }

    @Override
    public void onAddExaminee() {
        Bundle bundle = view.getExamineeData();

        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(user.getUid())
                .child("examinees")
                .child(bundle.get("name").toString());

        databaseReference.child("age").setValue(bundle.get("age"));
        databaseReference.child("name").setValue(bundle.get("name").toString());
        databaseReference.child("gender").setValue(bundle.get("gender").toString());

        view.navigateToAssessments(bundle);
    }
}
