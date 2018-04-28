package com.tehreh1uneh.weathertestcase.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tehreh1uneh.weathertestcase.model.network.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Configure and provides ApiService
 * @see ApiService
 */
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun api(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun retrofit(baseUrl: String, callAdapterFactory: CallAdapter.Factory, converterFactory: Converter.Factory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(callAdapterFactory)
                    .addConverterFactory(converterFactory)
                    .build()

    @Singleton
    @Provides
    fun baseUrl(): String = "https://api.openweathermap.org/"

    @Singleton
    @Provides
    fun callAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    fun gsonConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
}
