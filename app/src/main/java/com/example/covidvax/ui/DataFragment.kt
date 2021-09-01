package com.example.covidvax.ui

import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.R
import com.example.covidvax.data.DataManager
import com.example.covidvax.databinding.FragmentDataBinding
import com.example.covidvax.ui.adapters.VaccineAdapter
import com.example.covidvax.utils.Property

class DataFragment: BaseFragment<FragmentDataBinding>() {
    override val LOG_TAG: String = "DATA_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDataBinding
            = FragmentDataBinding::inflate

    override fun addCallbacks() {
        val adapter = VaccineAdapter(DataManager.sortCountriesBy(Property.TOTAL))
        binding?.dataRecyclerview?.adapter = adapter
        binding?.chipGroup?.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.total_chip -> {
                    adapter.updateData(DataManager.sortCountriesBy(Property.TOTAL))
                }
                R.id.percent_chip -> {
                    adapter.updateData(DataManager.sortCountriesBy(Property.PERCENT))
                }
                R.id.one_dose_chip -> {
                    adapter.updateData(DataManager.sortCountriesBy(Property.ONE_DOSE))
                }
                R.id.two_dose_chip -> {
                    adapter.updateData(DataManager.sortCountriesBy(Property.TWO_DOSE))
                }
            }
        }

    }



}