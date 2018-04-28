package com.tehreh1uneh.weathertestcase.model.network

import com.tehreh1uneh.weathertestcase.model.caching.ICachingHelper
import com.tehreh1uneh.weathertestcase.model.data.WeatherDayInfo
import com.tehreh1uneh.weathertestcase.model.data.toWeatherDayInfoList
import com.tehreh1uneh.weathertestcase.model.network.api.API_KEY
import com.tehreh1uneh.weathertestcase.model.network.api.ApiService
import com.tehreh1uneh.weathertestcase.model.network.api.UNITS
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Implements interaction (and data caching) with API using Retrofit2
 * @see INetworkHelper
 * @see <a href="https://github.com/square/retrofit">Retrofit2</a>
 */
class NetworkHelper @Inject constructor(private val api: ApiService, private val cachingHelper: ICachingHelper) : INetworkHelper {

    override fun getFiveDayWeatherForecast(city: CharSequence, language: CharSequence): Observable<MutableList<WeatherDayInfo>> =
            api.getDailyWeatherByCity(city.toString(), UNITS, language.toString(), API_KEY)
                    .subscribeOn(Schedulers.io())
                    .map { it.toWeatherDayInfoList() }
                    .doOnNext {
                        cachingHelper.cacheLastCity(city.toString())
                        cachingHelper.cacheLastList(it)
                    }
}
