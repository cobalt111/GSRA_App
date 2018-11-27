package com.timothycox.gsra_app.examinees;

interface ExamineesContract {
    interface View {
        void onClickAddExaminee();

        void navigateToExamineeCreator();

        void setRecyclerViewAdapter(ExamineesRecyclerViewAdapter adapter);
    }

    interface Presenter {
        void onAddExaminee();

        void create();
    }
}
