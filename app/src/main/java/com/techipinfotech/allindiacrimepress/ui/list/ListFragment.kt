package com.techipinfotech.allindiacrimepress.ui.list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.techipinfotech.allindiacrimepress.R
import com.techipinfotech.allindiacrimepress.databinding.FragmentMyListBinding
import com.techipinfotech.allindiacrimepress.model.MemberItem
import com.techipinfotech.allindiacrimepress.ui.adapters.MembersAdapter
import com.techipinfotech.allindiacrimepress.utils.ProcessDialog
import com.techipinfotech.allindiacrimepress.utils.Resource
import com.techipinfotech.allindiacrimepress.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by viewModels()
    private var _binding: FragmentMyListBinding? = null
    private val membersAdapter = MembersAdapter(this::onItemClicked)
    private val binding get() = _binding!!
    private lateinit var processDialog: ProcessDialog
    private lateinit var members: List<MemberItem>
    @Inject lateinit var userSharedPreferences: SharedPrefs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        processDialog = ProcessDialog(requireContext())
        processDialog.show()
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
                    val animationController: LayoutAnimationController =
                        AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
                    binding.memberList.layoutAnimation = animationController
                    resource.data?.let { members = it }
                    membersAdapter.submitList(members)
                    if (members.isNotEmpty())
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
        userSharedPreferences["member_id_temp"] = memberItem.memberId
        findNavController().navigate(R.id.action_navigation_my_list_to_navigation_account)
        Toast.makeText(requireContext(),memberItem.name, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}