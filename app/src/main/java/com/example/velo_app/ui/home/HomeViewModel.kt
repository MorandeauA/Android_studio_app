package com.example.velo_app.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.velo_app.model.Station

class HomeViewModel : ViewModel() {

    private val _stations = MutableLiveData<List<Station>>().apply {
        value = ArrayList()
    }
    val stations: MutableLiveData<List<Station>> = _stations
}