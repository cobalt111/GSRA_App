package com.timothycox.gsra_app.information;

class InformationPresenter implements InformationContract.Presenter {

    private InformationContract.View view;

    public InformationPresenter(InformationContract.View view) {
        this.view = view;
    }
}
