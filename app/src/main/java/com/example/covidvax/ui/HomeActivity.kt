package com.example.covidvax.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.covidvax.R
import com.example.covidvax.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val dataFragment = DataFragment()
    private val aboutFragment = AboutFragment()

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Covidvax)
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()

    }

    /**
     * setup HomeActivity onCreate function
     */
    fun setup(){
        addFragment(homeFragment)
        addNavigationListner()
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.clickMe.setOnClickListener {
            startActivity(Intent(this,DataActivity::class.java))
        }
    }

    private fun addNavigationListner() {
        binding.bottomNavBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_button -> {
                    replaceFragment(homeFragment)
                    true
                }
                R.id.search_button -> {
                    replaceFragment(searchFragment)
                    true
                }
                R.id.data_button -> {
                    replaceFragment(dataFragment)
                    true
                }
                R.id.about_button -> {
                    replaceFragment(aboutFragment)
                    true
                }
                else -> false
            }

        }
    }

    /**
     * add fragment to the main activity
     */
    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container,fragment)
        transaction.commit()
    }

    /**
     * rplace the existed fragment with the given fragment
     */
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
}