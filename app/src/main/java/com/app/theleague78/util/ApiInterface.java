package com.app.theleague78.util;
import com.app.theleague78.entity.AllLeaguesResponse;
import com.app.theleague78.entity.LoginResponse;
import com.app.theleague78.entity.RegisterResponse;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiInterface {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("customer/register")
    Call<RegisterResponse> customerSignUp(@Body JsonObject loginData);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("customer/login")
    Call<LoginResponse> customerLogin(@Body JsonObject loginData);

    @GET("league/getAllLeagues")
    Call<AllLeaguesResponse> getAllLeagues();

}
