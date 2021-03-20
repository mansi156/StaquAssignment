package com.mansi.staquassignment.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mansi.staquassignment.model.ItemResponse
import com.mansi.staquassignment.model.Items
import com.mansi.staquassignment.network.ApiInstance
import com.mansi.staquassignment.network.ApiService
import retrofit2.Call
import retrofit2.Response

class UnAnsweredListViewModel : ViewModel() {
    var unAnsweredList : MutableLiveData<List<Items>> = MutableLiveData()

    fun getObservableList() : MutableLiveData<List<Items>>{
        return  unAnsweredList
    }

    fun getUnAnsweredList(){
        val apiInstance = ApiInstance.getApiInstance().create(ApiService::class.java)
        val call = apiInstance.getUnansweredList("desc","activity","stackoverflow")
        call.enqueue( object : retrofit2.Callback<ItemResponse>{
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                if(response.isSuccessful) {
                    unAnsweredList.postValue(response.body()?.items)
                } else{
                    unAnsweredList.postValue(null)
                }
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                unAnsweredList.postValue(null)
            }
        })
    }
}