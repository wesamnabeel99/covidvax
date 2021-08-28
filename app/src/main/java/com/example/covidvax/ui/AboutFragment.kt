package com.example.covidvax.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.databinding.FragmentAboutBinding

class AboutFragment: BaseFragment<FragmentAboutBinding>() {
    override val LOG_TAG: String = "ABOUT_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAboutBinding =
        FragmentAboutBinding::inflate

    override fun addCallbacks() {
        //write your callbacks code here
    }

    override fun setup() {
        TODO("Not yet implemented")
    }
}