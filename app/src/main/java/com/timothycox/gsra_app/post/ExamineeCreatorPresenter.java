package com.timothycox.gsra_app.post;

import android.os.Bundle;

class ExamineeCreatorPresenter implements ExamineeCreatorContract.Presenter {

    private ExamineeCreatorContract.View view;

    public ExamineeCreatorPresenter(ExamineeCreatorContract.View view) {
        this.view = view;
    }

    @Override
    public void onAddExaminee() {
        Bundle bundle = view.createExaminee();
        view.navigateToAssessments(bundle);
    }
}
