package com.timothycox.gsra_app.assessment;

import android.content.Context;

class AssessmentNavigator implements AssessmentActivity.AssessmentScreenEvents {

    private Context context;

    public AssessmentNavigator(Context context) {
        this.context = context;
    }

    @Override
    public void itemClicked(int id) {

    }
}
