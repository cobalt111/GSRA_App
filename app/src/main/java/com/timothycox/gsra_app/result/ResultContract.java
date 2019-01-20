package com.timothycox.gsra_app.result;

interface ResultContract {
    interface View {
        void showTutorial(final boolean retry);
    }

    interface Presenter {
        void create();
        void getTutorialState();
        void onTutorialSeen();
        void retryTutorial();
    }
}
