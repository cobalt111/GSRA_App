package com.timothycox.gsra_app.main;

import android.os.Bundle;

import com.firebase.ui.auth.IdpResponse;
import com.timothycox.gsra_app.model.User;

class MainPresenter implements MainContract.Presenter {

    // todo remove tag
    private final static String TAG = "MainPresenter";

    private MainContract.View view;
    private User user;
    private IdpResponse response;

    MainPresenter(MainContract.View view, Bundle userBundle) {
        this.view = view;
        user = (User) userBundle.getSerializable("user");
    }

    @Override
    public void create() {
//        view.startLogin();
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

//    // todo fix this so presenter doesn't know about intent or idpresponse
//    @Override
//    public void onSignInAttempt(Intent intent) {
//        response = IdpResponse.fromResultIntent(intent);
//    }
//
//    @Override
//    public void onSignInSuccess() {
//        user = Authentication.getUser();
//    }
//
//    @Override
//    public void onSignInFailed() {
//        // todo handle failure using idpresponse properly
//        Log.d(TAG, String.valueOf(response.getError().getErrorCode()));
//    }
}
