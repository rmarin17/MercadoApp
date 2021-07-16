package com.rmarin17.mercadoapp

import android.app.Application
import com.rmarin17.mercadoapp.di.AppComponent
import com.rmarin17.mercadoapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Application class for maintaining global application state.
 */
class MercadoApp : Application(), HasAndroidInjector {

    private lateinit var appComponent: AppComponent

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
