package com.arun.daboyatask.Model

import android.util.Log
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.arun.daboyatask.data.remote.ApiInterface
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.ArrayList


/**
 *Created by Arun singh rawat  on 09-02-2021.
 *https://github.com/arunsinghrwt

 */
class MainViewModel(var apilist : ApiInterface) : ViewModel(){
    var TAG = "ViewMode"
    var mediatorList = MediatorLiveData<ArrayList<ResponseModel>>()
    fun getList( page : String ){
        var list = LiveDataReactiveStreams.fromPublisher(
            apilist.fetchList(page,"100")!!.onErrorReturn {
              ArrayList<ResponseModel>().apply {

              }
            }.map {
                return@map try {
                    Log.e("api_response","->> "+ GsonBuilder().create().toJson(it))
                    it
                } catch (e: Exception) {
                    ArrayList<ResponseModel>()
                }
            }.subscribeOn(Schedulers.io())
        )

        mediatorList.addSource(list){
                t ->
            mediatorList.value = t
            mediatorList.removeSource(list)

        }
    }

}