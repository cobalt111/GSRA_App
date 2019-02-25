package com.timothycox.gsra_app.examinees;

interface ExamineesContract {
    interface View {
        void showTutorial(final boolean retry);
        // todo add network connectivity check for presenter to use
        void onClickAddExaminee();
        void navigateToExamineeCreator();
        void setRecyclerViewAdapter(ExamineesRecyclerViewAdapter adapter);
    }

    interface Presenter {
        void create();
        void onAddExaminee();
        void getTutorialState();
        void onTutorialSeen();
        void retryTutorial();
    }
}
