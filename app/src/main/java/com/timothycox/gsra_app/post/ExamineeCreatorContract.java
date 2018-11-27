package com.timothycox.gsra_app.post;

import android.os.Bundle;

interface ExamineeCreatorContract {
    interface View {
        void onClickAddExaminee();

        Bundle getExamineeData();

        void navigateToAssessments(Bundle bundle);
    }

    interface Presenter {
        void onAddExaminee();
    }
}
