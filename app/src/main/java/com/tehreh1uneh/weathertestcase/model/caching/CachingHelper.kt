package com.tehreh1uneh.weathertestcase.model.caching

import com.tehreh1uneh.weathertestcase.model.data.WeatherDayInfo
import io.paperdb.Paper
import timber.log.Timber

/**
 * Implements interaction with cache using NoSql library "Paper"
 * @see ICachingHelper
 * @see <a href="https://github.com/pilgr/Paper">Paper</a>
 */
class CachingHelper : ICachingHelper {

    private val bookName = "network_data"
    private val cityKey = "city"
    private val listKey = "weather_day_info_list"

    override fun cacheLastCity(city: String) {
        Timber.d("City cached: %s", city)
        Paper.book(bookName).write(cityKey, city)
    }

    override fun cacheLastList(list: List<WeatherDayInfo>) {
        Timber.d("WeatherDayInfo list cached")
        Paper.book(bookName).write(listKey, list)
    }

    override fun getLastCityFromCache(): String? = Paper.book(bookName).read<String>(cityKey)

    override fun getLastListFromCache(): List<WeatherDayInfo>? = Paper.book(bookName).read<List<WeatherDayInfo>>(listKey)
}
