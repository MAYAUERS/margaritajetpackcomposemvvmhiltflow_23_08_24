package com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.model

data class Margarita(var drinks:List<Drinks>)

data class Drinks(var strDrinkThumb:String,var strDrink:String,var idDrink:String)