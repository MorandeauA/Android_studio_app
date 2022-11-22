package com.example.velo_app.ui.parkDetail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.velo_app.R
import com.example.velo_app.databinding.ActivityParkMapBinding
import com.example.velo_app.databinding.ActivityVeloMapsBinding
import com.example.velo_app.model.allStations
import com.example.velo_app.model.currentLocation
import com.example.velo_app.model.parkSelected
import com.example.velo_app.model.stationSelected
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ParkMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityParkMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park_map)

        binding = ActivityParkMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.parkMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        parkSelected?.let { park ->

            allStations?.let {
                for (parking in it) {
                    val stationLtLng = LatLng(parking.latitude, parking.longitude)
                    if(parking.id == park!!.id) {
                        mMap.addMarker(MarkerOptions().position(stationLtLng).title(park.showDetails()).icon(BitmapFromVector(this, R.drawable.ic_baseline_directions_car_24)))
                    }
                    mMap.addMarker(MarkerOptions().position(stationLtLng).title(parking.showDetails()))
                }
            }

            if( currentLocation != null ) {
                val stationLocality = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
                mMap.addMarker(MarkerOptions().position(stationLocality).title(park.showDetails()).icon(BitmapFromVector(this, R.drawable.ic_baseline_my_location)))
            }
            val stationLocality = LatLng(park.latitude, park.longitude)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(stationLocality,18f))


        }
    }

    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        // below line is use to generate a drawable.
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        // below line is use to create a bitmap for our
        // drawable which we have added.
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // below line is use to add bitmap in our canvas.
        val canvas = Canvas(bitmap)

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas)

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}