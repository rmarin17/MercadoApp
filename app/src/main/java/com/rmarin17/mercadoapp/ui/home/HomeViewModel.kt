package com.rmarin17.mercadoapp.ui.home

import com.rmarin17.mercadoapp.common.BaseViewModel
import javax.inject.Inject

/**
 * Class to acquire and keep the information that is necessary to show in home activity.
 */
class HomeViewModel @Inject constructor(private val navigator: HomeNavigator) : BaseViewModel() {

    fun navigateToSearchQuery(query: String) {
        navigator.navigateToSearchQuery(query)
    }
}
