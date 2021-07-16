package com.rmarin17.mercadoapp.di

import com.rmarin17.mercadoapp.HomeActivity
import com.rmarin17.mercadoapp.common.di.ActivityScope
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Dagger sub component for home.
 */
@ActivityScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: HomeActivity): HomeComponent
    }

    fun inject(activity: HomeActivity)
}
