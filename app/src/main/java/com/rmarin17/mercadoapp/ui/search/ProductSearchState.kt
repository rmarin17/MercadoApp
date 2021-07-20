package com.rmarin17.mercadoapp.ui.search

/**
 * Class to handle the different states of the SearchFragment.
 */
sealed class ProductSearchState {
    object Loading : ProductSearchState()
}
