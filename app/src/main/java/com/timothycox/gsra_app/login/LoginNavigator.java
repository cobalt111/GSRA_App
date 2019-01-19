package com.timothycox.gsra_app.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.firebase.ui.auth.AuthUI;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.main.MainActivity;
import com.timothycox.gsra_app.util.Authentication;

class LoginNavigator implements LoginActivity.LoginScreenEvents {

    static final int SIGN_IN = 123;

    static final int MAIN_ACTIVITY = 1;

    private Context context;

    public LoginNavigator(Context context) {
        this.context = context;
    }

    Intent createAuthInstance() {
        return AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.logo)
                .setAvailableProviders(Authentication.getAuthProviders())
                .build();
    }

    @Override
    public void itemClicked(int id, @Nullable Bundle bundle) {
        switch (id) {
            case MAIN_ACTIVITY: {
                Intent intent = new Intent(context, MainActivity.class);
                if (bundle != null) intent.putExtra("userBundle", bundle);
                context.startActivity(intent);
                break;
            }
        }
    }
}
