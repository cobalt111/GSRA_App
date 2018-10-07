package com.timothycox.gsra_app.profile;

class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;

    ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }
}
