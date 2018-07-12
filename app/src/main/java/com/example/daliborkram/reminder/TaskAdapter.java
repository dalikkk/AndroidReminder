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

import io.realm.Realm;
import io.realm.RealmConfiguration;
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
        //time.setText("Hello world");
        return convertView;
    }
    /*
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();

            //RealmConfiguration = new RealmConfiguration(new File(rootPath + "/realm/"), '');
            RealmConfiguration configuration = new RealmConfiguration.Builder().name("realmtutorial").directory(new File(rootPath + "/realm/")).build();
            Realm.setDefaultConfiguration(configuration);

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Task> peopleResult = realm.where(Task.class).findAll();
            Task[] people = peopleResult.toArray(new Task[peopleResult.size()]);
            // get max index
            int primaryKey = 0;
            for (int i = 0; i < people.length; i++)
                if (primaryKey < people[i].getId())
                    primaryKey = people[i].getId();
            realm.beginTransaction();
            Person person = realm.createObject(Person.class, primaryKey + 1);
            person.setName("Dalibor");
            realm.commitTransaction();
            TextView view = (TextView) findViewById(R.id.hello_world);
            view.setText(String.valueOf(primaryKey));
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
        }
    }
    */
}
