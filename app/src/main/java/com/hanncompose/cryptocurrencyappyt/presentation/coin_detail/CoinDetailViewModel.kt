package com.hanncompose.cryptocurrencyappyt.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanncompose.cryptocurrencyappyt.common.Constants
import com.hanncompose.cryptocurrencyappyt.common.Resource
import com.hanncompose.cryptocurrencyappyt.domain.usecase.get_coin.GetCoinUseCase
import com.hanncompose.cryptocurrencyappyt.presentation.coin_list.CoinsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) :ViewModel(){

    private val _state = mutableStateOf<CoinDetailListState>(CoinDetailListState())
    val state : State<CoinDetailListState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            coinId ->
            getCoin(coinId)
        }

    }


    private fun getCoin(coinId : String){
        getCoinUseCase(coinId).onEach {
            result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = CoinDetailListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailListState(error = result.message ?: "An unexpected Error occured")
                }
                is Resource.Success -> {
                    _state.value = CoinDetailListState(coin = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}