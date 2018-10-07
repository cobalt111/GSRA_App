package com.timothycox.gsra_app.main;

interface MainContract {
    interface View {
        void startLogin();

        void onClickRespondents();

        void navigateToRespondents();

        void onClickAssessments();

        void navigateToAssessments();
    }

    interface Presenter {
        void create();

        void onRespondents();

        void onAssessments();

        void onSignInAttempt();

        void onSignInSuccess();

        void onSignInFailed();
    }
}
