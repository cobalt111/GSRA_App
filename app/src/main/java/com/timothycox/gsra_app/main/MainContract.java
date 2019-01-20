package com.timothycox.gsra_app.main;

import android.os.Bundle;

interface MainContract {
    interface View {
        void showTutorial(final boolean retry);
        void onClickTests();
        void navigateToTests(Bundle bundle);
        void onClickAssessments();
        void navigateToAssessments(Bundle bundle);
    }

    interface Presenter {
        void create();
        void start();
        void getTutorialState();
        void onTutorialSeen();
        void retryTutorial();
        void onTests();
        void onAssessments();
    }
}
