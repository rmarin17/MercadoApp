package com.rmarin17.mercadoapp.di

import com.rmarin17.mercadoapp.domain.interactors.FetchProductsInteractor
import com.rmarin17.mercadoapp.domain.interactors.FetchProductsInteractorImpl
import dagger.Binds
import dagger.Module

/**
 * Module for provide dependencies related home.
 */
@Module
abstract class HomeModule {

    @Binds
    abstract fun provideFetchProductsInteractor(fetchProductsInteractor: FetchProductsInteractorImpl): FetchProductsInteractor
}
