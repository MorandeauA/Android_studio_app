package com.example.velo_app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.velo_app.adapter.StationAdapter
import com.example.velo_app.api.RetrofitHelper
import com.example.velo_app.api.StationApi
import com.example.velo_app.databinding.FragmentHomeBinding
import com.example.velo_app.model.allStations
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView= binding.recyclerViewStation
        var progressBarStation = binding.progressBarStation

        homeViewModel.stations.observe(viewLifecycleOwner) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = StationAdapter(it, requireContext())
            progressBarStation.visibility = View.GONE

            allStations=it
        }

        val stationApi = RetrofitHelper().getInstance().create(StationApi::class.java)
        GlobalScope.launch {
            val result = stationApi.getStation()
            Log.d("Home",result.body().toString())
            homeViewModel.stations.postValue(result.body())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}