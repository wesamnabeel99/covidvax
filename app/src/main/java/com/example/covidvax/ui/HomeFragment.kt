package com.example.covidvax.ui


import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.example.covidvax.R
import com.example.covidvax.data.VaccineData
import com.example.covidvax.databinding.FragmentHomeBinding
import com.example.covidvax.data.DataManager


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val LOG_TAG: String = "HOME_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentHomeBinding = FragmentHomeBinding::inflate



//region callbacks
    override fun addCallbacks() {
        val world = DataManager.worldStatistics()
        bindDay(world!!)

    /**
     * open popup dialog
     * @author Akram
     */
    binding?.pfizerCard?.setOnClickListener{showDialog(R.layout.pfizer)}
    binding?.modernaCard?.setOnClickListener{showDialog(R.layout.moderna)}
    binding?.johnsonCard?.setOnClickListener{showDialog(R.layout.johnson)}
    binding?.sputnikCard?.setOnClickListener{showDialog(R.layout.sputnik)}
    binding?.reasonsToVaccinate?.setOnClickListener { showDialog(R.layout.why_you_should_vaccine) }
    }
    /**
     * dialog function
     * @author Akram
     */
    fun showDialog(vac: Int) {
        MyDialog = Dialog(requireContext())
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        MyDialog.setContentView(vac)
        MyDialog.show()
    }


    /**
     * this function will bind the data of the day into ui
     * @author Wesam N. Shawqi
     * @param data, an object from the DailyData class
     * @return nothing
     */
    private fun bindDay (data:VaccineData) {
        binding?.apply {
            firstDataView.text = "One Dose Vaccinated : ${DataManager.abbreviateTheNumber(data.oneDoseVaccinated!!)}"
            secondDataView.text = "two Dose Vaccinated : ${DataManager.abbreviateTheNumber(data.twoDoseVaccinated!!)}"
            thirdDataView.text = "total Vaccinated : ${DataManager.abbreviateTheNumber(data.totalPeopleVaccinated!!)}"
            forthDataView.text = "percent : ${DataManager.abbreviateTheNumber(data.vaccinatedPerHundred!!.toLong())}%"

        }
    }
//endregion
}