package com.example.covidvax.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
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
    internal lateinit var btn_pfizer: Button
    internal lateinit var MyDialog: Dialog

    //endregion

    //region onCreate
    @SuppressLint("ResourceType", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Covidvax)
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vaccineInfo()
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

    private fun vaccineInfo() {
        btn_pfizer = findViewById(R.id.test1)
        btn_pfizer.setOnClickListener { showDialog() }

    }

    private fun showDialog() {
        MyDialog = Dialog(this)
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        MyDialog.setContentView(R.layout.pfizer)
        MyDialog.setTitle("Pfizer")
        MyDialog.show()
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
    fun parseTheData(){
        val inputStream = assets.open("country_vaccinations_updated.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = DataParser()
        buffer.forEachLine {
            val day = parser.parsing(it)
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
        transaction.addToBackStack(null)
        transaction.commit()
    }
    //endregion
    
    companion object{
        var LOG_TAG = "HOME_ACTIVITY_DATA"
    }
}