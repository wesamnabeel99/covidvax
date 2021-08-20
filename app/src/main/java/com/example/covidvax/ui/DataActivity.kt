package com.example.covidvax.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.covidvax.R
import com.example.covidvax.databinding.ActivityTestBinding
import com.example.covidvax.utils.CSVParser
import java.io.BufferedReader
import java.io.InputStreamReader

class DataActivity : SuperAbsFuns<ActivityTestBinding>() {
    override val LOG_TAG: String
        get() = "ActivityTestBinding"
    override val bindingInflater: (LayoutInflater) -> ActivityTestBinding =
        ActivityTestBinding::inflate

    override fun initializeCallBack() {

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
            logV(day)
        }


    }
}