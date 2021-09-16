package com.techipinfotech.allindiacrimepress.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.techipinfotech.allindiacrimepress.R
import com.techipinfotech.allindiacrimepress.databinding.ActivityMainBinding
import com.techipinfotech.allindiacrimepress.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var userSharedPreferences: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_CrimePress)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        userSharedPreferences = SharedPrefs(this, "USER")
        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_account -> {
                    navController.navigate(R.id.navigation_account)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_gallery -> {
                    navController.navigate(R.id.navigation_gallery)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_my_list -> {
                    navController.navigate(R.id.navigation_my_list)
                    return@setOnItemSelectedListener true
                }

                R.id.logout -> {
                    MaterialDialog(this).show {
                        title(text = "Logout?")
                        message(text = "Are you sure want to logout ?")
                        cornerRadius(16f)
                        positiveButton(text = "Yes") { dialog ->
                            userSharedPreferences = SharedPrefs(this@MainActivity, "USER")
                            userSharedPreferences.clearAll()
                            dialog.dismiss()
                            finish()
                        }
                        negativeButton(text = "Cancel") { dialog ->
                            dialog.dismiss()
                        }
                    }
                    return@setOnItemSelectedListener true
                }
                else -> Toast.makeText(this, "Select an item", Toast.LENGTH_SHORT).show()
            }

            false
        }
    }

}