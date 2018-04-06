package com.example.user.qapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button proceed;
    EditText copyText;
    String copiedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proceed=(Button)findViewById(R.id.bt_proceed);
        copyText=(EditText)findViewById(R.id.et_copy_text);


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.bt_proceed){
            copiedText=copyText.getText().toString();
            if(copiedText!=null){
                Toast.makeText(this, "ohk", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
