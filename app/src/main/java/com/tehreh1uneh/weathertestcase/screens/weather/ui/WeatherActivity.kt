package com.tehreh1uneh.weathertestcase.screens.weather.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.tehreh1uneh.weathertestcase.App
import com.tehreh1uneh.weathertestcase.R
import com.tehreh1uneh.weathertestcase.di.ApplicationComponent
import com.tehreh1uneh.weathertestcase.screens.weather.presenter.WeatherPresenter
import com.tehreh1uneh.weathertestcase.screens.weather.ui.list.WeatherListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_weather.*
import timber.log.Timber

class WeatherActivity : MvpAppCompatActivity(), WeatherView {

    @InjectPresenter lateinit var presenter: WeatherPresenter
    private lateinit var adapter: WeatherListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        initObservables()
        Timber.d("Activity created")
    }

    /**
     * @see WeatherView.init
     */
    override fun init() {
        expandSearchViewWithoutShowingKeyboard()
        initRecyclerView()
        Timber.d("Activity initialized")
    }

    /**
     * Expands SearchView to show last selected city
     */
    private fun expandSearchViewWithoutShowingKeyboard() {
        search_view_city.isIconified = false
        search_view_city.clearFocus()
        Timber.d("Search view expanded")
    }

    /**
     * Creates an Observable object by "RxBinding2" library and pass it to presenter.
     *
     * @see <a href="https://github.com/JakeWharton/RxBinding">RxBinding2</a>
     * @see WeatherPresenter.onSearchViewObservableInit
     */
    private fun initObservables() {

        val observable = RxSearchView.queryTextChanges(search_view_city)
        val language = getString(R.string.language_code_open_weather_map)

        presenter.onSearchViewObservableInit(observable, language)
        Timber.d("Observable for Search view initialized")
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = WeatherListAdapter(presenter.listPresenter)
        recycler_view.adapter = adapter
        Timber.d("Recycler view initialized")
    }

    /**
     * Provides Moxy presenter.
     * @see WeatherPresenter
     * @see ApplicationComponent
     */
    @ProvidePresenter
    fun providePresenter(): WeatherPresenter {
        val presenter = WeatherPresenter(AndroidSchedulers.mainThread())
        val applicationComponent = App.applicationComponent

        applicationComponent.inject(presenter)

        Timber.d("Presenter provided")
        return presenter
    }

    /**
     * @see WeatherView.updateWeatherList
     */
    override fun updateWeatherList() {
        adapter.notifyDataSetChanged()
        Timber.d("Weather updated")
    }

    /**
     * @see WeatherView.setCity
     */
    override fun setCity(city: String, submit: Boolean) {
        search_view_city.setQuery(city, submit)
        Timber.d("City changed")
    }

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }
}
