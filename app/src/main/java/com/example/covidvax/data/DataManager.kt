package com.example.covidvax.data

import com.example.covidvax.data.domain.VaccineData
import com.example.covidvax.utils.Property

object DataManager {
    //region initialize variables
    private val daysList = mutableListOf<VaccineData>()
    private val countriesSet = mutableSetOf<String>() // sets don't allow duplication so we use it to store countries names
    private val countriesMap = mutableMapOf<String,MutableList<VaccineData>>()
    val worldList = mutableListOf<VaccineData>()

    val countries : List<VaccineData>
    get() = countries.toList()

    //endregion

    //region data initialization
    /**
     * this function will add day data to the daysList and add country name to the countriesSet
     * the set will have the country name once, because duplication isn't allowed in sets
     * @param DailyData object , it should be passed as an argument for each line after parsing the data
     * @return Unit
     * @author Mohammed Zalzala , Wesam N. Shawqi
     */
    fun addDay (vaccineData: VaccineData) {
        daysList.add(vaccineData)
        countriesSet.add(vaccineData.country)
    }
    /**
     * this function will add the last statistics of countries to worldList
     * @param none
     * @return Unit
     * @author  Basheer Sameer
     */
    fun calculateCountryTotal() {
        countriesSet.forEach{
            worldList.add(calculateCountryStatistics(it.lowercase()))
        }

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
            countriesMap[it.lowercase()] = filterListByCountry(it)
        }
    }
    /**
     * @param country:String
     * @return list of data entries for the gven country
     * @author  Mohammed Zalzala
     */
    fun filterListByCountry(country:String) = daysList.filter { it.country == country } as MutableList<VaccineData>
    //endregion

    //region filter functions
    /**
     * @param country:String
     * @return the last statistics as an object for the given country
     * @author  Wesam N. Shawqi, Karrar Mohammed
     */
    fun calculateCountryStatistics (country: String): VaccineData {
        if (country in countriesMap.keys) {
            return VaccineData(
                country = country,
                date = countriesMap[country]!!.sortedBy { it.date }.last().date,
                totalPeopleVaccinated = countriesMap[country]!!.sortedBy { it.totalPeopleVaccinated }.last().totalPeopleVaccinated,
                oneDoseVaccinated = countriesMap[country]!!.sortedBy { it.oneDoseVaccinated }.last().oneDoseVaccinated,
                twoDoseVaccinated = countriesMap[country]!!.sortedBy { it.twoDoseVaccinated }.last().twoDoseVaccinated,
                dailyVaccinations = countriesMap[country]!!.sortedBy { it.dailyVaccinations }.last().dailyVaccinations,
                vaccinatedPerHundred = countriesMap[country]!!.sortedBy { it.vaccinatedPerHundred }.last().vaccinatedPerHundred
            )
        }
        else return VaccineData ("none","none",3,5,67,2,4.54)
    }

    /**
     * this function to calculate the vaccine statistics of the world
     * @param no parameter
     * @return the total statistics as an object for the world
     * @author  Wesam N. Shawqi, Karrar M. Habeeb
     */
    fun worldStatistics(): VaccineData {
        var worldTotalVaccinations: Long = 0
        var worldOneDoseVaccination: Long = 0
        var worldTwoDoseVaccination: Long = 0
        var averageVaccinatedPerHundred:Double=0.0
        worldList.forEach {
            worldTotalVaccinations+= it?.totalPeopleVaccinated!!
            worldOneDoseVaccination +=it.oneDoseVaccinated!!
            worldTwoDoseVaccination +=it.twoDoseVaccinated!!
            averageVaccinatedPerHundred +=it.vaccinatedPerHundred!!
        }
        averageVaccinatedPerHundred/= countriesSet.size
        return VaccineData(
            country = "World",
            date = "all time",
            totalPeopleVaccinated = worldTotalVaccinations,
            oneDoseVaccinated = worldOneDoseVaccination,
            twoDoseVaccinated = worldTwoDoseVaccination,
            dailyVaccinations = 0,
            vaccinatedPerHundred = averageVaccinatedPerHundred
        )
    }

    /**
     * this function will make the number as an abbreviated string
     * @author aioobe
     * @param Long number representing the data
     * @return abbreviated string of the string
     * source https://stackoverflow.com/questions/9769554/how-to-convert-number-into-k-thousands-m-million-and-b-billion-suffix-in-jsp
     */
    fun abbreviateTheNumber(number:Long):String {
        if (number < 1000) return "" + number
        val exp = (Math.log(number.toDouble()) / Math.log(1000.0)).toInt()
        return java.lang.String.format(
            "%.1f %c", //format the apperance of the number
            number / Math.pow(1000.0, exp.toDouble()),
            "kMB"[exp - 1]
        )
    }

    /**
     * this function sort the countries descending by the giving proberty
     * @param property : Property to sort the list by it
     * @return List of sorted countries
     * @author Wesam N. Shawqi
     */
    fun sortCountriesBy (property : Property) :List<VaccineData> = when (property) {
        Property.TOTAL -> {
            worldList.sortedByDescending { it.totalPeopleVaccinated?.let { it } }
        }
        Property.ONE_DOSE -> {
            worldList.sortedByDescending { it.oneDoseVaccinated?.let { it } }
        }
        Property.TWO_DOSE -> {
            worldList.sortedByDescending { it.twoDoseVaccinated?.let {it} }
        }
        Property.PERCENT -> {
            worldList.sortedByDescending { it.vaccinatedPerHundred?.let { it } }
        }
    }


    //endregion
}