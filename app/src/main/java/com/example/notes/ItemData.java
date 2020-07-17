package com.example.notes;

import android.graphics.drawable.Drawable;

public class ItemData {


    private String title;
    private String note;
    private String deadLine;

    public ItemData(String title, String note, String deadLine) {
        this.title = title;
        this.note = note;
        this.deadLine = deadLine;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public String getDeadLine() {
        return deadLine;
    }
}
