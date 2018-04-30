package com.tehreh1uneh.weathertestcase.screens.weather.ui

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Declares Activity methods for interaction with Presenter
 */
@StateStrategyType(value = AddToEndSingleStrategy::class)
interface WeatherView : MvpView {

    /**
     * Initializes Activity parameters on first view attach
     */
    fun init()

    /**
     * Updates list with information about daily weather
     */
    fun updateWeatherList()

    /**
     * Sets text in a search view
     */
    fun setCity(city: String, submit: Boolean = true)
}
