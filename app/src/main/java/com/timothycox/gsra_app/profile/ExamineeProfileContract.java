package com.timothycox.gsra_app.profile;

import com.timothycox.gsra_app.model.Examinee;

interface ExamineeProfileContract {
    interface View {
        void populateUIWithData(Examinee examinee);
    }

    interface Presenter {
        void create();
    }
}
