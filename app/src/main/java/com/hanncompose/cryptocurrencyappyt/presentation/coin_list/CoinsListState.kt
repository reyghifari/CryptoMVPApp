package com.hanncompose.cryptocurrencyappyt.presentation.coin_list

import com.hanncompose.cryptocurrencyappyt.domain.model.Coin

data class CoinsListState(
    val isLoading : Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
