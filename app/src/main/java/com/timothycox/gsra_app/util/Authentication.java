package com.timothycox.gsra_app.util;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.timothycox.gsra_app.model.User;

import java.util.Arrays;
import java.util.List;

public class Authentication {

    private IdpResponse response;

    public Authentication(IdpResponse response) {
        this.response = response;
    }

    public static List<AuthUI.IdpConfig> getAuthProviders() {
        return Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );
    }

    public static User getUser() {
        // todo check for null properly
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return new User(firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getUid());
    }

//    public void setAuthToPersist() {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.
//    }


}
