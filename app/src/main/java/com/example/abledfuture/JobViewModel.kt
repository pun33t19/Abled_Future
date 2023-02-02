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
    val jobDataList:MutableLiveData<MutableList<JobDataModel>> = MutableLiveData()







    }
