package com.timothycox.gsra_app.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.util.NetworkStateReceiver;

public class LoginActivity extends AppCompatActivity implements LoginContract.View,
        NetworkStateReceiver.NetworkStateReceiverListener {

    private LoginPresenter presenter;
    private LoginNavigator navigator;
    private NetworkStateReceiver networkStateReceiver;
    private AlertDialog networkDisconnectedDialog;
    private ProgressDialog loginSuccessDialog;
    private ProgressDialog loginLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        navigator = new LoginNavigator(this);
        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        networkDisconnectedDialog = new AlertDialog.Builder(this)
                .setTitle("Network Disconnected")
                .setMessage("Your network has been disconnected. Please reconnect and try again")
                .setNeutralButton(android.R.string.yes, null)
                .setIcon(android.R.drawable.ic_dialog_alert).create();
        loginLoadingDialog = new ProgressDialog(this);
        loginLoadingDialog.setMessage("Loading login screen. Please wait...");
        loginSuccessDialog = new ProgressDialog(this);
        loginSuccessDialog.setMessage("Login successful! Loading app...");

        presenter.create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);
    }

    @Override
    public void networkAvailable() {
        presenter.onNetworkAvailable();
    }

    @Override
    public void networkUnavailable() {
        presenter.onNetworkUnavailable();
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
            if (resultCode == RESULT_OK)
                presenter.onSignInSuccess();
            else
                presenter.onSignInFailed();
        }
    }

    @Override
    public void showNetworkDisconnectedDialog() {
        networkDisconnectedDialog.show();
    }

    @Override
    public void showLoginScreenLoadingDialog() {
        loginLoadingDialog.show();
    }

    @Override
    public void showAfterLoginSuccessLoadingDialog() {
        loginSuccessDialog.show();
    }

    @Override
    public void dismissNetworkDisconnectedDialog() {
        networkDisconnectedDialog.dismiss();
    }

    @Override
    public void dismissLoginScreenLoadingDialog() {
        loginLoadingDialog.dismiss();
    }

    @Override
    public void dismissAfterLoginSuccessfulLoadingDialog() {
        loginSuccessDialog.dismiss();
    }

    @Override
    public void showLoginFailedToast() {
        Toast.makeText(this, "Login attempt failed, please try again", Toast.LENGTH_SHORT).show();
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
