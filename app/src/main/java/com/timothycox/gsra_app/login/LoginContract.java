package com.timothycox.gsra_app.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

interface LoginContract {
    interface View {
        void startLogin();
        void navigateToMain(@Nullable Bundle bundle);
        void showLoginFailedToast();
        void showLoginScreenLoadingDialog();
        void showAfterLoginSuccessLoadingDialog();
    }
    interface Presenter {
        void create();
        void onMain();
        void onSignInAttempt(Intent intent);
        void onSignInSuccess();
        void onSignInFailed();
    }
}
