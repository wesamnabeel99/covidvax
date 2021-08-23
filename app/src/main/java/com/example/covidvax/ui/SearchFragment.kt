package com.example.covidvax.ui

import android.content.Context
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieDrawable
import com.example.covidvax.R
import com.example.covidvax.data.VaccineData
import com.example.covidvax.databinding.FragmentSearchBinding
import com.example.covidvax.utils.DataManager

class SearchFragment: BaseFragment<FragmentSearchBinding>() {
    override val LOG_TAG: String = "SEARCH_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding = FragmentSearchBinding::inflate

    override fun addCallbacks() {
    binding?.searchButton!!.setOnClickListener {
        val countryStatistics =DataManager.countryStatistics(binding?.searchBar?.text!!.toString().lowercase())
        if (countryStatistics!=null) {
            bindTheData(countryStatistics)
        } else {
            notFound()
        }

    }
    }

    private fun bindTheData(lastDay:VaccineData) {
        binding?.apply {
            totalVaccinated.text = DataManager.roundTheNumber(lastDay.totalPeopleVaccinated!!)
            fullyVaccinated.text = DataManager.roundTheNumber(lastDay.twoDoseVaccinated!!)
            dailyVaccinated.text = DataManager.roundTheNumber(lastDay.dailyVaccinations!!)
            lastUpdateText.text = "last update on : ${lastDay.date}"
            country.text = lastDay.country.capitalize()
            vaccinatedPerHundred.text = "${lastDay.vaccinatedPerHundred}%"
            when {
                lastDay.vaccinatedPerHundred!!<30.0 -> {
                    animation.setAnimation(R.raw.belowthirty)
                    animation.playAnimation()
                }
                lastDay.vaccinatedPerHundred>=30.0&&lastDay.vaccinatedPerHundred<70.0 -> {
                    animation.setAnimation(R.raw.vaccinating)
                    animation.playAnimation()
                }
                else -> {
                    animation.setAnimation(R.raw.fullyvaccinated)
                    animation.playAnimation()
                }
            }
        }
    }
    private fun notFound() {
        binding?.apply {
            totalVaccinated.text = "N/A"
            fullyVaccinated.text = "N/A"
            dailyVaccinated.text = "N/A"
            lastUpdateText.text = "N/A"
            vaccinatedPerHundred.text = ""
            country.text="not found"
            animation!!.setAnimation(R.raw.notfound)
            animation.playAnimation()
        }    }}
