package com.example.daliborkram.reminder;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {
    @PrimaryKey int id;
    String name;
    String comment;
    Color color;
    ArrayList<Date> finishedTasks;

    public ArrayList<Date> getFinishedTasks() {
        return finishedTasks;
    }

    public void addFinishedTask(Date date) {
        finishedTasks.add(date);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Task(String name, String comment, Color color) {

        this.name = name;
        this.comment = comment;
        this.color = color;
    }
}
