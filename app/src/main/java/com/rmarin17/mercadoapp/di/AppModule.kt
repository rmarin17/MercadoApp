package com.rmarin17.mercadoapp.di

import com.google.gson.GsonBuilder
import com.rmarin17.mercadoapp.network.ApiConstants.URL_BASE
import com.rmarin17.mercadoapp.network.ProductServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(URL_BASE)
            .client(OkHttpClient.Builder().build())
            .build()
    }

    @Singleton
    @Provides
    fun provideProductsService(retrofit: Retrofit): ProductServices {
        return retrofit.create(ProductServices::class.java)
    }
}
