package com.example.daliborkram.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(Context context, int resource, ArrayList<Task> dataList) {
        super(context, resource, dataList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        TextView name = convertView.findViewById(R.id.item_name);
        TextView time = convertView.findViewById(R.id.item_time);

    }

}
