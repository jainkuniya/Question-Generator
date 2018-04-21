package com.example.user.qapp.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.user.qapp.model.UploadFileResponse;

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
    ProgressDialog progressDialog;
    public void uploadFile(final Context context, File file, String title) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setTitle("Generating Questions");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        progressDialog.show();

        progressDialog.setCancelable(false);

        CreateRetrofit cr = new CreateRetrofit();

        Retrofit retrofit = cr.create();

        APIService service = retrofit.create(APIService.class);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("text/*"), file));
        RequestBody filenm =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), title);


        Call<UploadFileResponse> uploadfileserver = service.uploadFile(filePart, filenm);

        uploadfileserver.enqueue(new Callback<UploadFileResponse>() {
            @Override

            public void onResponse(Call<UploadFileResponse> call, Response<UploadFileResponse> response) {

                UploadFileResponse uploadFileResponse = response.body();
                int status = uploadFileResponse.getSuccess();

                if (response.code() == 200) {
                    if (status == 1) {
                        //send to next activity
                        //send questions via intent or singleton
                    }
                    Toast.makeText(context, uploadFileResponse.getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Something went wrong on server.", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
            public void onFailure(Call<UploadFileResponse> call, Throwable t) {
                Log.d("onFailure upload", t.toString());
                progressDialog.dismiss();
                Toast.makeText(context, "Failed to connect to server", Toast.LENGTH_SHORT).show();
            }

        });
    }
}


