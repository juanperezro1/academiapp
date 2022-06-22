package com.academiapp.services

import com.academiapp.app.MyApplication
import com.academiapp.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        private var newInstance: Retrofit? = null

        private fun getInstance(): Retrofit {
            if (newInstance == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
                    .readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build()

                val sp = SharedPreferencesManager(MyApplication.applicationContext())
                val mainurl = sp.getmainurl()

                newInstance = Retrofit.Builder()
                    .baseUrl(mainurl)
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return newInstance!!
        }

        fun createService(): RetrofitApi = getInstance().create(RetrofitApi::class.java)
    }
}