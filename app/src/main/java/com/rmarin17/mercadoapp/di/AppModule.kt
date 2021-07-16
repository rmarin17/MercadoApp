package com.rmarin17.mercadoapp.di

import com.google.gson.GsonBuilder
import com.rmarin17.mercadoapp.network.ApiConstants.URL_BASE
import com.rmarin17.mercadoapp.network.ProductServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module that instruct Dagger how to provide injected resources.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(URL_BASE)
            .build()
    }

    @Singleton
    @Provides
    fun provideProductsService(retrofit: Retrofit): ProductServices {
        return retrofit.create(ProductServices::class.java)
    }
}
