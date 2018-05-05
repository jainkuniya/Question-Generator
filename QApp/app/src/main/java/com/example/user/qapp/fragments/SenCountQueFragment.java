package com.example.user.qapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.qapp.R;
import com.example.user.qapp.Singleton;
import com.example.user.qapp.model.Question;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SenCountQueFragment extends Fragment {

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    @BindView(R.id.etAnswer)
    EditText etAnswer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_sentence_count, container, false);
        ButterKnife.bind(this, rootView);
        setData();
        return rootView;
    }

    private void setData() {
        int position = getArguments().getInt("position", 0);
        Question question = Singleton.getInstance().getQuestionList().get(position);
        tvQuestion.setText(question.getQuestion());
    }
}
