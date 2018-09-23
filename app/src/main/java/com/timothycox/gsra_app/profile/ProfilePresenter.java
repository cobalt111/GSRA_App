package com.timothycox.gsra_app.profile;

import com.timothycox.gsra_app.BasePresenter;

class ProfilePresenter extends BasePresenter {

    private ProfileView profileView;

    ProfilePresenter(ProfileView profileView) {
        this.profileView = profileView;
    }
}
