package com.example.user.qapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.user.qapp.R;
import com.example.user.qapp.activities.QuestionsActivity;
import com.example.user.qapp.model.Question;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MCQFragment extends Fragment {

    @BindView(R.id.rg)
    RadioGroup radioGroup;
    @BindView(R.id.tv_correct)
    TextView correct;
    @BindView(R.id.tv_explain)
    TextView explain;

    ArrayList<Question> questions;
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
        Bundle b=getArguments();
        questions=(ArrayList<Question>)b.getSerializable("question");

        correct.setVisibility(View.INVISIBLE);
        explain.setVisibility(View.INVISIBLE);


        if(radioGroup.getCheckedRadioButtonId() != -1){
            ((QuestionsActivity) getActivity()).changeButton();
        }

        return rootView;

    }


}
