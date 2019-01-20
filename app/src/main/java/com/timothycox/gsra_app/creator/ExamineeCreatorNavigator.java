package com.timothycox.gsra_app.creator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.timothycox.gsra_app.assessment.AssessmentActivity;

class ExamineeCreatorNavigator implements ExamineeCreatorActivity.CreatorScreenEvents {

    private Context context;
    static final int ASSESSMENTS_ACTIVITY = 1;

    public ExamineeCreatorNavigator(Context context) {
        this.context = context;
    }

    @Override
    public void itemClicked(final int id, @Nullable Bundle bundle) {
        switch (id) {
            case ASSESSMENTS_ACTIVITY: {
                Intent intent = new Intent(context, AssessmentActivity.class);
                if (bundle != null) intent.putExtra("newExaminee", bundle);
                context.startActivity(intent);
                break;
            }

        }
    }
}
