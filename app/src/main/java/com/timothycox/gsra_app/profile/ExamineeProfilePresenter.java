package com.timothycox.gsra_app.profile;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.model.Assessment;
import com.timothycox.gsra_app.model.Examinee;
import com.timothycox.gsra_app.util.Firebase;

import java.util.ArrayList;
import java.util.List;

class ExamineeProfilePresenter implements ExamineeProfileContract.Presenter {

    //todo remove tag
    private String TAG = "ExamineeProfilePresenter";

    private ExamineeProfileContract.View view;
    private Examinee examinee;
    private Firebase firebase;

    ExamineeProfilePresenter(ExamineeProfileContract.View view, Examinee examinee) {
        this.view = view;
        this.examinee = examinee;
        firebase = Firebase.getInstance();
    }

    @Override
    public void create() {
        getTutorialState();
        view.populateUIWithData(examinee);
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(examinee.getCreatorUid())
                .child("examinees")
                .child(examinee.getName())
                .child("assessments");
        firebase.access(true, databaseReference, new Firebase.OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                List<Assessment> assessments = new ArrayList<>();
                Assessment assessment = new Assessment();
                for (DataSnapshot currentAssessment : dataSnapshot.getChildren()) {
                    assessment.setCategory(currentAssessment.child("category").getValue(String.class));
                    assessment.setCompleted(currentAssessment.child("isCompleted").getValue(Boolean.class));
                    assessment.setResult(currentAssessment.child("result").getValue(Integer.class));
                    assessment.setTimestamp(currentAssessment.child("timestamp").getValue(String.class));
                }
                assessments.add(assessment);
                view.setRecyclerViewAdapter(new ExamineeProfileRecyclerViewAdapter(assessments));
            }

            @Override
            public void onFailure(DatabaseError databaseError) {
                //todo handle failure
                Log.d(TAG, databaseError.getMessage());
            }
        });
    }

    // todo test tutorial, retrieval of creatoruid from db
    @Override
    public void getTutorialState() {
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(examinee.getCreatorUid())
                .child("tutorials")
                .child("seenProfile");
        firebase.access(false, databaseReference, new Firebase.OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.getValue(Boolean.class)) view.showTutorial(false);
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
                .child(examinee.getCreatorUid())
                .child("tutorials")
                .child("seenProfile");
        databaseReference.setValue(true);
    }

    @Override
    public void retryTutorial() {
        view.showTutorial(true);
    }
}
