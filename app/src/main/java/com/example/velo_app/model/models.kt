package com.example.velo_app.model

import android.location.Location
import kotlinx.serialization.*

var stationSelected:Station? = null
var allStations:List<Station>? = null
var currentLocation: Location? = null
var allParks:List<Park>? = null
var parkSelected:Park? = null

@Serializable
data class Station (
    val id: Long,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val status: String,
    val bikeStands: Integer,
    val availableBikeStands: Integer,
    val availableBikes: Integer?,
    val recordId: String
) {
    fun toLocation(): Location {
        val location = Location("")

        location.latitude = latitude

        location.longitude = longitude

        return location
    }
    fun showDetails(): String {
        return ("🚲${availableBikes} 📣${availableBikes} ✅${bikeStands}")
    }
}

@Serializable
data class Park (
    val id: Long,
    val grpNom: String,
    val grpDisponible: Int,
    val latitude: Double,
    val longitude: Double
    ) {
    fun toLocation(): Location {
        val location = Location("")

        location.latitude = latitude

        location.longitude = longitude

        return location
    }

    fun showDetails(): String {
        return ("")
    }

}


