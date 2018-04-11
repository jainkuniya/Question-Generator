package com.example.user.qapp.utils;

import android.content.Context;
import android.os.Environment;

import com.example.user.qapp.network.UploadFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by user on 10/4/18.
 */

public class FileGenerator {

    public static File createFile(Context context, String data, String fileName) throws IOException {
        File path = new File(Environment.getExternalStorageDirectory(), "QApp");
        if (!path.exists()) {
            path.mkdir();
        }
        File file = new File(path, fileName);
        FileWriter stream = new FileWriter(file);
        try {
            stream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        return file;
    }
}
