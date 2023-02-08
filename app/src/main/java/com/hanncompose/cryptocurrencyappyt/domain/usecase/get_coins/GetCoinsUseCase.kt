package com.hanncompose.cryptocurrencyappyt.domain.usecase.get_coins

import com.hanncompose.cryptocurrencyappyt.common.Resource
import com.hanncompose.cryptocurrencyappyt.data.remote.dto.toCoin
import com.hanncompose.cryptocurrencyappyt.domain.model.Coin
import com.hanncompose.cryptocurrencyappyt.domain.repository.ICoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: ICoinRepository
) {
    operator fun invoke():Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        }catch (e:HttpException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected Error Occured"))
        }catch (e: IOException){
            emit(Resource.Error<List<Coin>>("Couldnt't reach server. Check your internet server"))
        }
    }
}