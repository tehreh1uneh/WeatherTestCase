package com.tehreh1uneh.weathertestcase.model.network.api

import com.tehreh1uneh.weathertestcase.model.network.api.entity.openweathermap.DailyWeather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Describes OpenWeatherMapApi methods for Retrofit2 library.
 */
interface ApiService {

    /**
     * Uses "5 day/3 hour forecast data"
     * @see <a href="https://openweathermap.org/forecast5">5 day/3 hour forecast data</a>
     *
     * @param units available options: "imperial" (Fahrenheit), "metric" (Celsius), "Standard" (Kelvin)
     * @param languageCode for weather description only
     * @return Observable object with Api data
     */
    @GET("/data/2.5/forecast")
    fun getDailyWeatherByCity(@Query("q") city: String,
                              @Query("units") units: String,
                              @Query("lang") languageCode: String,
                              @Query("APPID") apiKey: String): Observable<DailyWeather>
}
