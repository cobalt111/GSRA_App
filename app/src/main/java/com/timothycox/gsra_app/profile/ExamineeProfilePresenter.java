package com.timothycox.gsra_app.profile;

import com.timothycox.gsra_app.model.Examinee;

class ExamineeProfilePresenter implements ExamineeProfileContract.Presenter {

    private ExamineeProfileContract.View view;
    private Examinee examinee;

    ExamineeProfilePresenter(ExamineeProfileContract.View view, Examinee examinee) {
        this.view = view;
        this.examinee = examinee;
    }

    @Override
    public void create() {
        view.populateUIWithData(examinee);
    }
}
