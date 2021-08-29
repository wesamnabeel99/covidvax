package com.example.covidvax.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.databinding.FragmentDataBinding
import com.example.covidvax.ui.adapters.VaccineAdapter
import com.example.covidvax.data.DataManager

class DataFragment: BaseFragment<FragmentDataBinding>() {
    override val LOG_TAG: String = "DATA_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDataBinding
            = FragmentDataBinding::inflate

    override fun addCallbacks() {
        getData()
    }


    private fun getData(){
        val adapter = VaccineAdapter(DataManager.vaccineListData)
        binding?.dataRecyclerview?.adapter = adapter
    }
}