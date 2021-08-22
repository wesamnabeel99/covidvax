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

//region callbacks
    override fun addCallbacks() {

    }

    /**
     * this function will bind the data of the day into ui
     * @author Wesam N. Shawqi
     * @param day, an object from the DailyData class
     * @return nothing
     */
    private fun bindDay (day:DailyData) {
        binding?.apply {
        }
    }
//endregion
}