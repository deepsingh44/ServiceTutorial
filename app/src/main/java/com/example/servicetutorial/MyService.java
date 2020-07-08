package com.example.servicetutorial;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class MyService extends IntentService {

    public MyService() {
        super("Download Services");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            URL url = new URL("https://images.unsplash.com/photo-1541423408854-5df732b6f6d1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjI0MX0&w=1000&q=80");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpsURLConnection = (HttpURLConnection) urlConnection;

            InputStream im = httpsURLConnection.getInputStream();

            File root = Environment.getExternalStorageDirectory();
            File folder = new File(root, "kuldeep");
            folder.mkdir();

            File file = new File(folder, "abc.jpg");
            file.createNewFile();

            FileOutputStream fo = new FileOutputStream(file);
            int i = 0;
            byte[] buffer = new byte[1024];
            while ((i = im.read(buffer)) != -1) {
                fo.write(buffer, 0, i);
                fo.flush();
            }
            fo.close();
            im.close();
            Log.e("error", file.getAbsolutePath());
            Log.e("error", "successfully download");
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
    }
}
