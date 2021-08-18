package com.example.covidvax

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.covidvax.databinding.FragmentSearchBinding

class SearchFragment: BaseFragment<FragmentSearchBinding>() {
    override val LOG_TAG: String = "SEARCH_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding = FragmentSearchBinding::inflate

    override fun addCallbacks() {
        //write your callbacks code here
    }
}