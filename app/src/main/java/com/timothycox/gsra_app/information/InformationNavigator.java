package com.timothycox.gsra_app.information;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

class InformationNavigator implements InformationActivity.InformationScreenEvents {

    private Context context;

    public InformationNavigator(Context context) {
        this.context = context;
    }

    @Override
    public void itemClicked(final int id, @Nullable Bundle bundle) {

    }
}
