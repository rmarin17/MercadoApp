package com.rmarin17.mercadoapp.ui.search

import com.rmarin17.mercadoapp.ui.models.ProductListUiModel

/**
 * Class to handle the different states of the SearchFragment.
 */
sealed class ProductSearchState {
    object Loading : ProductSearchState()
    object ProductResultFailure : ProductSearchState()
    data class ProductResultSuccess(val products: List<ProductListUiModel>) : ProductSearchState()
}
