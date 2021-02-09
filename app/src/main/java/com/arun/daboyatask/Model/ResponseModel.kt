package com.arun.daboyatask.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
// Created by ArunsinghRawat  on 08-02-2021.



 **/

class ResponseModel : Serializable{

    var id : String = ""
    var author : String = ""
    var width : Int = 0
    var height : Int = 0
    var url : String = ""
    var download_url = ""


}