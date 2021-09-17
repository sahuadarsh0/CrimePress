package com.techipinfotech.allindiacrimepress.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.techipinfotech.allindiacrimepress.databinding.FragmentHomeBinding
import com.techipinfotech.allindiacrimepress.ui.adapters.MembersAdapter
import com.techipinfotech.allindiacrimepress.utils.Constants
import com.techipinfotech.allindiacrimepress.utils.ProcessDialog
import com.techipinfotech.allindiacrimepress.utils.Resource.Status.SUCCESS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val membersAdapter  by lazy { MembersAdapter() }
    private val binding get() = _binding!!
    private lateinit var processDialog: ProcessDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        processDialog = ProcessDialog(requireContext())
//        val textView: TextView = binding.textHome
//        homeViewModel.members.observe(viewLifecycleOwner, {
//            textView.text = it.data.toString()
//        })

        setupRecyclerView()
        setupObservers()
        val imageList = ArrayList<SlideModel>()
        homeViewModel.banner.observe(viewLifecycleOwner, {
            when (it.status) {
                SUCCESS -> {
                    val banner = it.data

                    if (banner != null) {
                        for ((i, v) in banner.withIndex()) {
                            imageList.add(SlideModel(Constants.BANNER.toString() + v.photo, v.title))
                        }
                        binding.imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
                    }
                }
                else -> Log.d("asa", "Slider Error")
            }
        })
        homeViewModel.members.observe(viewLifecycleOwner,{
            when(it.status){
                SUCCESS -> {
                   val members = it.data
                    membersAdapter.setList(ArrayList(it.data))
                    membersAdapter.notifyDataSetChanged()
                }
                else  -> Log.d("asa", " membersAdapter Error")
            }
        })


        return root
    }

    private fun setupRecyclerView() {

        binding.memberList.layoutManager = LinearLayoutManager(requireContext())
        binding.memberList.adapter = membersAdapter

    }
    private fun setupObservers() {
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}