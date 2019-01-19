package com.timothycox.gsra_app.main;

import android.os.Bundle;

interface MainContract {
    interface View {
//        void startLogin();

        void onClickTests();

        void navigateToTests(Bundle bundle);

        void onClickAssessments();

        void navigateToAssessments(Bundle bundle);
    }

    interface Presenter {
        void create();

        void onTests();

        void onAssessments();

//        void onSignInAttempt(Intent intent);

//        void onSignInSuccess();

//        void onSignInFailed();
    }
}
