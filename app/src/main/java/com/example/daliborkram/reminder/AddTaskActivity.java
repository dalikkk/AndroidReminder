package com.example.daliborkram.reminder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import yuku.ambilwarna.AmbilWarnaDialog;

public class AddTaskActivity extends AppCompatActivity {

    AddTaskActivity context;
    int taskBackgroundColor = R.color.colorPrimary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        context = this;
        ImageView imageView = findViewById(R.id.add_activity_add_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = findViewById(R.id.add_task_name);
                EditText comment = findViewById(R.id.add_task_comment);
                if (name.getText().toString().equals("")) {
                    Toast.makeText(context, "Please set name", Toast.LENGTH_LONG).show();
                } else {
                    MainActivity.increaseMaxId();
                    Realm realm = MainActivity.realm;
                    realm.beginTransaction();
                    Task task = MainActivity.realm.createObject(Task.class, MainActivity.getMaxId());
                    task.setName(name.getText().toString());
                    task.setComment(comment.getText().toString());
                    task.setTaskColor(taskBackgroundColor);
                    RealmList history = new RealmList<Date>();
                    history.add(new Date());
                    task.setHistory(history);
                    RealmList<String> historyComments = new RealmList<String>();
                    historyComments.add(comment.getText().toString());
                    task.setHistoryComments(historyComments);
                    realm.commitTransaction();
                    MainActivity.tasks.add(task);
                    MainActivity.adapter.notifyDataSetChanged();
                    context.finish();
                }
            }
        });
        Button button = findViewById(R.id.add_chose_color);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(context, taskBackgroundColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {

                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        taskBackgroundColor = color;
                    }
                });
                colorPicker.show();
            }
        });
    }
}
