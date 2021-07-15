package com.rmarin17.mercadoapp.di

import com.rmarin17.mercadoapp.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface AppComponent {

    fun inject(activity: HomeActivity)

}
