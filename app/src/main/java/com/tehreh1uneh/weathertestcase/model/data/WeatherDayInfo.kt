package com.tehreh1uneh.weathertestcase.model.data

import com.tehreh1uneh.weathertestcase.model.network.api.entity.openweathermap.WeatherList
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Base weather info class. Contains all data required for UI
 */
class WeatherDayInfo() {

    var date: Date? = null
    var icon: String? = null
    var temperature: Double? = null
    var maxTemperature: Double? = null
    var minTemperature: Double? = null
    var description: String? = null

    constructor(from: WeatherList) : this() {

        date = Date(TimeUnit.SECONDS.toMillis(from.dt))
        temperature = from.main.temp

        if (from.weather.isNotEmpty()) {
            val firstLine = from.weather[0]
            icon = firstLine.icon
            description = firstLine.description.capitalize()
        }

        maxTemperature = from.main.tempMax
        minTemperature = from.main.tempMin
    }
}
