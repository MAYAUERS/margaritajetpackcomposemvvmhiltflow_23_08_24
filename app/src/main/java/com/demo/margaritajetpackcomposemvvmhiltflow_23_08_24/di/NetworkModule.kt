package com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.di

import com.demo.margaritajetpackcomposemvvmhiltflow_23_08_24.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient,gsonConverterFactory: GsonConverterFactory):Retrofit =
      Retrofit.Builder().baseUrl("https://www.thecocktaildb.com/")
          .client(okHttpClient)
          .addConverterFactory(gsonConverterFactory)
          .build()

    @Singleton
    @Provides
    fun provideokHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient =
      OkHttpClient().newBuilder()
      .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()

    @Singleton
    @Provides
    fun providehttpLoggingInterceptor():HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
   fun provideGsonConverterFactory():GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit):ApiService = retrofit.create(ApiService::class.java)
}