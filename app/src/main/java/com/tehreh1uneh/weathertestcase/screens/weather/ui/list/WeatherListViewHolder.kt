package com.tehreh1uneh.weathertestcase.screens.weather.ui.list

import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.tehreh1uneh.weathertestcase.R

/**
 * Used for interaction with UI of list
 * @see IListRowView
 *
 * @param itemView View of list item
 */
class WeatherListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), IListRowView {

    private val imageViewIcon: ImageView = itemView.findViewById(R.id.image_view_list_item_icon)
    private val textViewDate: TextView = itemView.findViewById(R.id.text_view_list_item_date)
    private val textViewDescription: TextView = itemView.findViewById(R.id.text_view_list_item_description)
    private val textViewMaxTemperature: TextView = itemView.findViewById(R.id.text_view_list_item_max_temperature)
    private val textViewMinTemperature: TextView = itemView.findViewById(R.id.text_view_list_item_min_temperature)

    override var pos: Int = -1

    override fun bind(@DrawableRes icon: Int?, date: String?, description: String?, maxTemperature: Double?, minTemperature: Double?) {

        if (icon != null) {
            imageViewIcon.setImageResource(icon)
        }

        textViewDate.text = date
        textViewDescription.text = description
        textViewMaxTemperature.text = maxTemperature.toString()
        textViewMinTemperature.text = minTemperature.toString()
    }
}
