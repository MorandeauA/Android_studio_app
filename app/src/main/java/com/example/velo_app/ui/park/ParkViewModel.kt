package com.example.velo_app.ui.park

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.velo_app.model.Park

class ParkViewModel : ViewModel() {

    private val _parks = MutableLiveData<List<Park>>().apply {
        value = ArrayList()
    }
    val parks: MutableLiveData<List<Park>> = _parks
}