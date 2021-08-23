package com.example.covidvax.utils

import com.example.covidvax.data.VaccineData

class DataParser {
    fun parsing(line:String):VaccineData{
    val token = line.split(",")
    return VaccineData(
        country = token[Constants.ColumnIndex.COUNTRY],
        date = token[Constants.ColumnIndex.DATE],
        totalPeopleVaccinated = token[Constants.ColumnIndex.TOTAL_PEOPLE_VACCINATED].toDoubleOrNull()
            ?.toLong()?:0,
        oneDoseVaccinated = (token[Constants.ColumnIndex.ONE_DOSE_VACCINATED]).toDoubleOrNull()
            ?.toLong()?:0,
        twoDoseVaccinated =
        token[Constants.ColumnIndex.TWO_DOSE_VACCINATED].toDoubleOrNull()
            ?.toLong()?:0,
        dailyVaccinations = token[Constants.ColumnIndex.DAILY_VACCINATIONS].toIntOrNull()?:0
    )
    }
}