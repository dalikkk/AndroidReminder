package com.example.daliborkram.reminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task task = new Task();
        task.setName("Go shopping");
        tasks.add(task);
        task = new Task();
        task.setName("Say hello");
        tasks.add(task);
        task = new Task();
        task.setName("Think as BFU");
        tasks.add(task);
        task = new Task();
        task.setName("Think as programmer");
        tasks.add(task);
        TaskAdapter adapter = new TaskAdapter(this, R.id.task_item, tasks);
        GridView view = findViewById(R.id.grid_view_tasks);
        view.setAdapter(adapter);
    }
}
