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
    File path, file;
    Context context;
    String fileName;
    public FileGenerator(Context context) {
        this.context = context;
    }

    public void createFile(String data) throws IOException {
        path = new File(Environment.getExternalStorageDirectory(), "QApp");
        if (!path.exists()) {
            path.mkdir();
        }

        fileName = FileNameGenerator.generateFromIEMIAndTimeStamp(context) + ".txt";
        file = new File(path, fileName);
        FileWriter stream = new FileWriter(file);
        try {
            stream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        FileOpen.openFile(context, file);
        // sendFile();
    }

    public void sendFile() {
        UploadFile up = new UploadFile();
        up.upload_file(file, fileName);
    }

    ///data/user/0/com.example.user.qapp/files/QApp/352175070657246_1523387288744.txt
}
