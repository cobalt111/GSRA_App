package com.timothycox.gsra_app.assessment;

class AssessmentPresenter implements AssessmentContract.Presenter {

    private AssessmentContract.View view;

    AssessmentPresenter(AssessmentContract.View view) {
        this.view = view;
    }
}
