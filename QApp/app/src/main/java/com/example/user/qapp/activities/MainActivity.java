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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.et_copy_text)
    EditText copyText;
    @BindView(R.id.bt_proceed)
    Button proceed;

    String copiedText;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


  @OnClick(R.id.bt_proceed)
    public void proceed(View view) {



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

