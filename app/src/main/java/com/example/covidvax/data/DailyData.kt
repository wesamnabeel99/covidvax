package com.example.covidvax.data

data class DailyData(
    val country:String,
    val date:String,
    val totalPeopleVaccinated:Int?,
    val oneDoseVaccinated:Int?,
    val twoDoseVaccinated:Int?,
    val dailyVaccinations:Int?
)
