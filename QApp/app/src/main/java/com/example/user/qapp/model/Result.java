package com.example.user.qapp.model;

/**
 * Created by user on 10/4/18.
 */

public class Result {
    String message;
    int success;
    String output;
    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }



    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
