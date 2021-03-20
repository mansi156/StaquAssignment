package com.mansi.staquassignment.network

import com.mansi.staquassignment.utility.Common
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInstance {
    companion object{
        fun getApiInstance() : Retrofit{
           return Retrofit.Builder()
                    .baseUrl(Common.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}