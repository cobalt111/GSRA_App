package com.timothycox.gsra_app.respondents;

class RespondentsPresenter implements RespondentsContract.Presenter {

    private RespondentsContract.View view;

    RespondentsPresenter(RespondentsContract.View view) {
        this.view = view;
    }
}
