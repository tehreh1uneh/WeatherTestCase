package com.tehreh1uneh.weathertestcase.screens.weather.ui.list

import android.support.annotation.DrawableRes

/**
 * Describes the behaviour of a list row
 */
interface IListRowView {

    /**
     * Current position of a row in list
     */
    var pos: Int

    /**
     * Fills a row in a list
     */
    fun bind(@DrawableRes icon: Int?, date: String?, description: String?, maxTemperature: Double?, minTemperature: Double?)
}
