package com.timothycox.gsra_app.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.timothycox.gsra_app.assessment.AssessmentActivity;
import com.timothycox.gsra_app.information.InformationActivity;

class ResultNavigator implements ResultActivity.ResultScreenEvents {

    static final int ASSESSMENT_ACTIVITY = 1;
    static final int INFORMATION_ACTIVITY = 2;
    private Context context;

    public ResultNavigator(Context context) {
        this.context = context;
    }

    @Override
    public void itemClicked(final int id, @Nullable Bundle bundle) {
        switch (id) {
            case ASSESSMENT_ACTIVITY: {
                Intent intent = new Intent(context, AssessmentActivity.class);
                if (bundle != null) intent.putExtra("userBundle", bundle);
                context.startActivity(intent);
                break;
            }
            case INFORMATION_ACTIVITY: {
                Intent intent = new Intent(context, InformationActivity.class);
                if (bundle != null) intent.putExtra("userBundle", bundle);
                context.startActivity(intent);
                break;
            }
        }
    }
}
