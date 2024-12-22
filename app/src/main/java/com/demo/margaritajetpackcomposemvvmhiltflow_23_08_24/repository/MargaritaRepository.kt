package com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.repository

import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.model.Margarita
import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MargaritaRepository @Inject constructor(private var apiService: ApiService) {

    fun getMargarita():Flow<Margarita> = flow {
        val result = apiService.getMargarita()
        emit(result)
    }.flowOn(Dispatchers.IO)
}