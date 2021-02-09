package com.arun.daboyatask.data.remote

import com.arun.daboyatask.Model.ResponseModel
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


/**
// Created by Arun Singh Rawat  on 09-02-2021.



 **/

//Api interface calling api for details

interface ApiInterface {
    @GET("/v2/list")
    @Headers("Content-Type:application/json")
    fun fetchList(@Query("page") page : String? , @Query("limit") limit:String?):Flowable<ArrayList<ResponseModel>>
}