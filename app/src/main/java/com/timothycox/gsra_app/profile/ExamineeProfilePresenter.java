package com.timothycox.gsra_app.profile;

class ExamineeProfilePresenter implements ExamineeProfileContract.Presenter {

    private ExamineeProfileContract.View view;

    ExamineeProfilePresenter(ExamineeProfileContract.View view) {
        this.view = view;
    }
}
