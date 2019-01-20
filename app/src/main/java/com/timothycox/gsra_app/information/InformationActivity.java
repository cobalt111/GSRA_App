package com.timothycox.gsra_app.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.timothycox.gsra_app.R;

public class InformationActivity extends AppCompatActivity implements InformationContract.View {

    private InformationPresenter presenter;
    private InformationNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        presenter = new InformationPresenter(this);
        navigator = new InformationNavigator(this);
    }

    interface InformationScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }
}
