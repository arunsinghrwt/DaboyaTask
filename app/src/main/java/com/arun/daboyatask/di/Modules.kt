package com.arun.daboyatask.di

import com.arun.daboyatask.Model.MainViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 *Created by Arun singh rawat  on 09-02-2021.
 *https://github.com/arunsinghrwt

 */



var retrofitModule = module {
    single { returnProvideRetrofit() }
    single { getApi(get()) }
    factory { PbrBtn() }
    viewModel { MainViewModel(get ()) }
}
