package com.timothycox.gsra_app.assessment.list;

interface AssessmentListContract {
    interface View {
        void setRecyclerViewAdapter(AssessmentRecyclerViewAdapter adapter);
    }

    interface Presenter {
        void create();
    }
}
