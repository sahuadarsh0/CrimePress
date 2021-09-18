package com.techipinfotech.allindiacrimepress.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.techipinfotech.allindiacrimepress.databinding.FragmentMyListBinding
import com.techipinfotech.allindiacrimepress.model.MemberItem
import com.techipinfotech.allindiacrimepress.ui.adapters.MembersAdapter
import com.techipinfotech.allindiacrimepress.utils.ProcessDialog
import com.techipinfotech.allindiacrimepress.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by viewModels()
    private var _binding: FragmentMyListBinding? = null
    private val membersAdapter = MembersAdapter(this::onItemClicked)
    private val binding get() = _binding!!
    private lateinit var processDialog: ProcessDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyListBinding.inflate(inflater, container, false)
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
        listViewModel.members.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> processDialog.show()
                Resource.Status.SUCCESS -> {
                    membersAdapter.submitList(it.data)
                    Log.d("asa", "setupObservers: reached " + it.data)
                    Log.d("asa", membersAdapter.itemCount.toString())
                    processDialog.dismiss()
                }
                Resource.Status.ERROR -> {
                    Log.d("asa", "member error ${it.message}")
                    processDialog.dismiss()
                }
            }
        })

    }

    private fun onItemClicked(memberItem: MemberItem) {
        Toast.makeText(requireContext(), "name "+memberItem.name, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}