package com.hanncompose.cryptocurrencyappyt.presentation.coin_detail

import com.hanncompose.cryptocurrencyappyt.domain.model.Coin
import com.hanncompose.cryptocurrencyappyt.domain.model.CoinDetail

data class CoinDetailListState(
    val isLoading : Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
