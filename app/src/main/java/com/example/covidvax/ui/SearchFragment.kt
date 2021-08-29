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
            pieChart.isVisible=false
            totalVaccinated.text = "N/A"
            fullyVaccinated.text = "N/A"
            oneDoseVaccinated.text = "N/A"
            lastUpdateText.text = "N/A"
            vaccinatedPerHundred.text = ""
            country.text="not found"
            animation!!.setAnimation(R.raw.notfound)
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
        val countryStatistics = DataManager.countryStatistics(binding?.searchBar?.query!!.toString().lowercase())
        if (countryStatistics!=null) {
            bindTheData(countryStatistics)
            addToPieChart(countryStatistics)
        } else {
            notFound()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}
