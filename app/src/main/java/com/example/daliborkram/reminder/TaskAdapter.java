package com.example.daliborkram.reminder;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public class TaskAdapter extends ArrayAdapter<Task> {
    ArrayList<Task> tasks;
    public TaskAdapter(Context context, int resource, ArrayList<Task> dataList) {
        super(context, resource, dataList);
        tasks = dataList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        TextView name = convertView.findViewById(R.id.item_name);
        TextView time = convertView.findViewById(R.id.item_time);
        name.setText(task.getName());
        convertView.setBackgroundColor(task.getTaskColor());
        long timeSpan =  new Date().getTime() - task.getHistory().get(task.getHistory().size() - 1).getTime();
        time.setText(timeToString(timeSpan));
        return convertView;
    }

    private String timeToString(long miliseconds) {
        long s = miliseconds / 1000;
        long min = s / 60;
        s %= 60;
        long h = min  / 60;
        min %= 60;
        long days = h / 24;
        h %= 24;
        String result = "";
        boolean onlyZero = true;
        if (days != 0)
            onlyZero = false;
        if (!onlyZero){
            result += String.valueOf(days);
            if (days == 1)
                result += " day ";
            else
                result += " days ";
        }
        if (h != 0)
            onlyZero = false;
        if (!onlyZero)
            result += String.valueOf(h) + ":";
        if (min != 0)
            onlyZero = false;
        if (!onlyZero)
            result += String.valueOf(min) + ":";
        result += String.valueOf(s);
        return result;

    }
}
