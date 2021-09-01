package com.example.covidvax.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidvax.R
import com.example.covidvax.data.domain.VaccineData
import com.example.covidvax.ui.viewholdres.VaccineHolder

class VaccineAdapter(val list: List<VaccineData?>) : RecyclerView.Adapter<VaccineHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vaccine,parent,false)
        return VaccineHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VaccineHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.apply {
            vaccinationDate.text= currentItem?.date
            countryName.text = currentItem?.country?.capitalize()
            totalVaccineNumber.text = currentItem?.totalPeopleVaccinated.toString()
            percentText.text = currentItem?.vaccinatedPerHundred.toString()
        }
    }
}