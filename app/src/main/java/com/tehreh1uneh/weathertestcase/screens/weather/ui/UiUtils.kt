package com.tehreh1uneh.weathertestcase.screens.weather.ui

import android.support.annotation.DrawableRes
import com.tehreh1uneh.weathertestcase.R
import com.tehreh1uneh.weathertestcase.model.data.WeatherDayInfo
import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension function for Date class.
 *
 * @return formatted String for displaying in list item view
 */
fun Date.formatForList(): String = SimpleDateFormat("EEEE HH:mm", Locale.getDefault()).format(this).capitalize()

/**
 * @return Drawable resource by OpenWeatherMapApi icon type or null if it is an unknown type
 *
 * @see <a href="https://openweathermap.org/weather-conditions">Weather conditions</a>
 */
@DrawableRes
fun WeatherDayInfo.getIconId(): Int? =
        when (icon) {
            "01d" -> R.mipmap.clear_sky
            "01n" -> R.mipmap.clear_sky_night
            "02d" -> R.mipmap.few_clouds
            "02n" -> R.mipmap.few_clouds_night
            "03d", "03n" -> R.mipmap.scattered_clouds
            "04d", "04n" -> R.mipmap.broken_clouds
            "09d", "09n" -> R.mipmap.shower_rain
            "10d", "10n" -> R.mipmap.rain
            "11d", "11n" -> R.mipmap.thunderstorm
            "13d", "13n" -> R.mipmap.snow
            "50d", "50n" -> R.mipmap.mist
            else -> null
        }
