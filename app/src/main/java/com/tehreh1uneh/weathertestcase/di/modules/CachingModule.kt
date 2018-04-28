package com.tehreh1uneh.weathertestcase.di.modules

import com.tehreh1uneh.weathertestcase.model.caching.CachingHelper
import com.tehreh1uneh.weathertestcase.model.caching.ICachingHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides ICachingHelper
 * @see ICachingHelper
 */
@Module
class CachingModule {

    @Singleton
    @Provides
    fun cachingHelper(): ICachingHelper = CachingHelper()
}
