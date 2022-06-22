package com.academiapp.functions;

import android.content.Context;

import com.academiapp.services.SharedPreferencesManager;

import java.io.IOException;

import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBaseURl {

    //public static final String BASE_URL = "https://apitest.academiapp.app/";
    //    public static final String BASE_URL_NEWS = "https://min-api.cryptocompare.com/data/";
    public static final String API_KEY = "authorization: Apikey 525fa1933d3246c3bbd6a8ec96207baf3104c80d12b95a4b4cf9196ece4d3728";
    public static final String APP_NAME = "TrackingCrypto";
    private SharedPreferencesManager sp;
    Context context;
//    private static Retrofit retrofit = null;

    public ApiBaseURl(Context context) {
        context = context;
        sp = new SharedPreferencesManager(context);




    }

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + sp)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();

    String mainurl=sp.getmainurl();

    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .baseUrl(mainurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

//    public static Retrofit getClient() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
//
//    public static Retrofit getNews() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL_NEWS)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
}
