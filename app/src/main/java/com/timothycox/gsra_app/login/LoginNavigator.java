package com.timothycox.gsra_app.login;

import android.content.Context;

class LoginNavigator implements LoginActivity.LoginScreenEvents {

    private Context context;

    LoginNavigator(Context context) {
        this.context = context;
    }

    @Override
    public void itemClicked(final int id) {

    }
}
