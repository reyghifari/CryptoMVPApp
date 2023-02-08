package com.hanncompose.cryptocurrencyappyt.domain.repository

import com.hanncompose.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.hanncompose.cryptocurrencyappyt.data.remote.dto.CoinDto

interface ICoinRepository {

    suspend fun getCoins() : List<CoinDto>

    suspend fun getCoinById(coinId : String) : CoinDetailDto
}