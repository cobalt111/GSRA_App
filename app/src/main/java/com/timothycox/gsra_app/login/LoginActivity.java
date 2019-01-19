package com.timothycox.gsra_app.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.timothycox.gsra_app.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;
    private LoginNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        navigator = new LoginNavigator(this);

        ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "",
                "Loading. Please wait...", true);

        presenter.create();
    }

    @Override
    public void startLogin() {
        startActivityForResult(navigator.createAuthInstance(), LoginNavigator.SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LoginNavigator.SIGN_IN) {
            presenter.onSignInAttempt(data);
            if (resultCode == RESULT_OK) {
                presenter.onSignInSuccess();
                presenter.onMain();
            }
            else
                presenter.onSignInFailed();
        }
    }

    @Override
    public void showLoginFailedToast() {
        Toast loginFailedToast = new Toast(this);
        loginFailedToast.makeText(this, "Login attempt failed, please try again", Toast.LENGTH_SHORT);
        loginFailedToast.show();
    }

    interface LoginScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }

    @Override
    public void navigateToMain(@Nullable Bundle bundle) {
        navigator.itemClicked(LoginNavigator.MAIN_ACTIVITY, bundle);
        finish();
    }
}