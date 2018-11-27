package com.timothycox.gsra_app.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.firebase.ui.auth.AuthUI;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.assessment.list.AssessmentListActivity;
import com.timothycox.gsra_app.examinees.ExamineesActivity;
import com.timothycox.gsra_app.util.Authentication;

class MainNavigator implements MainActivity.MainScreenEvents {

    static final int SIGN_IN = 123;

    static final int EXAMINEES_ACTIVITY = 2;
    static final int ASSESSMENT_LIST_ACTIVITY = 3;
    private Context context;

    MainNavigator(Context context) {
        this.context = context;
    }

    Intent createAuthInstance() {
        return AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.logo)
                .setAvailableProviders(Authentication.getAuthProviders())
                .build();
    }


    @Override
    public void itemClicked(final int id, @Nullable Bundle bundle) {
        switch (id) {
            case EXAMINEES_ACTIVITY: {
                Intent intent = new Intent(context, ExamineesActivity.class);
                if (bundle != null) intent.putExtra("userBundle", bundle);
                context.startActivity(intent);
                break;
            }
            case ASSESSMENT_LIST_ACTIVITY: {
                Intent intent = new Intent(context, AssessmentListActivity.class);
                if (bundle != null) intent.putExtra("userBundle", bundle);
                context.startActivity(intent);
                break;
            }
        }
    }
}
