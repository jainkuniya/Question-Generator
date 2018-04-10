package com.example.user.qapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.qapp.Model.Result;
import com.example.user.qapp.Utility.APIService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button proceed;
    EditText copyText;
    String copiedText;
    File path,file;
    String fileName;

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
        if (view.getId() == R.id.bt_proceed) {
            copiedText = copyText.getText().toString();
            if (copiedText != null) {
                try {
                    createFile(copiedText);
                    sendToServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "ohk", Toast.LENGTH_SHORT).show();
            }
        }

    }

    // to send the created file to the server to get processed
    public void sendToServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8000").addConverterFactory(GsonConverterFactory.create())
                .build();


        APIService service = retrofit.create(APIService.class);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("text/*"), file));
        RequestBody filenm =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), fileName);

        Call<Result> uploadfileserver = service.uploadFile( filePart,filenm);

        uploadfileserver.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                String res = result.getMsg();


                Log.d("onResponse upload", "" + response.code() +
                        "  response body " + response.body() +
                        " responseError " + response.errorBody() + " responseMessage " +
                        response.message());


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("onFailure upload", t.toString());
            }
        });
    }

    //to create file in the internal storage of the mobile
    public void createFile(String data) throws IOException {
         path = this.getFilesDir();
         file = new File(path,generateFileName());
        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
    }

    public String generateFileName() {
       fileName=null;
        byte[] array = new byte[5]; // length is bounded by 5
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        String IMEIno = getDeviceId(this);

        if(IMEIno!=null){
            fileName=IMEIno+generatedString+".txt";
        }
        else
            fileName=generatedString+".txt";
        return fileName;
    }

    public String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return telephonyManager.getDeviceId();
        }
        else
            return null;
    }
}
