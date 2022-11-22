package com.example.velo_app.api

import com.example.velo_app.model.Park
import retrofit2.Response
import retrofit2.http.GET

interface ParkApi {

    @GET("api/parks")
    suspend fun getPark(): Response<List<Park>>

}