package com.example.user.qapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.qapp.R;
import com.example.user.qapp.utils.FileGenerator;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button proceed;
    EditText copyText;
    String copiedText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                FileGenerator fg = new FileGenerator(this);
                try {
                    fg.createFile(copiedText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

}