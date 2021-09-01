package com.example.covidvax.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.covidvax.R
import com.example.covidvax.data.DataManager
import com.example.covidvax.data.domain.VaccineData
import com.example.covidvax.databinding.ItemVaccineBinding
import com.example.olympics.ui.VaccineDiffUtil

class VaccineAdapter(var list: List<VaccineData>) : RecyclerView.Adapter<VaccineAdapter.VaccineHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vaccine,parent,false)
        return VaccineHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VaccineHolder, position: Int) {
        var currentItem = list[position]
        holder.binding.apply {
        countryNameTextView.text=currentItem?.country?.capitalize()
        rankTextView.text = "#" + (position+1).toString()
        totalVaccinatedTextView.text = "Total Vaccinated:" + currentItem.totalPeopleVaccinated?.let { DataManager.abbreviateTheNumber(it) }
            oneDoseTextView.text = "One Dose Vaccinated:" + currentItem.oneDoseVaccinated?.let { DataManager.abbreviateTheNumber(it) }
            twoDoseTextView.text = "Two Dose Vaccinated:" + currentItem.twoDoseVaccinated?.let { DataManager.abbreviateTheNumber(it) }
            percentTextView.text = currentItem?.vaccinatedPerHundred.toString() + "% Vaccinated"
        }
    }
    fun updateData(newList:List<VaccineData>) {
        val diffrentResult = DiffUtil.calculateDiff(VaccineDiffUtil(list,newList))
        list = newList
        diffrentResult.dispatchUpdatesTo(this)
    }

    class VaccineHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemVaccineBinding.bind(itemView)
    }
}