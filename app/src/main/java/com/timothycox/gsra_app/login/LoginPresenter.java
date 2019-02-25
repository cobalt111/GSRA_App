package com.timothycox.gsra_app.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.IdpResponse;
import com.timothycox.gsra_app.model.User;
import com.timothycox.gsra_app.util.Authentication;

class LoginPresenter implements LoginContract.Presenter {

    //todo remove tag
    private final static String TAG = "MainPresenter";

    private User user;
    private IdpResponse response;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void create() {
        view.showLoginScreenLoadingDialog();
    }

    @Override
    public void onNetworkAvailable() {
//        view.dismissNetworkDisconnectedDialog();
        view.startLogin();
    }

    @Override
    public void onNetworkUnavailable() {
//        view.showNetworkDisconnectedDialog();
    }


    @Override
    public void createNewUser() {

    }

    // todo fix this so presenter doesn't know about intent or idpresponse
    @Override
    public void onSignInAttempt(Intent intent) {
        response = IdpResponse.fromResultIntent(intent);
    }

    @Override
    public void onSignInSuccess() {
        view.dismissLoginScreenLoadingDialog();
        view.showAfterLoginSuccessLoadingDialog();
        user = Authentication.getUser();
        openMain();
        view.dismissAfterLoginSuccessfulLoadingDialog();
    }

    @Override
    public void onSignInFailed() {
        // todo handle failure using idpresponse properly
        Log.d(TAG, String.valueOf(response.getError().getErrorCode()));
        view.showLoginFailedToast();
        view.startLogin();
    }

    @Override
    public void openMain() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        view.navigateToMain(bundle);
    }
}
