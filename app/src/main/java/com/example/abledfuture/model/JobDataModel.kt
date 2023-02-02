package com.example.abledfuture.model

import com.beust.klaxon.Json


data class JobInitialModel(
    @Json(name = "status") val status: String,
    @Json("request_id") val request_id: String,
    @Json("data") val data: List<JobDataModel>
)

data class JobDataModel(
    val employer_name: String,
    val employer_logo: String,
    val job_publisher: String,
    val job_employment_type: String,
    val job_title: String,
    val job_description: String,
    val job_city: String,
    val job_state: String,
    val job_salary_currency: String,
    val job_preference: String

)
