package com.example.covidvax.utils

import android.util.Log
import com.example.covidvax.data.DailyData

object DataManager {
    val daysList = mutableListOf<DailyData>()
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

    fun lastIndexOfFilteredList(country:String) = daysList.findLast { it.country == country }


    fun filteredListByCountry(country:String) = daysList.filter { it.country == country }

}