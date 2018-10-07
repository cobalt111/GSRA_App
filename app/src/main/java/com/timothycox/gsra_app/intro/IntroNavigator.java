package com.timothycox.gsra_app.intro;

import android.content.Context;

class IntroNavigator implements IntroActivity.IntroScreenEvents {

    private Context context;

    IntroNavigator(Context context) {
        this.context = context;
    }

    @Override
    public void itemClicked(int id) {

    }
}
