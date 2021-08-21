package com.example.covidvax.data

data class VacDay(
    val country:String,
    val date:String,
    val total_vaccinations:Int?, // total = people_vaccinated + people_fully_vaccinated
    val people_vaccinated:Int?,
    val people_fully_vaccinated:Int?,
    val daily_vaccinations:Int?
)
