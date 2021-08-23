package com.example.covidvax.ui

import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.data.VaccineData
import com.example.covidvax.databinding.FragmentSearchBinding
import com.example.covidvax.utils.DataManager

class SearchFragment: BaseFragment<FragmentSearchBinding>() {
    override val LOG_TAG: String = "SEARCH_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding = FragmentSearchBinding::inflate

    override fun addCallbacks() {
    binding?.searchButton!!.setOnClickListener {
        DataManager.countryStatistics(binding?.searchBar?.text!!.toString().lowercase())?.let {
            TransitionManager.beginDelayedTransition(binding?.root)
            bindTheData(
                it
            )
        }
    }
    }

    private fun bindTheData(lastDay:VaccineData) {
        binding?.apply {
            totalVaccinated.text = DataManager.roundTheNumber(lastDay.totalPeopleVaccinated!!)
            fullyVaccinated.text = DataManager.roundTheNumber(lastDay.twoDoseVaccinated!!)
            dailyVaccinated.text = DataManager.roundTheNumber(lastDay.dailyVaccinations!!)
            lastUpdateText.text = "last update on : ${lastDay.date}"
            vaccinatedPerHundred.text = "${lastDay.vaccinatedPerHundred}%"
        }
    }
    private fun notFound() {
//        binding?.apply {
//            totalVaccinated.text = "notFound"
//            fullyVaccinated.text = "notFound"
//            oneDoseVaccinated.text = "notFound"
       }
    }
