package com.tehreh1uneh.weathertestcase.screens.weather.presenter

import com.tehreh1uneh.weathertestcase.screens.weather.ui.list.IListRowView

/**
 * Declares methods for interaction between RecyclerView list and its presenter
 */
interface IListPresenter <T> {

    /**
     * Contains information about list elements
     */
    var list: List<T>

    /**
     * @return actual number of list elements
     */
    fun getSize(): Int = list.size

    /**
     * Used to inform presenter when ViewHolder binds
     */
    fun onBindView(view: IListRowView)
}
