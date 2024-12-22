package com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.network

import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.model.Margarita
import retrofit2.http.GET

interface ApiService {

    //https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita

    @GET("api/json/v1/1/search.php?s=margarita")
    suspend fun getMargarita():Margarita
}