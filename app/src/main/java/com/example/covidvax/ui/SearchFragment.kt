package com.example.covidvax.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.databinding.FragmentSearchBinding
import com.example.covidvax.ui.BaseFragment

class SearchFragment: BaseFragment<FragmentSearchBinding>() {
    override val LOG_TAG: String = "SEARCH_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding = FragmentSearchBinding::inflate

    override fun addCallbacks() {
        //write your callbacks code here
    }
}