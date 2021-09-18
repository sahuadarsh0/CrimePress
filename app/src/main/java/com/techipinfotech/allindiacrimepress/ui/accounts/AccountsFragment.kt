package com.techipinfotech.allindiacrimepress.ui.accounts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.techipinfotech.allindiacrimepress.R
import com.techipinfotech.allindiacrimepress.databinding.FragmentsAccountsBinding
import com.techipinfotech.allindiacrimepress.model.MemberItem
import com.techipinfotech.allindiacrimepress.utils.ProcessDialog
import com.techipinfotech.allindiacrimepress.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountsFragment : Fragment() {


    private val accountsViewModel: AccountsViewModel by viewModels()
    private var _binding: FragmentsAccountsBinding? = null
    private lateinit var processDialog: ProcessDialog
    private lateinit var member: MemberItem
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentsAccountsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        processDialog = ProcessDialog(requireContext())
        processDialog.show()
        setupObservers()
        return root
    }

    private fun setupObservers() {
        accountsViewModel.members.observe(viewLifecycleOwner, {

            val animationController: LayoutAnimationController =
                AnimationUtils.loadLayoutAnimation(context, R.anim.grid_animation)
            binding.card.layoutAnimation = animationController
            member = it
            binding.member = member
//                    if (member) {
            binding.card.visibility = VISIBLE
            processDialog.dismiss()
//                    }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}