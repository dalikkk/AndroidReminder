package com.example.daliborkram.reminder;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    static Context context;
    public static ArrayList<Task> tasks;
    public static final String TASK_POSITION = "position";
    private static final int PERMISION_REQUEST_STRAGE_CODE = 1;
    public static Realm realm;
    private static int maxId = 0;
    public static TaskAdapter adapter;
    private Runnable timeUpdate = new Runnable() {
        @Override
        public void run() {
            MainActivity.adapter.notifyDataSetChanged();
            timeUpdater.postDelayed(this, 500);
        }
    };

    Handler timeUpdater;

    public static void increaseMaxId() {
        maxId++;
    }

    public static int getMaxId() {
        return maxId;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasks = new ArrayList<Task>();
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            dbOncerateCommunication();
            adapter = new TaskAdapter(this, R.id.task_item, tasks);
            GridView view = findViewById(R.id.grid_view_tasks);
            view.setAdapter(adapter);
            view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(context, TaskActivity.class);
                    intent.putExtra(TASK_POSITION, i);
                    startActivity(intent);
                    return false;
                }
            });
            ImageView addBtn = findViewById(R.id.main_add);
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AddTaskActivity.class);
                    startActivity(intent);
                }
            });
            timeUpdater = new Handler();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISION_REQUEST_STRAGE_CODE);
        }
        context = this;
    }
    protected void dbOncerateCommunication() {
        Realm.init(this);
        String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("reminder").directory(new File(rootPath + "/reminder/")).build();
        Realm.setDefaultConfiguration(configuration);

        realm = Realm.getDefaultInstance();
        RealmResults<Task> tasks = realm.where(Task.class).findAll();
        // copy to ArrayList
        for (int i = 0;i < tasks.size();i++){
            this.tasks.add(tasks.get(i));
            if (tasks.get(i).getId() > maxId)
                maxId = tasks.get(i).getId();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISION_REQUEST_STRAGE_CODE) {
            boolean ok = true;
            for (int i = 0;i < permissions.length;i++) {
                if (permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE) || permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED)
                        ok = false;
            }
            if (ok)
                dbOncerateCommunication();
            else
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        timeUpdater.removeCallbacks(timeUpdate);
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeUpdater.postDelayed(timeUpdate, 500);
    }
}
