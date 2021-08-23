package com.example.covidvax.ui

import android.transition.TransitionManager
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
        DataManager.countryStatistics(binding?.searchBar?.text!!.toString())?.let { it1 ->
            bindTheData(
                it1
            )
        }
    }
    }

    private fun bindTheData(lastDay:VaccineData) {
        binding?.apply {
            totalVaccinated.text = lastDay.totalPeopleVaccinated.toString()
            fullyVaccinated.text = lastDay.twoDoseVaccinated.toString()
            dailyVaccinated.text = lastDay.dailyVaccinations.toString()
        }
    }
    private fun notFound() {
//        binding?.apply {
//            totalVaccinated.text = "notFound"
//            fullyVaccinated.text = "notFound"
//            oneDoseVaccinated.text = "notFound"
       }
    }
