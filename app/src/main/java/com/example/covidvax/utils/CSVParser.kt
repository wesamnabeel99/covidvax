package com.example.covidvax.utils

import com.example.covidvax.data.VacDay

class CSVParser {
    fun parsing(line:String):VacDay{
        val token = line.split(",")

        return VacDay(
            country = token[Constants.RowIndex.country],
            date = token[Constants.RowIndex.date],
            total_vaccinations = token[Constants.RowIndex.total_vaccinations].toDoubleOrNull()
                ?.toInt(),
            people_vaccinated = token[Constants.RowIndex.people_vaccinated].toDoubleOrNull()
                ?.toInt(),
            people_fully_vaccinated =
            token[Constants.RowIndex.people_fully_vaccinated].toDoubleOrNull()
                ?.toInt(),
            daily_vaccinations = token[Constants.RowIndex.daily_vaccinations].toIntOrNull()
        )
    }
}