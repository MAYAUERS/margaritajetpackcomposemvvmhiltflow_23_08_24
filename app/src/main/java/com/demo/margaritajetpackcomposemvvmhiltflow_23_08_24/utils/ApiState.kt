package com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.utils

import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.model.Margarita

sealed class ApiState {

    class Success(var data:Margarita):ApiState()
    class Failure(var msg:Throwable):ApiState()
    object Loading:ApiState()
    object Empty:ApiState()
}