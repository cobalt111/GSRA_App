package com.timothycox.gsra_app.profile;

import android.content.Context;

class ProfileNavigator implements ProfileActivity.ProfileClickEvents {

    private Context context;

    ProfileNavigator(Context context) {
        this.context = context;
    }

    @Override
    public void itemClicked(int id) {

    }
}
