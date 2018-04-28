package com.tehreh1uneh.weathertestcase.model.network.api.entity.openweathermap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * File contains data classes that describe OpenWeatherMapApi "5 day/3 hour forecast data"
 * using GSON annotations
 */

data class DailyWeather(val cod: String,
                        val message: Double,
                        @Expose val cnt: Int,
                        @Expose val list: List<WeatherList>,
                        @Expose val city: City)

data class WeatherList(@Expose val dt: Long,
                       @Expose val main: Main,
                       @Expose val weather: List<Weather>,
                       @Expose val clouds: Clouds,
                       @Expose val wind: Wind,
                       @Expose val rain: Rain,
                       @Expose val sys: Sys,
                       @Expose val dtTxt: String)

data class Clouds(@Expose val all: Int)

data class Wind(@Expose val speed: Double,
                @Expose val deg: Double)

data class Rain(@SerializedName("3h") @Expose val info: Double)

data class Sys(@Expose val pod: String)

data class City(@Expose val id: Int,
                @Expose val name: String,
                @Expose val coord: Coord,
                @Expose val country: String,
                @Expose val population: Int)

data class Coord(@Expose val lat: Double,
                 @Expose val lon: Double)

data class Main(@Expose val temp: Double,
                @Expose val tempMin: Double,
                @Expose val tempMax: Double,
                @Expose val pressure: Double,
                @Expose val seaLevel: Double,
                @Expose val humidity: Int,
                val tempKf: Double)

data class Weather(@Expose val id: Int,
                   @Expose val main: String,
                   @Expose val description: String,
                   @Expose val icon: String)
