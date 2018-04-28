package com.tehreh1uneh.weathertestcase.model

import com.tehreh1uneh.weathertestcase.model.caching.ICachingHelper
import com.tehreh1uneh.weathertestcase.model.data.WeatherDayInfo
import com.tehreh1uneh.weathertestcase.model.network.INetworkHelper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Implements interaction with all model layer classes
 */
class DataManager @Inject constructor(private val networkHelper: INetworkHelper, private val cachingHelper: ICachingHelper) : IDataManager {

    /**
     * @see INetworkHelper.getFiveDayWeatherForecast
     */
    override fun getFiveDayWeatherForecast(city: CharSequence, language: CharSequence): Observable<MutableList<WeatherDayInfo>> =
            networkHelper.getFiveDayWeatherForecast(city, language)

    /**
     * @see ICachingHelper.cacheLastCity
     */
    override fun cacheLastCity(city: String) {
        cachingHelper.cacheLastCity(city)
    }

    /**
     * @see ICachingHelper.cacheLastList
     */
    override fun cacheLastList(list: List<WeatherDayInfo>) {
        cachingHelper.cacheLastList(list)
    }

    /**
     * @see ICachingHelper.getLastCityFromCache
     */
    override fun getLastCityFromCache(): String? = cachingHelper.getLastCityFromCache()

    /**
     * @see ICachingHelper.getLastListFromCache
     */
    override fun getLastListFromCache(): List<WeatherDayInfo>? = cachingHelper.getLastListFromCache()
}
