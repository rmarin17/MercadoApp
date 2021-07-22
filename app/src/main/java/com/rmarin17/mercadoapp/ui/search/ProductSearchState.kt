package com.rmarin17.mercadoapp.ui.search

import com.rmarin17.mercadoapp.ui.models.ProductUiModel

/**
 * Class to handle the different states of the SearchFragment.
 */
sealed class ProductSearchState {
    data class Loading(val loadedAction: () -> Unit) : ProductSearchState()
    object ProductResultFailure : ProductSearchState()
    data class ProductResultSuccess(val products: List<ProductUiModel>) : ProductSearchState()
}
