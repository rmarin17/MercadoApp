package com.rmarin17.mercadoapp.common.ext

import com.rmarin17.mercadoapp.MercadoApplication
import com.rmarin17.mercadoapp.di.AppComponent
import androidx.appcompat.app.AppCompatActivity

/**
 * File that contains all the activity extensions functions.
 */

/**
 * Extension function to get the app component.
 */
fun AppCompatActivity.appComponent(): AppComponent {
    return MercadoApplication.instance.getAppComponent()
}
