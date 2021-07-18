package com.rmarin17.mercadoapp.common.ext

import androidx.fragment.app.Fragment
import com.rmarin17.mercadoapp.common.di.ActivityInjector

/**
 * File that contains all the fragment extensions functions.
 */

/**
 * Extension function to get the ActivityInjector.
 */
fun Fragment.activityInjector() : ActivityInjector {
    return this.activity as ActivityInjector
}