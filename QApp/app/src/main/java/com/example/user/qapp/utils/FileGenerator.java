package com.example.user.qapp.utils;

import android.content.Context;

import com.example.user.qapp.network.UploadFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by user on 10/4/18.
 */

public class FileGenerator {
    File path,file;
    Context context;
    String fileName;
    public FileGenerator(Context context){
        this.context=context;
    }
    public void createFile(String data) throws IOException {
        path = new File(context.getFilesDir(),"QApp");
        if(!path.exists()){
            path.mkdir();
        }

        fileName= FileNameGenerator.generateFromIEMIAndTimeStamp(context);
        file = new File(path,fileName);
        FileWriter stream = new FileWriter(file);
        try {
            stream.append(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
       // sendFile();
    }
    public void sendFile(){
        UploadFile up=new UploadFile();
        up.upload_file(file,fileName);
    }

}
