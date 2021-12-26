package com.example.climby.ui.profile

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.climby.databinding.FragmentMyOutingsBinding
import com.example.climby.ui.discover.adapter.DiscoverAdapter
import com.example.climby.ui.profile.adapter.DiscoverAdapterProfile
import com.example.climby.ui.profile.viewmodel.MyOutingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyOutingsFragment : Fragment() {

    private lateinit var binding: FragmentMyOutingsBinding
    private lateinit var myOutingsViewModel: MyOutingsViewModel
    private lateinit var discoverAdapterProfile: DiscoverAdapterProfile

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        myOutingsViewModel = ViewModelProvider(this).get(MyOutingsViewModel::class.java)
        binding = FragmentMyOutingsBinding.inflate(layoutInflater)
        val view: View = binding.root

        binding.RVTrips.layoutManager = LinearLayoutManager(activity)
        myOutingsViewModel.tripsModel.observe(viewLifecycleOwner,  {
            if(it.isNullOrEmpty()){
                binding.CLTripsEmpty.isVisible = true
                binding.RVTrips.isVisible = false
                moveHand()
            }else{
                /*binding.CLTripsEmpty.isVisible = false*/
                binding.RVTrips.isVisible = true
                discoverAdapterProfile = DiscoverAdapterProfile(it, requireContext())
                binding.RVTrips.adapter = discoverAdapterProfile
                discoverAdapterProfile.SetOnItemClickListener(object : DiscoverAdapterProfile.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        //loadActivity(it[position])
                    }
                })
            }
        })
        myOutingsViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.PBMyOutings.isVisible = it
        })

        myOutingsViewModel.getMyTrips()


        return view
    }

    private fun moveHand(){
        val anim = ObjectAnimator.ofFloat(binding.IVHandEmpty, "translationY", 0f, 50f)
        anim.duration = 1000
        anim.repeatCount = Animation.INFINITE;
        anim.repeatMode = ValueAnimator.REVERSE;

        anim.start()
    }
}