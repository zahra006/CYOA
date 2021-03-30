package com.mobile.cyoa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {
    String LOGINURL = "http://192.168.43.235:8000/api/";
    @FormUrlEncoded
    @POST("login")
    Call<String> getUserLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
