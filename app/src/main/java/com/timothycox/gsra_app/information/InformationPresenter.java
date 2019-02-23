package com.timothycox.gsra_app.information;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.model.Examinee;
import com.timothycox.gsra_app.util.Firebase;

class InformationPresenter implements InformationContract.Presenter {

    //todo remove tag
    private String TAG = "InformationPresenter";

    private InformationContract.View view;
    private Examinee examinee;
    private Firebase firebase;

    public InformationPresenter(InformationContract.View view, Examinee examinee) {
        this.view = view;
        this.examinee = examinee;
        firebase = Firebase.getInstance();
    }

    @Override
    public void create() {

    }

    @Override
    public void getTutorialState() {
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(examinee.getCreatorUid())
                .child("tutorials")
                .child("seenInformation");
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
        //todo finish creating tutorial
    }

    @Override
    public void retryTutorial() {
        // todo finish creating tutorial
    }
}
