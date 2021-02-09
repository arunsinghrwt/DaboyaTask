package com.arun.daboyatask.di

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.multidex.BuildConfig
import com.arun.daboyatask.R
import com.arun.daboyatask.data.remote.ApiInterface
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier


/**
 *Created by Arun singh rawat  on 09-02-2021.
 *https://github.com/arunsinghrwt

 */



fun returnProvideRetrofit(): Retrofit {
    val logging = HttpLoggingInterceptor()

    if (BuildConfig.DEBUG) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        logging.setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    val httpClient = OkHttpClient.Builder()

    httpClient.addInterceptor(logging)
    val gson = GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
        .serializeNulls()
        .setLenient()
        .create()
    return Retrofit.Builder()
        .baseUrl("https://picsum.photos")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(
            httpClient.build()
        )
        .build()
}

fun getApi(retrofit: Retrofit): ApiInterface {
    return retrofit.create(ApiInterface::class.java)
}


class PbrBtn {
    var objectAnimator: ObjectAnimator? = null
    var mybtn: TextView? = null
    fun init(btn: TextView) {
        this.mybtn = btn
    }

    fun start() {
        objectAnimator?.cancel()
        mybtn?.setBackgroundResource(R.drawable.animated_progress)
        this.objectAnimator = mybtn!!.background.showProgressAnim()
    }

    fun cancel(defaulticon: Int = 0) {
        mybtn?.setBackgroundResource(defaulticon)
        objectAnimator?.cancel()
    }
    fun Drawable.showProgressAnim(): ObjectAnimator {
        return ObjectAnimator.ofInt(this, "level", 0, 10000)
            .apply {
                repeatCount = ValueAnimator.INFINITE
                interpolator = LinearInterpolator()
                duration = 600L
                start()
            }

    }

}