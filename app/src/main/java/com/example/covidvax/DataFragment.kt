package com.example.covidvax

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.databinding.FragmentDataBinding

class DataFragment: BaseFragment<FragmentDataBinding>() {
    override val LOG_TAG: String = "DATA_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDataBinding = FragmentDataBinding::inflate

    override fun addCallbacks() {
        //write your callbacks code here
    }
}