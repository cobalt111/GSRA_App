package com.timothycox.gsra_app.examinees;

class ExamineesPresenter implements ExamineesContract.Presenter {

    private ExamineesContract.View view;

    ExamineesPresenter(ExamineesContract.View view) {
        this.view = view;
    }
}
