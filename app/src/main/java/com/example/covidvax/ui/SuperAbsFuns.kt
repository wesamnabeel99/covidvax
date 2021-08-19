package com.example.covidvax.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

//This class contains all the abstract functions that is used for data parsing
abstract class SuperAbsFuns<vb:ViewBinding>:AppCompatActivity() {

    //defining vars which are LOG_TAG, Binding and binding inflater to be used as an inflater
    abstract val LOG_TAG:String
    private lateinit var _binding :vb
    abstract val bindingInflater : (LayoutInflater) -> vb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(_binding.root)

        //calling abstract functions to be executed
        openData()
        initializeCallBack()
    }

    abstract fun initializeCallBack()

    abstract fun openData()

    protected fun logV(str:String){
        Log.v(LOG_TAG,str)
    }
}