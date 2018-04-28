package com.tehreh1uneh.weathertestcase.di.modules

import com.tehreh1uneh.weathertestcase.model.caching.ICachingHelper
import com.tehreh1uneh.weathertestcase.model.network.INetworkHelper
import com.tehreh1uneh.weathertestcase.model.network.NetworkHelper
import com.tehreh1uneh.weathertestcase.model.network.api.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides INetworkHelper
 * @see INetworkHelper
 */
@Module(includes = [RetrofitModule::class, CachingModule::class])
class NetworkHelperModule {

    @Singleton
    @Provides
    fun networkHelper(apiService: ApiService, cachingHelper: ICachingHelper): INetworkHelper =
            NetworkHelper(apiService, cachingHelper)
}
