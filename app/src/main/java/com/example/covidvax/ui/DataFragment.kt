package com.example.covidvax.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.data.DataManager
import com.example.covidvax.data.domain.VaccineData
import com.example.covidvax.databinding.FragmentDataBinding
import com.example.covidvax.ui.BaseFragment
import com.example.covidvax.ui.adapters.VaccineAdapter

class DataFragment: BaseFragment<FragmentDataBinding>() {
    override val LOG_TAG: String = "DATA_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDataBinding
            = FragmentDataBinding::inflate

    override fun addCallbacks() {

        val adapter = VaccineAdapter(DataManager.wordList)
        binding?.dataRecyclerview?.adapter = adapter

    }
}