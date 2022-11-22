package com.example.velo_app.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.velo_app.R
import com.example.velo_app.model.Park
import com.example.velo_app.model.currentLocation
import com.example.velo_app.model.parkSelected
import com.example.velo_app.ui.parkDetail.ParkDetailActivity
import com.example.velo_app.ui.parkDetail.ParkMapActivity



class ParkAdapter(private val parks:List<Park>, private val context: Context) :
    RecyclerView.Adapter<ParkAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val name: TextView = itemView.findViewById(R.id.name)
        val disponibilite: TextView = itemView.findViewById(R.id.disponibilite)
        val distance: TextView = itemView.findViewById(R.id.distance)
        val dispo: TextView = itemView.findViewById(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_park_item,parent,false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val park = parks[position]
        holder.name.text = park.grpNom
        if(currentLocation != null) {
            holder.distance.text = "${String.format("%.1f", currentLocation!!.distanceTo(park.toLocation()) / 1000)}KM"
        }else {
            holder.distance.text = "Géolocalisation désactivée"
        }
        holder.disponibilite.text = park.grpDisponible.toString()

        holder.cardView.setOnClickListener {
            val intent = Intent(context, ParkDetailActivity::class.java)
            parkSelected = park
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return parks.size
    }
}