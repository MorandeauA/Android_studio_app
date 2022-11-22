package com.example.velo_app.api

import com.example.velo_app.model.Station
import retrofit2.Response
import retrofit2.http.GET

interface StationApi {

    @GET("api/stations")
    suspend fun getStation(): Response<List<Station>>

}