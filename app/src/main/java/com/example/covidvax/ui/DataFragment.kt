package com.example.covidvax.ui

import android.os.Bundle
import android.os.ParcelFileDescriptor.open
import android.system.Os.open
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.covidvax.databinding.FragmentDataBinding
import com.example.covidvax.ui.BaseFragment
import com.example.covidvax.ui.adapters.VaccineAdapter
import com.example.covidvax.utils.DataManager
import com.example.covidvax.utils.DataParser
import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.FileChannel.open

class DataFragment: BaseFragment<FragmentDataBinding>() {
    override val LOG_TAG: String = "DATA_FRAGMENT"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDataBinding
            = FragmentDataBinding::inflate

    override fun addCallbacks() {
        getData()
    }

    override fun setup(){

    }
    private fun getData(){
        val adapter = VaccineAdapter(DataManager.vaccineListData)
        binding?.dataRecyclerview?.adapter = adapter
    }
}