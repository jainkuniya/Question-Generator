package com.example.user.qapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.qapp.R;
import com.example.user.qapp.network.UploadFile;
import com.example.user.qapp.utils.FileGenerator;
import com.example.user.qapp.utils.FileNameGenerator;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button proceed;
    EditText copyText;
    String copiedText;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;

        setContentView(R.layout.activity_main);
        proceed = (Button) findViewById(R.id.bt_proceed);
        copyText = (EditText) findViewById(R.id.et_copy_text);
        proceed.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_proceed:
                copiedText = copyText.getText().toString();
                String fileName = FileNameGenerator.generateFromIEMIAndTimeStamp(context) + ".txt";
                try {
                    File file = FileGenerator.createFile(context, copiedText,
                            fileName);
                    Log.i("MainActivity", "File created");
                    //upload file
                    UploadFile up = new UploadFile();
                    up.uploadFile(this,file, fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

    }

}