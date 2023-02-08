package com.hanncompose.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanncompose.cryptocurrencyappyt.common.Resource
import com.hanncompose.cryptocurrencyappyt.domain.usecase.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase : GetCoinsUseCase
) : ViewModel(){

    private val _state = mutableStateOf<CoinsListState>(CoinsListState())
    val state : State<CoinsListState> = _state

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach {
            result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = CoinsListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = CoinsListState(error = result.message ?: "An unexpected Error occured")
                }
                is Resource.Success -> {
                    _state.value = CoinsListState(coins = result.data ?: emptyList())
                }

            }
        }.launchIn(viewModelScope)
    }
}