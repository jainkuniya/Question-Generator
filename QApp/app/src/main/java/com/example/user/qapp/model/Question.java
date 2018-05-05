package com.example.user.qapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {

    @SerializedName("options")
    @Expose
    private List<String> options = null;
    @SerializedName("comprehension")
    @Expose
    private String comprehension;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private Integer answer;
    @SerializedName("question_type")
    @Expose
    private String questionType;
    @SerializedName("explanation")
    @Expose
    private String explanation;

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getComprehension() {
        return comprehension;
    }

    public void setComprehension(String comprehension) {
        this.comprehension = comprehension;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
