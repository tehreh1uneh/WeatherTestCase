package com.tehreh1uneh.weathertestcase.di.modules

import com.tehreh1uneh.weathertestcase.model.DataManager
import com.tehreh1uneh.weathertestcase.model.IDataManager
import com.tehreh1uneh.weathertestcase.model.caching.ICachingHelper
import com.tehreh1uneh.weathertestcase.model.network.INetworkHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides all model layer dependencies
 * @see IDataManager
 */
@Module(includes = [NetworkHelperModule::class, CachingModule::class])
class DataManagerModule {

    @Singleton
    @Provides
    fun dataManager(networkHelper: INetworkHelper, cachingHelper: ICachingHelper): IDataManager =
            DataManager(networkHelper, cachingHelper)
}
