package com.rmarin17.mercadoapp.ui.navigator

import com.rmarin17.mercadoapp.ui.models.ProductUiModel

/**
 * Interface of HomeNavigator, to handle the navigation.
 */
interface HomeNavigator {

    fun navigateToSearchQuery(query: String)
    fun navigateToProductDetail(product: ProductUiModel)
}
