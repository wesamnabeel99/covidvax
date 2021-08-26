package com.example.covidvax.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.covidvax.R
import com.example.covidvax.databinding.ActivityHomeBinding
import com.example.covidvax.utils.DataParser
import com.example.covidvax.utils.DataManager
import java.io.BufferedReader
import java.io.InputStreamReader

class HomeActivity : AppCompatActivity() {

    //region initilize variables
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val dataFragment = DataFragment()
    private val aboutFragment = AboutFragment()
    lateinit var binding: ActivityHomeBinding
    //endregion

    //region onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Covidvax)
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }
    //endregion

    //region setup
    /**
     * setup HomeActivity onCreate function
     * @author Karrar Mohammed
     */
    fun setup(){
        addFragment(homeFragment)
        addNavigationListner()
        parseTheData()
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
     * open the file and parse the data
     * @author Mohammed Zalzala
     */
    private fun parseTheData(){
        val inputStream = assets.open("country_vaccinations_updated.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = DataParser()
        buffer.forEachLine {
            val day = parser.parseTheData(it)
            DataManager.addDay(day)
        }
        DataManager.mapTheData()
    }
    //endregion

    //region add fragments
    /**
     * add fragment to the home activity
     * @author Karrar Mohammed
     */
    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container,fragment)
        transaction.commit()
    }

    /**
     * replace the existed fragment with the given fragment
     * @param fragment:Fragment
     * @author Karrar Mohammed
     */
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
    //endregion
    
    companion object{
        var LOG_TAG = "HOME_ACTIVITY_DATA"
    }
}