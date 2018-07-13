package com.example.daliborkram.reminder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import yuku.ambilwarna.AmbilWarnaDialog;

public class TaskActivity extends AppCompatActivity {

    private Task task;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        context = this;
        Intent intent = getIntent();
        int position = intent.getIntExtra(MainActivity.TASK_POSITION, -1);
        task = MainActivity.tasks.get(position);
        setTitle(task.getName());
        final TextView comment = findViewById(R.id.detail_task_comment);
        comment.setText(task.getComment());
        ImageView delete = findViewById(R.id.task_delete);
        ImageView save = findViewById(R.id.task_save);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm = MainActivity.realm;
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Task> toDelete = realm.where(Task.class).equalTo("id", task.getId()).findAll();
                        toDelete.deleteAllFromRealm();
                    }
                });
                MainActivity.adapter.notifyDataSetChanged();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm = MainActivity.realm;
                Task editTask = realm.where(Task.class)
                        .equalTo("id", task.getId())
                        .findFirst();
                realm.beginTransaction();
                EditText comment = findViewById(R.id.detail_task_comment);
                task.setComment(comment.getText().toString());
                realm.commitTransaction();
                MainActivity.adapter.notifyDataSetChanged();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
        Button button = findViewById(R.id.task_color_picker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(context, task.getTaskColor(), new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {

                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        Realm realm = MainActivity.realm;
                        Task editTask = realm.where(Task.class)
                                .equalTo("id", task.getId())
                                .findFirst();
                        realm.beginTransaction();
                        editTask.setTaskColor(color);
                        realm.commitTransaction();
                    }
                });
                colorPicker.show();
            }
        });
    }
}
