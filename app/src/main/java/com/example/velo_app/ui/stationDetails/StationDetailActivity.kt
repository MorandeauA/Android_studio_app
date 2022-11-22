package com.example.velo_app.ui.stationDetails

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.velo_app.R
import com.example.velo_app.model.stationSelected

class StationDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_detail)

        val stationName = findViewById<TextView>(R.id.stationName)
        val openMapBtn = findViewById<Button>(R.id.openMapBtn)

        stationSelected?.let {station ->
            stationName.text = station.name

            openMapBtn.setOnClickListener {
                // Display a label at the location of Google's Sydney office
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${station.latitude},${station.longitude}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }
}