package com.manjenko.testproject.pojo;

public class ListCreateRequest {
    private String text;
    private boolean isDone;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
