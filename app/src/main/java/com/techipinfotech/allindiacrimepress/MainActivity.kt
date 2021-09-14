package com.techipinfotech.allindiacrimepress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_CrimePress)
        setContentView(R.layout.activity_main)
    }
}