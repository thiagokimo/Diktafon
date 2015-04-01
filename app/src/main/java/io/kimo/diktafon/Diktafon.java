package io.kimo.diktafon;

import android.app.Application;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

/**
 * Diktafon application
 */
public class Diktafon extends Application {

    public static final String APP_FOLDER = "/Diktafon";

    @Override
    public void onCreate() {
        super.onCreate();

        ensureAppFolder();
    }

    private void ensureAppFolder() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(getApplicationContext(), "NO SD CARD", Toast.LENGTH_LONG).show();
        } else {
            File directory = new File(Environment.getExternalStorageDirectory()+ APP_FOLDER);
            directory.mkdirs();
        }
    }
}
