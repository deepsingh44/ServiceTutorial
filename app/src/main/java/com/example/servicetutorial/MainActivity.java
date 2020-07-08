package com.example.servicetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText troll, tname, tgrade;
    static final String PROVIDER_NAME = "com.example.servicetutorial.PersonProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/students";
    static final Uri CONTENT_URI = Uri.parse(URL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        troll = findViewById(R.id.roll);
        tname = findViewById(R.id.name);
        tgrade = findViewById(R.id.grade);
    }

    public void start(View view) {
        ContentValues cv = new ContentValues();
        int roll = Integer.parseInt(troll.getText().toString());
        cv.put("roll", roll);
        cv.put("name", tname.getText().toString());
        cv.put("grade", tgrade.getText().toString());

        Uri uri = getContentResolver().insert(CONTENT_URI, cv);
        Toast.makeText(this, "" + uri, Toast.LENGTH_SHORT).show();

    }

    public void fetch(View view) {
        Cursor c = managedQuery(CONTENT_URI, null, null, null, "name");
        while (c.moveToNext()) {
            Toast.makeText(this, c.getInt(0) + "\t" + c.getString(1), Toast.LENGTH_SHORT).show();

        }
    }

    public void getStudent(View view) {
        String roll = troll.getText().toString();
        Cursor c = managedQuery(CONTENT_URI, null, "roll=?", new String[]{roll}, "name");
        if (c.moveToNext()) {
            Toast.makeText(this, c.getInt(0) + "\t" + c.getString(1) + "\t" + c.getString(2), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "This roll is not exist", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view) {
        ContentValues cv = new ContentValues();
        int roll = Integer.parseInt(troll.getText().toString());
        cv.put("roll", roll);
        cv.put("name", tname.getText().toString());
        cv.put("grade", tgrade.getText().toString());

        int i = getContentResolver().update(CONTENT_URI, cv, "roll=?", new String[]{String.valueOf(roll)});
        if (i > 0) {
            Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Updation Failed", Toast.LENGTH_SHORT).show();
        }

    }

    public void delete(View view) {
        int roll = Integer.parseInt(troll.getText().toString());

        int i = getContentResolver().delete(CONTENT_URI, "roll=?", new String[]{String.valueOf(roll)});
        if (i > 0) {
            Toast.makeText(this, "Delete Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Deletion Failed", Toast.LENGTH_SHORT).show();
        }

    }

}
