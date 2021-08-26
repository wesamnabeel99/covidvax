package com.example.covidvax.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.data.VaccineData
import com.example.covidvax.databinding.FragmentHomeBinding
import com.example.covidvax.utils.DataManager


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val LOG_TAG: String = "HOME_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentHomeBinding = FragmentHomeBinding::inflate

//region callbacks
    override fun addCallbacks() {
        val world = DataManager.calculateWorldStatistics()
        world?.let { bindDay(it) }
    }




    /**
     * this function will bind the data of the day into ui
     * @author Wesam N. Shawqi
     * @param data, an object from the DailyData class
     * @return nothing
     */
    private fun bindDay (data:VaccineData) {
        binding?.apply {
                firstDataView.text = "One Dose Vaccinated : ${data.oneDoseVaccinated?.let { DataManager.abbreviateTheNumber(it) }}"
                secondDataView.text = "two Dose Vaccinated : ${data.twoDoseVaccinated?.let { DataManager.abbreviateTheNumber(it) }}"
                thirdDataView.text = "total Vaccinated : ${data.totalPeopleVaccinated?.let { DataManager.abbreviateTheNumber(it) }}"
                forthDataView.text = "percent : ${data.vaccinatedPerHundred?.toLong()}%"
        }
    }
//endregion
}