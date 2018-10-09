package com.timothycox.gsra_app.examinees;

interface ExamineesContract {
    interface View {
        void onClickAddExaminee();

        void navigateToExamineeCreator();
    }

    interface Presenter {
        void onAddExaminee();
    }
}
