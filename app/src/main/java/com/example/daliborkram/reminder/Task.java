package com.example.daliborkram.reminder;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private String comment;

    private int taskColor;

    private RealmList<Date> history;

    public RealmList<Date> getHistory() {
        return history;
    }

    public void setHistory(RealmList<Date> history) {
        this.history = history;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(int taskColor) {
        this.taskColor = taskColor;
    }

    public Task() {
    }
}
