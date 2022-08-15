package com.creeds_code.padi_20;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Schedule {

    private String title;
    private String time;
    private String text;


    public Schedule(String title, String time, String text) {
        this.title = title;
        this.time = time;
        this.text = text;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
