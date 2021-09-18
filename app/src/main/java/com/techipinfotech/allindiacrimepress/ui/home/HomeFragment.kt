package com.techipinfotech.allindiacrimepress.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.techipinfotech.allindiacrimepress.databinding.FragmentHomeBinding
import com.techipinfotech.allindiacrimepress.model.MemberItem
import com.techipinfotech.allindiacrimepress.ui.adapters.MembersAdapter
import com.techipinfotech.allindiacrimepress.utils.Constants
import com.techipinfotech.allindiacrimepress.utils.ProcessDialog
import com.techipinfotech.allindiacrimepress.utils.Resource.Status.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(){

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val membersAdapter = MembersAdapter(this::onItemClicked)
    private val binding get() = _binding!!
    private lateinit var processDialog: ProcessDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        processDialog = ProcessDialog(requireContext())
        setupRecyclerView()
        setupObservers()

        return root
    }

    private fun setupRecyclerView() {
        binding.memberList.adapter = membersAdapter
    }
    private fun setupObservers() {
        val imageList = ArrayList<SlideModel>()
        homeViewModel.banner.observe(viewLifecycleOwner, {
            when (it.status) {
                LOADING ->
                    Log.d("asa", "banner error ${it.message}")
                SUCCESS -> {
                    val banner = it.data

                    if (banner != null) {
                        for ((i, v) in banner.withIndex()) {
                            imageList.add(SlideModel(Constants.BANNER.toString() + v.photo, v.title))
                        }
                        binding.imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
                    }

                }
                ERROR -> {
                    Log.d("asa", "banner error ${it.message}")

                }

            }
        })

        homeViewModel.members.observe(viewLifecycleOwner,{
            when(it.status){
                LOADING -> processDialog.show()
                SUCCESS -> {
                    membersAdapter.submitList(it.data)
                    Log.d("asa", "setupObservers: reached "+it.data)
                    Log.d("asa", membersAdapter.itemCount.toString())
                    processDialog.dismiss()
                }
                ERROR -> {
                    Log.d("asa", "member error ${it.message}")
                    processDialog.dismiss()
                }
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClicked(memberItem: MemberItem) {
        Toast.makeText(requireContext(), "name "+memberItem.name, Toast.LENGTH_SHORT).show()
    }
}