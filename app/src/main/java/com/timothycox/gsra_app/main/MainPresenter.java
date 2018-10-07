package com.timothycox.gsra_app.main;

class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void create() {
        view.startLogin();
    }

    @Override
    public void onRespondents() {
        view.navigateToRespondents();
    }

    @Override
    public void onAssessments() {
        view.navigateToAssessments();
    }

    @Override
    public void onSignInAttempt() {

    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignInFailed() {

    }
}
