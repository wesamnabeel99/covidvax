package com.example.covidvax.ui

import android.view.LayoutInflater
import android.view.ViewGroup
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
            totalVaccinated.text = DataManager.abbreviateTheNumber(lastDay.totalPeopleVaccinated!!)
            fullyVaccinated.text = DataManager.abbreviateTheNumber(lastDay.twoDoseVaccinated!!)
            oneDoseVaccinated.text = DataManager.abbreviateTheNumber(lastDay.oneDoseVaccinated!!)
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
            oneDoseVaccinated.text = "N/A"
            lastUpdateText.text = "N/A"
            vaccinatedPerHundred.text = ""
            country.text="not found"
            animation!!.setAnimation(R.raw.notfound)
            animation.playAnimation()
        }    }}
