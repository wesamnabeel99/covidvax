package com.example.covidvax.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.example.covidvax.R
import com.example.covidvax.data.domain.VaccineData
import com.example.covidvax.databinding.FragmentSearchBinding
import com.example.covidvax.data.DataManager
import org.eazegraph.lib.models.PieModel

class SearchFragment: BaseFragment<FragmentSearchBinding>(), SearchView.OnQueryTextListener {
    override val LOG_TAG: String = "SEARCH_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding = FragmentSearchBinding::inflate
    override fun addCallbacks() {
    binding?.searchBar!!.setOnQueryTextListener(this)
    }

    private fun bindTheData(lastDay: VaccineData) {
        binding?.apply {
            totalVaccinated.text = lastDay.totalPeopleVaccinated?.let { DataManager.abbreviateTheNumber(it) }
            fullyVaccinated.text = lastDay.twoDoseVaccinated?.let { DataManager.abbreviateTheNumber(it) }
            oneDoseVaccinated.text = lastDay.oneDoseVaccinated?.let { DataManager.abbreviateTheNumber(it) }
            lastUpdateText.text = "last update on : ${lastDay.date}"
            country.text = lastDay.country.capitalize()
            vaccinatedPerHundred.text = "${lastDay.vaccinatedPerHundred}%"
           playAnimation(lastDay)
        }
    }
    private fun showNotFoundStatus() {
        binding?.apply {
            pieChart.isVisible=false
            totalVaccinated.text = "N/A"
            fullyVaccinated.text = "N/A"
            oneDoseVaccinated.text = "N/A"
            lastUpdateText.text = "N/A"
            vaccinatedPerHundred.text = ""
            country.text="not found"
            animation.setAnimation(R.raw.notfound)
            animation.playAnimation()
        }
    }

    private fun addToPieChart(day: VaccineData) {
        binding?.apply {
            pieChart.isVisible=true
            pieChart.clearChart()
//            pieChart.addPieSlice(PieModel("total vaccination", day.totalPeopleVaccinated!!.toFloat(),Color.parseColor("#3F51B5")))
            pieChart.addPieSlice(PieModel("two dose vaccination", day.twoDoseVaccinated!!.toFloat(),Color.parseColor("#C3DC2D2D")))
            pieChart.addPieSlice(PieModel("one dose vaccination", day.oneDoseVaccinated!!.toFloat(),Color.parseColor("#2196F3")))
            pieChart.startAnimation()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val countryStatistics = DataManager.calculateCountryStatistics(binding?.searchBar?.query!!.toString().lowercase())
        if (countryStatistics.country!="none") {
            bindTheData(countryStatistics)
            addToPieChart(countryStatistics)
        } else {
            showNotFoundStatus()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun playAnimation(lastDay :VaccineData) {
        when {
            lastDay.vaccinatedPerHundred!!<30.0 -> {
                binding?.animation?.setAnimation(R.raw.belowthirty)
                binding?.animation?.playAnimation()
            }
            lastDay.vaccinatedPerHundred>=30.0&&lastDay.vaccinatedPerHundred<70.0 -> {
                binding?.animation?.setAnimation(R.raw.vaccinating)
                binding?.animation?.playAnimation()
            }
            else -> {
                binding?.animation?.setAnimation(R.raw.fullyvaccinated)
                binding?.animation?.playAnimation()
            }
        }
    }
}
