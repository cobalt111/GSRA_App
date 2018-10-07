package com.timothycox.gsra_app.result;

class ResultPresenter implements ResultContract.Presenter {

    private ResultContract.View view;

    ResultPresenter(ResultContract.View view) {
        this.view = view;
    }
}
