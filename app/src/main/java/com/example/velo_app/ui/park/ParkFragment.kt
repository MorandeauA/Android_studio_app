package com.example.velo_app.ui.park

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.velo_app.adapter.ParkAdapter
import com.example.velo_app.api.ParkApi
import com.example.velo_app.api.RetrofitHelper
import com.example.velo_app.api.StationApi
import com.example.velo_app.databinding.FragmentParkBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ParkFragment : Fragment() {

    private var _binding: FragmentParkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val parkViewModel =
            ViewModelProvider(this).get(ParkViewModel::class.java)

        _binding = FragmentParkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerViewPark
        parkViewModel.parks.observe(viewLifecycleOwner) {
            val adapter = ParkAdapter(it, requireContext())
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }

        val parkApi = RetrofitHelper().getInstance().create(ParkApi::class.java)
        GlobalScope.launch {
            val result = parkApi.getPark()
            Log.d("Park",result.body().toString())
            parkViewModel.parks.postValue(result.body())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
