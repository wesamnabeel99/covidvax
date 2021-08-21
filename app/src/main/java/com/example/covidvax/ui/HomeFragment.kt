package com.example.covidvax.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.databinding.FragmentHomeBinding
import com.example.covidvax.ui.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String = "HOME_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding = FragmentHomeBinding::inflate

    override fun addCallbacks() {
        //write your callbacks code here
    }


}