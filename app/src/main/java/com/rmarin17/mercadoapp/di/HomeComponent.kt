package com.rmarin17.mercadoapp.di

import com.rmarin17.mercadoapp.ui.home.HomeActivity
import com.rmarin17.mercadoapp.common.di.ActivityScope
import com.rmarin17.mercadoapp.ui.search.SearchFragment
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
    fun inject(fragment: SearchFragment)
}
