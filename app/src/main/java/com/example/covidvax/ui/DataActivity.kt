package com.example.covidvax.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.covidvax.R
import com.example.covidvax.data.VacDay
import com.example.covidvax.databinding.ActivityTestBinding
import com.example.covidvax.databinding.FragmentDataBinding
import com.example.covidvax.utils.CSVParser
import com.example.covidvax.utils.DataManager
import java.io.BufferedReader
import java.io.InputStreamReader

class DataActivity : SuperAbsFuns<FragmentDataBinding>() {
    override val LOG_TAG: String
        get() = "ActivityTestBinding"
    override val bindingInflater: (LayoutInflater) -> FragmentDataBinding =
        FragmentDataBinding::inflate

    override fun initializeCallBack() {
    binding?.apply {
        after.setOnClickListener{
           bindDay(DataManager.getNextDay())
        }
        previous.setOnClickListener{
            bindDay(DataManager.getPrevDay())
        }
    }
    }

    override fun openStart() {
        openFile()
    }

    private fun openFile() {
        val inputStream = assets.open("country_vaccinations.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CSVParser()
        buffer.forEachLine {
            val day = parser.parsing(it)
            DataManager.addDay((day))
        }
    bindDay(DataManager.getCurrentDay())
    }

    private fun bindDay (day:VacDay) {
        binding?.apply {
            country.text = day.country
            date.text = day.date
            today.text=day.daily_vaccinations.toString()
            total.text=day.total_vaccinations.toString()
            fully.text=day.people_fully_vaccinated.toString()
        }
    }
}