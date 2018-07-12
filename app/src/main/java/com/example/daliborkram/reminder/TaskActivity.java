package com.example.daliborkram.reminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Intent intent = getIntent();
        int position = intent.getIntExtra(MainActivity.TASK_POSITION, -1);
        Task task = MainActivity.tasks.get(position);
        setTitle(task.getName());
        TextView view = findViewById(R.id.detail_task_comment);
        view.setText(task.getComment());
    }
}
