package com.timothycox.gsra_app.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

interface LoginContract {
    interface View {
        void startLogin();
        void navigateToMain(@Nullable Bundle bundle);
//        void showNetworkDisconnectedDialog();
//        void dismissNetworkDisconnectedDialog();
        void showLoginFailedToast();
        void showLoginScreenLoadingDialog();
        void dismissLoginScreenLoadingDialog();
        void showAfterLoginSuccessLoadingDialog();
        void dismissAfterLoginSuccessfulLoadingDialog();
    }
    interface Presenter {
        void create();
        void createNewUser();
        void openMain();
        void onNetworkAvailable();
        void onNetworkUnavailable();
        void onSignInAttempt(Intent intent);
        void onSignInSuccess();
        void onSignInFailed();
    }
}
