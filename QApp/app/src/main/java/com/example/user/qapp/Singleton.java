package com.example.user.qapp;

import com.example.user.qapp.model.Question;

import java.util.List;

/**
 * Created by vishwesh on 05/05/18.
 */

public class Singleton {
    private static Singleton singleton;
    private List<Question> questionList;

    private Singleton() {
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
        this.questionList = questionList;
    }
}
