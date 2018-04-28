package com.tehreh1uneh.weathertestcase.model.caching

import com.tehreh1uneh.weathertestcase.model.data.WeatherDayInfo

/**
 * Declares possible caching functions
 */
interface ICachingHelper {

    /**
     * Caches the last selected city
     * @param city city name
     */
    fun cacheLastCity(city: String)

    /**
     * Caches the last displayed weather list
     * @param list collection with information about the weather
     */
    fun cacheLastList(list: List<WeatherDayInfo>)

    /**
     * @return the last selected city
     */
    fun getLastCityFromCache(): String?

    /**
     * @return the last displayed weather list
     */
    fun getLastListFromCache(): List<WeatherDayInfo>?
}
