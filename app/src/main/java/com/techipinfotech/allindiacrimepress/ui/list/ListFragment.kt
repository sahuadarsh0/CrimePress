package com.techipinfotech.allindiacrimepress.ui.list

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
    private lateinit var members: List<MemberItem>

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

    fun filter(strTyped: String) {
        val filteredList = arrayListOf<MemberItem>()

        for (member in members) {
            if (member.name!!.lowercase().contains(strTyped.lowercase())) {
                filteredList.add(member)

            }
        }
        membersAdapter.submitList(filteredList)
    }

    private fun setupRecyclerView() {
        binding.memberList.adapter = membersAdapter
    }

    private fun setupObservers() {
        listViewModel.members.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> processDialog.show()
                Resource.Status.SUCCESS -> {
                    resource.data?.let { members = it }
                    membersAdapter.submitList(members)
                    Log.d("asa", "setupObservers: reached " + resource.data)
                    processDialog.dismiss()
                }
                Resource.Status.ERROR -> {
                    Log.d("asa", "member error ${resource.message}")
                    processDialog.dismiss()
                }
            }
        })

    }

    private fun onItemClicked(memberItem: MemberItem) {
        Toast.makeText(requireContext(), "name " + memberItem.name, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}