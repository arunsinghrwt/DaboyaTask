package com.arun.daboyatask.BaseApplication

import androidx.multidex.MultiDexApplication
import org.koin.core.context.startKoin


/**
// Created by Arun Singh Rawat  on 09-02-2021.



 **/

class MyApplication : MultiDexApplication(){


    override fun onCreate() {
        super.onCreate()
        startKoin {



        }



    }



}