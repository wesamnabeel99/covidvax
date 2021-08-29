package com.example.covidvax.data.domain

data class VaccineData(
    val country:String,
    val date:String,
    val totalPeopleVaccinated:Long?,
    val oneDoseVaccinated:Long?,
    val twoDoseVaccinated:Long?,
    val dailyVaccinations:Long?,
    val vaccinatedPerHundred:Double?
)
