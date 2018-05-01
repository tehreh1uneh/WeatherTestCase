package com.tehreh1uneh.weathertestcase.di

import com.tehreh1uneh.weathertestcase.di.modules.DataManagerModule
import com.tehreh1uneh.weathertestcase.screens.weather.presenter.WeatherPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Injects all model layer dependencies using IDataManager
 * @see DataManagerModule
 */
@Singleton
@Component(modules = [DataManagerModule::class])
interface ApplicationComponent {
    fun inject(weatherPresenter: WeatherPresenter)
}
