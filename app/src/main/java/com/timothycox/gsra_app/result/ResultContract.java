package com.timothycox.gsra_app.result;

import android.os.Bundle;

import com.timothycox.gsra_app.model.Assessment;
import com.timothycox.gsra_app.model.Examinee;

interface ResultContract {
    interface View {
        void showTutorial(final boolean retry);
        void populatedUIWithData(Examinee examinee, Assessment assessment);
        void onClickNewTest();
        void navigateToNewTest(Bundle bundle);
        void onClickLearnMore();
        void navigateToLearnMore(Bundle bundle);
    }

    interface Presenter {
        void create();
        void onNewTest();
        void onLearnMore();
        void getTutorialState();
        void onTutorialSeen();
        void retryTutorial();
    }
}
