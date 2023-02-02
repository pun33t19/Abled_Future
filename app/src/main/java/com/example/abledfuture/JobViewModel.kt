package com.example.abledfuture

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.abledfuture.model.JobDataModel
import com.example.abledfuture.model.JobInitialModel

import com.example.delizza.network.JobApi

import kotlinx.coroutines.launch
import java.lang.Exception

class JobViewModel : ViewModel() {

   val jobInitial:MutableLiveData<JobInitialModel> =MutableLiveData()

    fun fetch(){
     //   val jobDataList:MutableList<JobDataModel> = mutableListOf()



        viewModelScope.launch {
            try {

                jobInitial.value=JobApi.retroFitService.getMeals("India","1")
                Log.d("Pizza_List","Pizza list retrieved successfully! "+jobInitial.value!!)
                Log.d("List_Here ",jobInitial.value.toString())
            }
            catch (e: Exception){
                Log.d("Exception","Exception occurred in fetching data "+e.message)
            }



        }





    }
}