package com.tehreh1uneh.weathertestcase.screens.weather.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tehreh1uneh.weathertestcase.R
import com.tehreh1uneh.weathertestcase.model.data.WeatherDayInfo
import com.tehreh1uneh.weathertestcase.screens.weather.presenter.IListPresenter

/**
 * Describes basic behaviour of weather list RecyclerView
 * @param presenter Used for data binding
 * @see IListPresenter
 */
class WeatherListAdapter(private val presenter: IListPresenter<WeatherDayInfo>) : RecyclerView.Adapter<WeatherListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder =
            WeatherListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false))

    override fun getItemCount(): Int = presenter.getSize()

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.pos = position
        presenter.onBindView(holder)
    }
}
