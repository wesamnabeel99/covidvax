package com.example.covidvax.utils

import android.util.Log
import com.example.covidvax.data.DailyData

object DataManager {
    //region initialize variables
    val daysList = mutableListOf<DailyData>()
    val countriesSet = mutableSetOf<String>() // sets don't allow duplication so we use it to store countries names
    val dataMap = mutableMapOf<String,MutableList<DailyData>>()
    //endregion

    //region data initialization
    /**
     * this function will add day data to the daysList and add country name to the countriesSet
     * the set will have the country name once, because duplication isn't allowed in sets
     * @param DailyData object , it should be passed as an argument for each line after parsing the data
     * @return Unit
     * @author Mohammed Zalzala , Wesam N. Shawqi
     */
    fun addDay (dailyData:DailyData) {
        daysList.add(dailyData)
        countriesSet.add(dailyData.country)
    }
    //endregion

    //region data mapping
    /**
     * this function will map each country to its list by putting the country in the key and the list in the value
     * @param Unit
     * @return Unit
     * @author  Wesam N. Shawqi
     */
    fun mapTheData () {
        countriesSet.forEach() {
            dataMap.put (it.lowercase(), filterListByCountry(it))
        }
    }
    /**
     * @param country:String
     * @return list of data entries for the gven country
     * @author  Mohammed Zalzala
     */
    private fun filterListByCountry(country:String) = daysList.filter { it.country == country } as MutableList<DailyData>
    //endregion

    //region search functions
    /**
     * @param country:String
     * @return the last statistics as an object for the given country
     * @author  Wesam N. Shawqi
     */
    fun getLastStatistics (country: String): DailyData? {
        if (country in dataMap.keys) {
            return dataMap[country]!!.last()
        }
        else return null
    }
    //endregion
}