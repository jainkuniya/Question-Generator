package com.example.user.qapp.Utility;

import com.example.user.qapp.Model.Result;

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
    @POST("upload_file")
    Call<Result> uploadFile(@Part MultipartBody.Part file, @Part("title") RequestBody  title);
}
