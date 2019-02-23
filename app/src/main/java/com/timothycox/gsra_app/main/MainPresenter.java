package com.timothycox.gsra_app.main;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.model.User;
import com.timothycox.gsra_app.util.Firebase;

class MainPresenter implements MainContract.Presenter {

    //todo remove tag
    private String TAG = "MainPresenter";
    private MainContract.View view;
    private User user;
    private Firebase firebase;

    MainPresenter(MainContract.View view, Bundle userBundle) {
        this.view = view;
        user = (User) userBundle.getSerializable("user");
        firebase = Firebase.getInstance();
    }

    @Override
    public void create() {
        getTutorialState();
    }

    @Override
    public void start() {
    }

    @Override
    public void getTutorialState() {
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(user.getUid())
                .child("tutorials")
                .child("seenMain");
        firebase.access(false, databaseReference, new Firebase.OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                try {
                    if (!dataSnapshot.getValue(Boolean.class)) view.showTutorial(false);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(DatabaseError databaseError) {
                //todo handle failure
                Log.d(TAG, databaseError.getMessage());
            }
        });
    }

    @Override
    public void onTutorialSeen() {
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(user.getUid())
                .child("tutorials")
                .child("seenMain");
        databaseReference.setValue(true);
    }

    @Override
    public void retryTutorial() {
        view.showTutorial(true);
    }

    @Override
    public void onTests() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        view.navigateToTests(bundle);
    }

    @Override
    public void onAssessments() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        view.navigateToAssessments(bundle);
    }
}
