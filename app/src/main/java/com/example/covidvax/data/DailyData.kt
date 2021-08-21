package com.example.covidvax.data

data class DailyData(
    val country:String,
    val date:String,
    val totalPeopleVaccinated:Int?,
    val peopleVaccinated:Int?,
    val peopleFullyVaccinated:Int?,
    val dailyVaccinations:Int?
)
