package com.example.daliborkram.reminder;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {
    @PrimaryKey int id;
    private String name;
    private String comment;
    //Color color;
    //ArrayList<Date> finishedTasks;

    String colorHex;

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
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
    /*
        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    */
    public Task() {
    }
}
