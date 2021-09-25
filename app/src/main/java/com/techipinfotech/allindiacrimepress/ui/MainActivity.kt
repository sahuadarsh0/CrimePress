package com.techipinfotech.allindiacrimepress.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.techipinfotech.allindiacrimepress.R
import com.techipinfotech.allindiacrimepress.databinding.ActivityMainBinding
import com.techipinfotech.allindiacrimepress.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    @Inject lateinit var userSharedPreferences: SharedPrefs
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_CrimePress)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = binding.appBarMain.bottomNavView
        navController = findNavController(R.id.nav_host_fragment_content_main)
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_account -> {
                    navController.navigate(R.id.navigation_account)
                    userSharedPreferences["member_id_temp"] =  userSharedPreferences["member_id"]
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


//        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title =""


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_gallery,
                R.id.navigation_my_list, R.id.logout

            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}