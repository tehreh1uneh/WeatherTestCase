package com.tehreh1uneh.weathertestcase.model.network

import com.tehreh1uneh.weathertestcase.model.data.WeatherDayInfo
import io.reactivex.Observable

/**
 * Declares all possible options for interaction with API
 */
interface INetworkHelper {

    /**
     * @return Observable object with list of daily weather data
     */
    fun getFiveDayWeatherForecast(city: CharSequence, language: CharSequence): Observable<MutableList<WeatherDayInfo>>
}
