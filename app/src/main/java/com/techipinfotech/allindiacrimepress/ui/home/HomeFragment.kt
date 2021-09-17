package com.techipinfotech.allindiacrimepress.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.denzcoskun.imageslider.models.SlideModel
import com.techipinfotech.allindiacrimepress.databinding.FragmentHomeBinding
import com.techipinfotech.allindiacrimepress.utils.Constants
import com.techipinfotech.allindiacrimepress.utils.Resource.Status.SUCCESS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.members.observe(viewLifecycleOwner, {
            textView.text = it.data.toString()
        })

        val imageList = ArrayList<SlideModel>()
        homeViewModel.banner.observe(viewLifecycleOwner, {
            when (it.status) {
                SUCCESS -> {
                    val banner = it.data

                    for ((i, v) in banner!!.withIndex()) {
                        imageList.add(SlideModel(Constants.BANNER.toString() + v.photo, v.title))
                        Log.d("asa", "onCreateView: $i"+Constants.BANNER.toString() + v.photo)
                    }

                    binding.imageSlider.setImageList(imageList)
                }
                else -> Log.d("asa", "Slider Error")
            }
        })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}