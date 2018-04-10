package com.example.user.qapp.network;

import android.util.Log;

import com.example.user.qapp.model.Result;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 10/4/18.
 */

public class UploadFile {
    String fileName;

    public void upload_file(File file, String title) {

        CreateRetrofit cr = new CreateRetrofit();

        Retrofit retrofit = cr.create();

        APIService service = retrofit.create(APIService.class);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("text/*"), file));
        RequestBody filenm =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), fileName);


        Call<Result> uploadfileserver = service.uploadFile(filePart, filenm);

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
}


