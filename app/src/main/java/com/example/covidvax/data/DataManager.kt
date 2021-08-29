package com.example.covidvax.data

import com.example.covidvax.data.domain.VaccineData

object DataManager {
    //region initialize variables
    val daysList = mutableListOf<VaccineData>()
    val countriesSet = mutableSetOf<String>() // sets don't allow duplication so we use it to store countries names
    val countriesMap = mutableMapOf<String,MutableList<VaccineData>>()
    //endregion
    val vaccineListData: List<VaccineData>
    get() = daysList
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
            countriesMap.put (it.lowercase(), filterListByCountry(it))
        }
    }
    /**
     * @param country:String
     * @return list of data entries for the gven country
     * @author  Mohammed Zalzala
     */
    private fun filterListByCountry(country:String) = daysList.filter { it.country == country } as MutableList<VaccineData>
    //endregion

    //region filter functions
    /**
     * @param country:String
     * @return the last statistics as an object for the given country
     * @author  Wesam N. Shawqi, Karrar Mohammed
     */
    fun countryStatistics (country: String): VaccineData? {
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
        else return null
    }

    /**
     * this function to calculate the vaccine statistics of the world
     * @param no parameter
     * @return the total statistics as an object for the world
     * @author  Wesam N. Shawqi, Karrar M. Habeeb
     */
    fun worldStatistics(): VaccineData? {
        var worldTotalVaccinations: Long = 0
        var worldOneDoseVaccination: Long = 0
        var worldTwoDoseVaccination: Long = 0
        var averageVaccinatedPerHundred:Double=0.0
        countriesMap.forEach {
            val countryData = countryStatistics(it.key)
            worldTotalVaccinations += countryData?.totalPeopleVaccinated!!
            worldOneDoseVaccination += countryData.oneDoseVaccinated!!
            worldTwoDoseVaccination += countryData.twoDoseVaccinated!!
            averageVaccinatedPerHundred += countryData.vaccinatedPerHundred!!
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

        //endregion
}