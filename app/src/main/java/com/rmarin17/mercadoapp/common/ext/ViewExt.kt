package com.rmarin17.mercadoapp.common.ext

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged

/**
 * File that contains all the extensions related to view.
 */

/**
 * Extension function to get the ViewModel in fragment without ViewModelFactory
 */
inline fun <reified T : ViewModel> Fragment.getViewModel(): T {
    return ViewModelProvider(this).get(T::class.java)
}

/**
 * Extension function to get the ViewModel in fragment with ViewModelFactory
 */
inline fun <reified T : ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit = {}): T {
    val vm = ViewModelProvider(this, factory).get(T::class.java)
    vm.body()
    return vm
}

/**
 * Extension function for observe a livedata.
 */
fun <T> Fragment.observe(liveData: LiveData<T>?, observer: Observer<in T>) {
    liveData?.distinctUntilChanged()?.observe(viewLifecycleOwner, observer)
}

/**
 * Extension function to set up visibility of the view.
 */
fun View.visible(visible: Boolean = true) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
