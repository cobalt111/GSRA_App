package com.timothycox.gsra_app.profile;


import com.timothycox.gsra_app.model.Examinee;

interface ExamineeProfileContract {
    interface View {
        void showTutorial(final boolean retry);
        void populateUIWithData(Examinee examinee);
        void setRecyclerViewAdapter(ExamineeProfileRecyclerViewAdapter adapter);
    }

    interface Presenter {
        void create();
        void getTutorialState();
        void onTutorialSeen();
        void retryTutorial();    }
}
