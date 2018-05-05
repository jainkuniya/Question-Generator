package com.example.user.qapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.user.qapp.R;
import com.example.user.qapp.Singleton;
import com.example.user.qapp.model.Question;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MCQFragment extends Fragment {

    @BindView(R.id.rg)
    RadioGroup radioGroup;

    @BindView(R.id.rbOptionA)
    RadioButton rbOptionA;

    @BindView(R.id.rbOptionB)
    RadioButton rbOptionB;

    @BindView(R.id.rbOptionC)
    RadioButton rbOptionC;

    @BindView(R.id.rbOptionD)
    RadioButton rbOptionD;

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    @BindView(R.id.tvComprehension)
    TextView tvComprehension;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_mcq, container, false);
        ButterKnife.bind(this, rootView);

        setData();
        return rootView;
    }

    private void setData() {
        final int position = getArguments().getInt("position", 0);
        Question question = Singleton.getInstance().getQuestionList().get(position);
        tvQuestion.setText(question.getQuestion());
        tvComprehension.setText(question.getComprehension());

        List<String> options = question.getOptions();

        for (int i = 0; i < options.size(); i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setText(options.get(i));
            radioGroup.getChildAt(i).setTag(String.valueOf(i));
        }

        if (options.size() == 2) {
            // true false type
            rbOptionC.setVisibility(View.GONE);
            rbOptionD.setVisibility(View.GONE);
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    Singleton.getInstance().updateAnswer(position, (String) checkedRadioButton.getTag());
                }
            }
        });
    }


}
