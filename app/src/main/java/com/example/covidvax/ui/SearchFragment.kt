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
    val result = DataManager.countryStatistics(binding?.editText!!.text.toString().lowercase())
        TransitionManager.beginDelayedTransition(binding?.root)
        when (result) {
            null -> {
                notFound()
            }
            else -> {
                bindTheData(result)
            }
        }
    }
    }

    private fun bindTheData(lastDay:VaccineData) {
        binding?.apply {
            totalVaccinated.text = lastDay.totalPeopleVaccinated.toString()
            fullyVaccinated.text = lastDay.twoDoseVaccinated.toString()
            oneDoseVaccinated.text = lastDay.oneDoseVaccinated.toString()
        }
    }
    private fun notFound() {
        binding?.apply {
            totalVaccinated.text = "notFound"
            fullyVaccinated.text = "notFound"
            oneDoseVaccinated.text = "notFound"
        }
    }
}