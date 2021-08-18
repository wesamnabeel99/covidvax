package com.example.covidvax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.covidvax.databinding.ActivityHomeBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class HomeActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
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