package com.timothycox.gsra_app.result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.timothycox.gsra_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity implements ResultContract.View {

    private ResultPresenter presenter;

    @BindView(R.id.resultBoyFace) ImageView boyFace;
    @BindView(R.id.resultGirlFace) ImageView girlFace;
    @BindView(R.id.resultNeutralFace) ImageView neutralFace;
    @BindView(R.id.resultAgeText) TextView ageText;
    @BindView(R.id.resultAgeLabelText) TextView ageTextLabel;
    @BindView(R.id.resultDateText) TextView dateText;
    @BindView(R.id.resultDateLabelText) TextView dateLabelText;
    @BindView(R.id.resultNameText) TextView nameText;
    @BindView(R.id.resultExplanationText) TextView explanationText;
    @BindView(R.id.resultScoreText) TextView scoreText;
    @BindView(R.id.resultScoreLabelText) TextView scoreLabelText;
    @BindView(R.id.resultLearnMoreButton) Button learnMoreButton;
    @BindView(R.id.resultTakeNewTestButton) Button takeNewTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        presenter = new ResultPresenter(this);
    }

    interface ResultScreenEvents {
        void itemClicked(final int id);
    }
}
