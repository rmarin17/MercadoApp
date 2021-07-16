package com.rmarin17.mercadoapp.di

import com.rmarin17.mercadoapp.MercadoApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Dagger component of the application
 */
@Component(modules = [AppModule::class, AndroidInjectionModule::class])
@Singleton
interface AppComponent : AndroidInjector<MercadoApp> {

    val homeComponentFactory: HomeComponent.Factory
}
