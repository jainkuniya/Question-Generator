package com.example.user.qapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.user.qapp.R;
import com.example.user.qapp.activities.QuestionsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TrueFalseFragment extends Fragment {

    @BindView(R.id.rg)
    RadioGroup radioGroup;
    @BindView(R.id.tv_correct)
    TextView correct;
    @BindView(R.id.tv_explain)
    TextView explain;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_true_false, container, false);
        ButterKnife.bind(this, rootView);
        correct.setVisibility(View.INVISIBLE);
        explain.setVisibility(View.INVISIBLE);

        return rootView;
    }
}
