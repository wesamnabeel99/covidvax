package com.example.covidvax.utils

import com.example.covidvax.data.DailyData

class DataParser {
    fun parsing(line:String):DailyData{
    val token = line.split(",")
    return DailyData(
        country = token[Constants.ColumnIndex.COUNTRY],
        date = token[Constants.ColumnIndex.DATE],
        totalPeopleVaccinated = token[Constants.ColumnIndex.TOTAL_PEOPLE_VACCINATED].toDoubleOrNull()
            ?.toInt()?:0,
        oneDoseVaccinated = (token[Constants.ColumnIndex.ONE_DOSE_VACCINATED]).toDoubleOrNull()
            ?.toInt()?:0,
        twoDoseVaccinated =
        token[Constants.ColumnIndex.TWO_DOSE_VACCINATED].toDoubleOrNull()
            ?.toInt()?:0,
        dailyVaccinations = token[Constants.ColumnIndex.DAILY_VACCINATIONS].toIntOrNull()?:0
    )
    }
}