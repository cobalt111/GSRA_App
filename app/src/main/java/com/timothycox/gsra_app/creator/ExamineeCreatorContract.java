package com.timothycox.gsra_app.creator;

import android.os.Bundle;

interface ExamineeCreatorContract {
    interface View {
        void onClickAddExaminee();
        Bundle saveEnteredExamineeData();
        void showTutorial(final boolean retry);
        void navigateToAssessments(Bundle bundle);
    }

    interface Presenter {
        void create();
        void onAddExaminee();
        void getTutorialState();
        void onTutorialSeen();
        void retryTutorial();
    }
}
