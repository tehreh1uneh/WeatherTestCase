package com.tehreh1uneh.weathertestcase.model

import com.tehreh1uneh.weathertestcase.model.caching.ICachingHelper
import com.tehreh1uneh.weathertestcase.model.network.INetworkHelper

/**
 * Uses to interact with all model layer classes
 * @see INetworkHelper
 * @see ICachingHelper
 */
interface IDataManager : INetworkHelper, ICachingHelper