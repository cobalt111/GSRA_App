package com.timothycox.gsra_app.examinees;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.timothycox.gsra_app.post.ExamineeCreatorActivity;

class ExamineesNavigator implements ExamineesActivity.ExamineesScreenEvents {

    private Context context;
    static final int EXAMINEE_CREATOR_ACTIVITY = 1;

    ExamineesNavigator(Context context) {
        this.context = context;
    }

    @Override
    public void itemClicked(final int id, @Nullable Bundle bundle) {
        switch (id) {
            case EXAMINEE_CREATOR_ACTIVITY: {
                Intent intent = new Intent(context, ExamineeCreatorActivity.class);
                if (bundle != null) intent.putExtra("bundle", bundle);
                context.startActivity(intent);
                break;
            }
        }
    }
}
