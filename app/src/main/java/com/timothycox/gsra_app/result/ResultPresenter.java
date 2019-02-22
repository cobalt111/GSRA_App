package com.timothycox.gsra_app.result;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.model.Assessment;
import com.timothycox.gsra_app.model.Examinee;
import com.timothycox.gsra_app.util.Firebase;

class ResultPresenter implements ResultContract.Presenter {

    //todo remove tag
    private String TAG = "ResultsPresenter";

    private ResultContract.View view;
    private Examinee examinee;
    private Assessment assessment;
    private Firebase firebase;
    private boolean tutorialSeen;

    ResultPresenter(ResultContract.View view, Examinee examinee, Assessment assessment) {
        this.view = view;
        this.examinee = examinee;
        this.assessment = assessment;
        firebase = Firebase.getInstance();
    }

    @Override
    public void create() {
        getTutorialState();
        view.populatedUIWithData(examinee, assessment);
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

    @Override
    public void onNewTest() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedExaminee", examinee);
        bundle.putSerializable("assessment", assessment);
        view.navigateToNewTest(bundle);
    }

    @Override
    public void onLearnMore() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedExaminee", examinee);
        bundle.putSerializable("assessment", assessment);
        view.navigateToLearnMore(bundle);
    }
}
