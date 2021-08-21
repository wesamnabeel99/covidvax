package com.example.covidvax.utils

import com.example.covidvax.data.VacDay

object DataManager {
    // don't take this class, just change your work from DataActivity to HomeActivity
    private val daysList = mutableListOf<VacDay>()
    private var dayIndex = 0


    fun addDay (vacDay:VacDay) {
        daysList.add(vacDay)
    }

    fun getCurrentDay():VacDay = daysList[dayIndex]

    fun getNextDay() :VacDay {
        dayIndex++
        return daysList[dayIndex]
    }
    fun getPrevDay() :VacDay {
        dayIndex--
        return daysList[dayIndex]
    }
}