package com.timothycox.gsra_app.login;

class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    LoginPresenter(LoginContract.View view) {
        this.view = view;
    }
}
