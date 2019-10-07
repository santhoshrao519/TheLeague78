package com.app.theleague78.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //public static final String BASE_URL = "http://ec2-18-217-201-202.us-east-2.compute.amazonaws.com/";
    public static final String BASE_URL = "http://18.235.239.32:8080/TheServer78/restful/";
    public static final String PAYPAL_URL = "https://api.sandbox.paypal.com";
    public static final String TINY_URL = "http://tinyurl.com/";

    private static Retrofit retrofit = null;
    private static Retrofit tinyretrofit = null;

    public static Retrofit getClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
       loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(1300, TimeUnit.SECONDS)
                .readTimeout(1300, TimeUnit.SECONDS)
                .writeTimeout(1300, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();


        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                  //  .addCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory.MainThreadExecutor()))
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getPayPal() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(1300, TimeUnit.SECONDS)
                .readTimeout(1300, TimeUnit.SECONDS)
                .writeTimeout(1300, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();


        if (tinyretrofit == null) {

            tinyretrofit = new Retrofit.Builder()
                    .baseUrl(PAYPAL_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    //  .addCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory.MainThreadExecutor()))
                    .build();
        }
        return tinyretrofit;
    }
    public static Retrofit getTinyURLClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(1300, TimeUnit.SECONDS)
                .readTimeout(1300, TimeUnit.SECONDS)
                .writeTimeout(1300, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();


        if (tinyretrofit == null) {

            tinyretrofit = new Retrofit.Builder()
                    .baseUrl(TINY_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    //  .addCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory.MainThreadExecutor()))
                    .build();
        }
        return tinyretrofit;
    }
}