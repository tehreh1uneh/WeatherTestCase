package com.tehreh1uneh.weathertestcase.screens.weather.ui

import android.annotation.SuppressLint
import android.support.annotation.DrawableRes
import com.tehreh1uneh.weathertestcase.R
import com.tehreh1uneh.weathertestcase.model.data.WeatherDayInfo
import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension function for Date class.
 * @return formatted String for displaying in list item view
 *
 * @see DateFormatter
 */
fun Date.formatForList(): String = DateFormatter.format(this)

/**
 * Formats Date object to String for displaying in list item view
 */
object DateFormatter {
    @SuppressLint("SimpleDateFormat")
    private val dateFormatDayOfWeek = SimpleDateFormat("EEEE HH:mm")

    fun format(date: Date): String = dateFormatDayOfWeek.format(date).capitalize()
}

/**
 * @return Drawable resource by OpenWeatherMapApi icon type or null if it is an unknown type
 */
@DrawableRes
fun WeatherDayInfo.getIconId(): Int? =
        when (icon) {
            "01d", "01n" -> R.mipmap.clear_sky
            "02d", "02n" -> R.mipmap.scattered_clouds
            "03d", "03n", "04d", "04n" -> R.mipmap.few_clouds
            "09d", "09n" -> R.mipmap.shower_rain
            "10d", "10n" -> R.mipmap.rain
            "11d", "11n" -> R.mipmap.thunderstorm
            "13d", "13n" -> R.mipmap.snow
            "50d", "50n" -> R.mipmap.mist
            else -> null
        }
