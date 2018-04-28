package com.tehreh1uneh.weathertestcase.screens.weather.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.tehreh1uneh.weathertestcase.model.IDataManager
import com.tehreh1uneh.weathertestcase.model.data.WeatherDayInfo
import com.tehreh1uneh.weathertestcase.screens.weather.ui.WeatherView
import com.tehreh1uneh.weathertestcase.screens.weather.ui.formatForList
import com.tehreh1uneh.weathertestcase.screens.weather.ui.getIconId
import com.tehreh1uneh.weathertestcase.screens.weather.ui.list.IListRowView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * It's the "Moxy" presenter
 * @see <a href="https://github.com/Arello-Mobile/Moxy">Moxy</a>
 *
 * @param scheduler Main thread scheduler (e.g. it may be TestScheduler while testing)
 */
@InjectViewState
class WeatherPresenter(private val scheduler: Scheduler) : MvpPresenter<WeatherView>() {

    @Inject
    lateinit var dataManager: IDataManager
    private lateinit var searchViewSubscription: Disposable
    val listPresenter: IListPresenter<WeatherDayInfo> = WeatherListPresenter()

    override fun onFirstViewAttach() {

        super.onFirstViewAttach()

        viewState.init()

        val weatherDayInfoList = dataManager.getLastListFromCache()
        if (weatherDayInfoList != null) {
            listPresenter.list = weatherDayInfoList
            viewState.updateWeatherList()
        }

        val city = dataManager.getLastCityFromCache()
        if (city != null) {
            viewState.setCity(city)
        }

        Timber.d("First view attach")
    }

    /**
     * Unsubscribes from subscription
     */
    override fun onDestroy() {
        if (!searchViewSubscription.isDisposed) {
            searchViewSubscription.dispose()
        }
        super.onDestroy()
        Timber.d("Destroyed")
    }

    /**
     * Create a subscription to change the text in the search box.
     * After changing the text the subscription gets weather data, updates list presenter data
     * and notify view
     */
    fun onSearchViewObservableInit(observable: Observable<CharSequence>, language: CharSequence) {

        val inactionTimeoutMillis: Long = 500

        searchViewSubscription = observable
                .debounce(inactionTimeoutMillis, TimeUnit.MILLISECONDS)
                .filter { !it.isEmpty() }
                .distinctUntilChanged({ textFirst, textSecond -> textFirst.isEqualsForOpenWeatherMapRequestByCity(textSecond) })
                .subscribe(
                        {
                            val city = it.withoutWhitespaceDuplicates()
                            Timber.d("Weather request: %s", city)
                            dataManager.getFiveDayWeatherForecast(city, language)
                                    .observeOn(scheduler)
                                    .subscribe(
                                            {
                                                Timber.d("Weather data successfully loaded from web")
                                                listPresenter.list = it
                                                viewState.updateWeatherList()
                                            },
                                            { Timber.v(it, "Error during loading weather from web") }
                                    )
                        },
                        {
                            val message = "Error during getting text from UI"
                            Timber.e(it, message)
                            RuntimeException(message)
                        }
                )
        Timber.d("Subscription created")
    }

    /**
     * Compares two char sequences using trim() function and whitespaces replacing
     */
    private fun CharSequence.isEqualsForOpenWeatherMapRequestByCity(another: CharSequence): Boolean =
            withoutWhitespaceDuplicates() == another.withoutWhitespaceDuplicates()

    /**
     * Uses trim() function then replaces duplicate whitespaces with one whitespace
     */
    private fun CharSequence.withoutWhitespaceDuplicates(): CharSequence =
            trim().replace(Regex("""\s+"""), " ")

    /**
     * Presenter for RecyclerView list.
     * @see IListPresenter
     */
    inner class WeatherListPresenter : IListPresenter<WeatherDayInfo> {

        override var list: List<WeatherDayInfo> = listOf()

        override fun onBindView(view: IListRowView) {

            val dayInfo = list[view.pos]

            view.bind(dayInfo.getIconId(),
                    dayInfo.date?.formatForList(),
                    dayInfo.description,
                    dayInfo.maxTemperature,
                    dayInfo.minTemperature)
        }
    }
}
