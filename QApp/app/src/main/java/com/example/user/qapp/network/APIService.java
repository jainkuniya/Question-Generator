package com.example.user.qapp.network;

import com.example.user.qapp.model.UploadFileResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by user on 10/4/18.
 */

public interface APIService {

    @Multipart
    @POST("upload_file/")
    Call<UploadFileResponse> uploadFile(@Part MultipartBody.Part file, @Part("title") RequestBody title);
}
