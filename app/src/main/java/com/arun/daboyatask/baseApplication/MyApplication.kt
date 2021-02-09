package com.arun.daboyatask.baseApplication

import androidx.multidex.MultiDexApplication
import com.arun.daboyatask.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
// Created by Arun Singh Rawat  on 09-02-2021.



 **/

class MyApplication : MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            koin.loadModules(listOf(retrofitModule))
            koin.createRootScope()
        }
    }
}