package com.timothycox.gsra_app.creator;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.model.User;
import com.timothycox.gsra_app.util.Firebase;

class ExamineeCreatorPresenter implements ExamineeCreatorContract.Presenter {

    //todo remove tag
    private String TAG = "ExamineeCreatorPresenter";

    private ExamineeCreatorContract.View view;
    private User user;
    private Firebase firebase;
    private boolean tutorialSeen;


    public ExamineeCreatorPresenter(ExamineeCreatorContract.View view, User user) {
        this.view = view;
        this.user = user;
        firebase = Firebase.getInstance();
    }

    @Override
    public void create() {
        getTutorialState();
    }

    @Override
    public void getTutorialState() {
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(user.getUid())
                .child("tutorials")
                .child("seenCreator");
        firebase.access(false, databaseReference, new Firebase.OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                tutorialSeen = dataSnapshot.getValue(Boolean.class);
                if (!tutorialSeen) view.showTutorial(false);
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
        tutorialSeen = true;
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(user.getUid())
                .child("tutorials")
                .child("seenCreator");
        databaseReference.setValue(tutorialSeen);
    }

    @Override
    public void retryTutorial() {
        view.showTutorial(true);
    }

    @Override
    public void onAddExaminee() {
        Bundle bundle = view.saveEnteredExamineeData();

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
