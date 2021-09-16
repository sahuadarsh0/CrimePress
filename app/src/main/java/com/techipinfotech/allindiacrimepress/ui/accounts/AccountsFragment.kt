package com.techipinfotech.allindiacrimepress.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.techipinfotech.allindiacrimepress.databinding.FragmentsAccountsBinding

class AccountsFragment : Fragment() {


    private lateinit var accountsViewModel: AccountsViewModel
    private var _binding: FragmentsAccountsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountsViewModel = ViewModelProvider(this).get(AccountsViewModel::class.java)

        _binding = FragmentsAccountsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}