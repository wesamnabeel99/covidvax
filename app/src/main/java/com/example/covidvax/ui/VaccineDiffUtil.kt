package com.example.olympics.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.covidvax.data.domain.VaccineData

class VaccineDiffUtil(val mOldList : List<VaccineData> , val mNewList : List<VaccineData>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =mOldList.size

    override fun getNewListSize(): Int =mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].country ==mNewList[newItemPosition].country
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
return true
    }


}