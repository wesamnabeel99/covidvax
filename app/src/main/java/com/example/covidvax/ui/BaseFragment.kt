package com.example.covidvax.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding> : Fragment() {


    //region initialize variables
    internal lateinit var MyDialog: Dialog
    abstract val LOG_TAG : String
    abstract val bindingInflater: (LayoutInflater,ViewGroup?, Boolean ) -> VB
    private var _binding: ViewBinding? = null
    protected val binding
    get() = _binding as VB?
    //endregion

    //region fragment creation override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = bindingInflater(inflater,container,false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCallbacks()
    }
    //endregion

    //region abstract functions
    abstract fun addCallbacks()
    //endregion

    protected fun log(value: String){
        Log.v(LOG_TAG,value)
    }


}