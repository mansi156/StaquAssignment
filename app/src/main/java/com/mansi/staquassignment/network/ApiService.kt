package com.mansi.staquassignment.network


import com.mansi.staquassignment.model.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("questions/no-answers")
    fun getUnansweredList(@Query("order") order : String,
                          @Query("sort") sort : String,
                          @Query("site") site : String) : Call<ItemResponse>
}