package com.rmarin17.mercadoapp.common.di

interface ActivityInjector {
    /**
     * Generic function to inject fragments, must be called from onActivityCrated method inside fragment
     */
    fun <T> inject(fragment: T)
}
