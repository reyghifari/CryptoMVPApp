package com.hanncompose.cryptocurrencyappyt.di

import com.hanncompose.cryptocurrencyappyt.common.Constants
import com.hanncompose.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.hanncompose.cryptocurrencyappyt.data.repository.CoinRepository
import com.hanncompose.cryptocurrencyappyt.domain.repository.ICoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi() : CoinPaprikaApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api:CoinPaprikaApi) : ICoinRepository {
        return  CoinRepository(api)
    }

}