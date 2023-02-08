package com.hanncompose.cryptocurrencyappyt.data.repository

import com.hanncompose.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.hanncompose.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.hanncompose.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.hanncompose.cryptocurrencyappyt.domain.repository.ICoinRepository
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val api: CoinPaprikaApi
) : ICoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}