package com.techipinfotech.allindiacrimepress.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.techipinfotech.allindiacrimepress.databinding.FragmentGalleryBinding
import com.techipinfotech.allindiacrimepress.databinding.FragmentHomeBinding
import com.techipinfotech.allindiacrimepress.model.GalleryItem
import com.techipinfotech.allindiacrimepress.model.MemberItem
import com.techipinfotech.allindiacrimepress.ui.adapters.GalleryAdapter
import com.techipinfotech.allindiacrimepress.ui.adapters.MembersAdapter
import com.techipinfotech.allindiacrimepress.ui.home.HomeViewModel
import com.techipinfotech.allindiacrimepress.utils.ProcessDialog
import com.techipinfotech.allindiacrimepress.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    private val galleryViewModel: GalleryViewModel by viewModels()
    private val galleryAdapter = GalleryAdapter(this::onItemClicked)


    private val binding get() = _binding!!
    private lateinit var processDialog: ProcessDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        processDialog = ProcessDialog(requireContext())
        binding.galleryList.layoutManager = StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL)
        binding.galleryList.adapter=galleryAdapter
        galleryViewModel.gallery.observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.LOADING -> processDialog.show()
                Resource.Status.SUCCESS -> {
                    galleryAdapter.submitList(it.data)
                    Log.d("asa", "setupObservers: reached "+it.data)
                    processDialog.dismiss()
                }
                Resource.Status.ERROR -> {
                    Log.d("asa", "gallery error ${it.message}")
                    processDialog.dismiss()
                }
            }
        })
        return root
    }

    private fun onItemClicked(galleryItem: GalleryItem) {
        Toast.makeText(requireContext(),galleryItem.title, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}