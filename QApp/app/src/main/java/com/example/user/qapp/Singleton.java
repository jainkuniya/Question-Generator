package com.example.user.qapp;

import android.util.SparseArray;

import com.example.user.qapp.model.Question;

import java.util.List;

/**
 * Created by vishwesh on 05/05/18.
 */

public class Singleton {
    private static Singleton singleton;
    private static List<Question> questionList;
    private SparseArray<String> answers;

    private Singleton() {
        answers = new SparseArray<>();
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        Singleton.questionList = questionList;
    }

    public void updateAnswer(int position, String answer) {
        answers.put(position, answer);
    }

    public String getAnswer(int position) {
        return answers.get(position) == null ? "" : answers.get(position);
    }
}
