package com.timothycox.gsra_app.result;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.model.Examinee;
import com.timothycox.gsra_app.util.Firebase;

class ResultPresenter implements ResultContract.Presenter {

    //todo remove tag
    private String TAG = "ResultsPresenter";

    private ResultContract.View view;
    private Examinee examinee;
    private Firebase firebase;
    private boolean tutorialSeen;

    ResultPresenter(ResultContract.View view, Examinee examinee) {
        this.view = view;
        this.examinee = examinee;
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
                .child(examinee.getCreatorUid())
                .child("tutorials")
                .child("seenResult");
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
                .child(examinee.getCreatorUid())
                .child("tutorials")
                .child("seenResult");
        databaseReference.setValue(tutorialSeen);
    }

    @Override
    public void retryTutorial() {
        view.showTutorial(true);
    }
}
