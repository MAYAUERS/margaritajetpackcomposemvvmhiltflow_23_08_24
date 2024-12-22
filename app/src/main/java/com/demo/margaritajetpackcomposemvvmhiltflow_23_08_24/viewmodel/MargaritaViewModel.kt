package com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.repository.MargaritaRepository
import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MargaritaViewModel @Inject constructor(private var margaritaRepository: MargaritaRepository):ViewModel() {

    var margaritaFlow = margaritaRepository.getMargarita().map {
         ApiState.Success(it)
    }.catch {
        ApiState.Failure(it)
    }.onStart {
        ApiState.Loading
    }
}