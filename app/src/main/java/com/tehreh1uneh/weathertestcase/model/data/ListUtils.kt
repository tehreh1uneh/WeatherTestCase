package com.tehreh1uneh.weathertestcase.model.data

import com.tehreh1uneh.weathertestcase.model.network.api.entity.openweathermap.DailyWeather

/**
 * Extension function for data class.
 * Converts data received from the OpenWeatherMapApi to list of base weather info classes
 */
fun DailyWeather.toWeatherDayInfoList(): MutableList<WeatherDayInfo> =
        MutableList(list.size) { WeatherDayInfo(list[it]) }
