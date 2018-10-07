package com.timothycox.gsra_app.intro;

class IntroPresenter implements IntroContract.Presenter {

    private IntroContract.View view;

    IntroPresenter(IntroContract.View view) {
        this.view = view;
    }
}
