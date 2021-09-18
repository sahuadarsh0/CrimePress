package com.techipinfotech.allindiacrimepress.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val membersAdapter = MembersAdapter(this::onItemClicked)
    private val binding get() = _binding!!
    private lateinit var processDialog: ProcessDialog
    private lateinit var members: List<MemberItem>

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

        binding.searchMember.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    filter(s.toString())
                }
            }
        })
        return root
    }

    private fun setupRecyclerView() {
        binding.memberList.adapter = membersAdapter
    }

    fun filter(strTyped: String) {
        val filteredList = arrayListOf<MemberItem>()

        for (member in members) {
            if (member.name!!.lowercase().contains(strTyped.lowercase())) {
                filteredList.add(member)

            }
        }
        membersAdapter.submitList(filteredList)
        Log.d("asa", "filter:  + ${members.size} + ${filteredList.size} ")
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

        homeViewModel.members.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                LOADING -> processDialog.show()
                SUCCESS -> {
                    resource.data?.let { members = it }
                    membersAdapter.submitList(members)
                    Log.d("asa", "setupObservers: reached " + resource.data)
                    processDialog.dismiss()
                }
                ERROR -> {
                    Log.d("asa", "member error ${resource.message}")
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
        Toast.makeText(requireContext(), memberItem.name, Toast.LENGTH_SHORT).show()
    }
}