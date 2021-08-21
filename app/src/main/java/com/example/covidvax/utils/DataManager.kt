package com.example.covidvax.utils

import com.example.covidvax.data.DailyData

object DataManager {
    private val daysList = mutableListOf<DailyData>()
    private var dayIndex = 0

    fun addDay (dailyData:DailyData) {
        daysList.add(dailyData)
    }

    fun getCurrentDay():DailyData = daysList[dayIndex]

    fun getNextDay() :DailyData {
        dayIndex++
        return daysList[dayIndex]
    }
    fun getPrevDay() :DailyData {
        dayIndex--
        return daysList[dayIndex]
    }
}