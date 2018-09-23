package com.timothycox.gsra_app.main;

import android.content.Context;
import android.content.Intent;

import com.timothycox.gsra_app.BasePresenter;
import com.timothycox.gsra_app.assessment.AssessmentActivity;
import com.timothycox.gsra_app.respondents.RespondentsActivity;

class MainPresenter extends BasePresenter {

    private MainView mainView;

    MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    void onRespondents(Context context) {
        Intent intent = new Intent(context, RespondentsActivity.class);
        context.startActivity(intent);
    }

    void onAssessments(Context context) {
        Intent intent = new Intent(context, AssessmentActivity.class);
        context.startActivity(intent);
    }
}
