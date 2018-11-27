package com.timothycox.gsra_app.post;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.User;
import com.timothycox.gsra_app.util.Firebase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamineeCreatorActivity extends AppCompatActivity implements ExamineeCreatorContract.View {

    private ExamineeCreatorPresenter presenter;
    private ExamineeCreatorNavigator navigator;

    @BindView(R.id.examinee_creator_name_textfield) EditText nameTextField;
    @BindView(R.id.examinee_creator_age_textfield) EditText ageTextField;
    @BindView(R.id.examinee_creator_add_button) Button addExamineeButton;
    @BindView(R.id.examineeCreatorGenderSpinner) Spinner genderSpinner;

    @Override
    @OnClick(R.id.examinee_creator_add_button)
    public void onClickAddExaminee() {
        presenter.onAddExaminee();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinee_creator);
        ButterKnife.bind(this);


        presenter = new ExamineeCreatorPresenter(this);
        navigator = new ExamineeCreatorNavigator(this);
    }

    @Override
    public Bundle createExaminee() {
        //todo implement this stuff in presenter
        User user = (User) getIntent().getBundleExtra("userBundle").getSerializable("user");

        Firebase firebase = Firebase.getInstance();
        DatabaseReference databaseReference = firebase.getDatabaseReference()
                .child("server")
                .child("users")
                .child(user.getUid())
                .child("examinees")
                .child(nameTextField.getText().toString());
        databaseReference.child("age").setValue(Integer.parseInt(ageTextField.getText().toString()));
        databaseReference.child("name").setValue(nameTextField.getText().toString());
        databaseReference.child("gender").setValue(genderSpinner.getSelectedItem().toString());


        Bundle bundle = new Bundle();

        bundle.putString("name", nameTextField.getText().toString());
        bundle.putInt("age", Integer.parseInt(ageTextField.getText().toString()));

        return bundle;
    }

    @Override
    public void navigateToAssessments(Bundle bundle) {
        navigator.itemClicked(ExamineeCreatorNavigator.ASSESSMENTS_ACTIVITY, bundle);
    }

    interface CreatorScreenEvents {
        void itemClicked(final int id, @Nullable Bundle bundle);
    }
}
