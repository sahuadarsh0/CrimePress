package com.techipinfotech.allindiacrimepress.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.techipinfotech.allindiacrimepress.R
import com.techipinfotech.allindiacrimepress.databinding.ActivityLoginBinding
import com.techipinfotech.allindiacrimepress.utils.Resource
import com.techipinfotech.allindiacrimepress.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var userSharedPreferences: SharedPrefs
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_CrimePress)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        i = Intent(this, MainActivity::class.java)

        if (userSharedPreferences["member_id"] != null) {
            startActivity(i)
            finish()
        }

        binding.apply {
            loginButton.setOnClickListener {
                login(username.text.toString(), password.text.toString())
            }
        }
        loginViewModel.memberItem.observe(this,{
            when(it.status){
                Resource.Status.LOADING -> Log.d("asa", "onCreate: loading")
                Resource.Status.SUCCESS -> Log.d("asa", "onCreate: loading")
                Resource.Status.ERROR -> Log.d("asa", "onCreate: loading")

            }
        })
        initBlur()
    }

    private fun login(username: String, password: String) {
        loginViewModel.login(username, password)
    }

    private fun initBlur() {
        val radius = 20f

        val decorView = window.decorView;
        //ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        val rootView = decorView.findViewById<ViewGroup>(android.R.id.content);
        //Set drawable to draw in the beginning of each blurred frame (Optional).
        //Can be used in case your layout has a lot of transparent space and your content
        //gets kinda lost after after blur is applied.
        val windowBackground = decorView.background;

        binding.blurView.setupWith(rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
    }
}