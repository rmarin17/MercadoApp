package com.rmarin17.mercadoapp.common.ext

import android.app.Activity
import androidx.annotation.IdRes
import com.rmarin17.mercadoapp.MercadoApplication
import com.rmarin17.mercadoapp.di.AppComponent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import java.lang.ref.WeakReference

/**
 * File that contains all the activity extensions functions.
 */

/**
 * Extension function to get the app component.
 */
fun AppCompatActivity.appComponent(): AppComponent {
    return MercadoApplication.instance.getAppComponent()
}

/**
 * Extension function to find nav controller from Activity by resId.
 */
fun Activity.findNavController(@IdRes viewId: Int): NavController {
    return Navigation.findNavController(this, viewId)
}

/**
 * Extension function to execute an action in a weak reference of activity to prevent memory leaks.
 */
inline fun <T : FragmentActivity> WeakReference<T>.performActionWithSafeActivity(action: (T) -> Unit) {
    get()?.let {
        action(it)
    }
}
