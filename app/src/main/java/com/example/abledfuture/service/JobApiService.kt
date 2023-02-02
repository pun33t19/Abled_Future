package com.example.delizza.network


import com.example.abledfuture.model.JobDataModel
import com.example.abledfuture.model.JobInitialModel

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

//base url custom rest api
private const val BASE_URL = "https://jsearch.p.rapidapi.com/"

//initialising the moshi converter factory to be used for parsing the Json response
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Retrofit initialisation block
private val retroFit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//an interface for all the api specific methods along with the endpoints
interface JobApiService {

    @GET("search")
    @Headers("X-RapidAPI-Key:685c2e89a0msh9e99f3168438145p19768cjsn104b3bbd9388","X-RapidAPI-Host:jsearch.p.rapidapi.com")
    suspend fun getMeals(
        @Query("query") country: String,
        @Query("num_pages") pages: String
    ): JobInitialModel

}

//using singleton pattern to initialise retrofit object only once lazily

object JobApi {
    val retroFitService: JobApiService by lazy {
        retroFit.create(JobApiService::class.java)
    }
}







