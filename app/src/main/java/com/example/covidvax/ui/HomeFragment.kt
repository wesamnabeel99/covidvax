package com.example.covidvax.ui


import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.example.covidvax.R
import com.example.covidvax.data.domain.VaccineData
import com.example.covidvax.databinding.FragmentHomeBinding
import com.example.covidvax.data.DataManager


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val LOG_TAG: String = "HOME_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentHomeBinding = FragmentHomeBinding::inflate



//region callbacks
    override fun addCallbacks() {
        val world = DataManager.worldStatistics()
        bindDay(world)

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
     * this function shows the dialog for given layout
     * @param layoutId an Int representing the layout id
     * @return Unit
     * @author Akram
     */
    fun showDialog(layoutId: Int) {
        MyDialog = Dialog(requireContext())
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        MyDialog.setContentView(layoutId)
        MyDialog.show()
    }


    /**
     * this function will bind the data of the day into ui
     * @author Wesam N. Shawqi
     * @param data, an object from the DailyData class
     * @return nothing
     */
    private fun bindDay (data: VaccineData) {
        binding?.apply {
            firstDataView.text = "One Dose Vaccinated : ${data.oneDoseVaccinated?.let { DataManager.abbreviateTheNumber(it) }}"
            secondDataView.text = "two Dose Vaccinated : ${data.twoDoseVaccinated?.let { DataManager.abbreviateTheNumber(it) }}"
            thirdDataView.text = "total Vaccinated : ${data.totalPeopleVaccinated?.let { DataManager.abbreviateTheNumber(it) }}"
            forthDataView.text = "percent : ${data.vaccinatedPerHundred?.toLong()}%"        }
    }

//endregion
}