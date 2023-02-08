package com.hanncompose.cryptocurrencyappyt.domain.usecase.get_coin

import com.hanncompose.cryptocurrencyappyt.common.Resource
import com.hanncompose.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.hanncompose.cryptocurrencyappyt.domain.model.CoinDetail
import com.hanncompose.cryptocurrencyappyt.domain.repository.ICoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: ICoinRepository
) {
    operator fun invoke(coinId: String):Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        }catch (e:HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected Error Occured"))
        }catch (e: IOException){
            emit(Resource.Error<CoinDetail>("Couldnt't reach server. Check your internet server"))
        }
    }
}