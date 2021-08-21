package com.example.covidvax.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.data.DailyData
import com.example.covidvax.databinding.FragmentHomeBinding
import com.example.covidvax.utils.DataManager


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String = "HOME_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentHomeBinding = FragmentHomeBinding::inflate

    override fun addCallbacks() {
        bindDay (DataManager.getCurrentDay())
        binding!!.next.setOnClickListener {
            bindDay(DataManager.getNextDay())
        }
        binding!!.previous.setOnClickListener {
            bindDay(DataManager.getPrevDay())
        }
    }

    /**
     * this function will bind the data of the day into ui
     * @author Wesam N. Shawqi
     * @param day, an object from the DailyData class
     * @return nothing
     */
    private fun bindDay (day:DailyData) {
        binding?.apply {
            textView.text = DataManager.getCurrentDay().date
            fullyVaccinated.text = DataManager.getCurrentDay().peopleFullyVaccinated.toString()
            dailyVaccinated.text = DataManager.getCurrentDay().dailyVaccinations.toString()
        }
    }

}