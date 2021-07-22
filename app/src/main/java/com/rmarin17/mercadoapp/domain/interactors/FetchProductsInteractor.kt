package com.rmarin17.mercadoapp.domain.interactors

import com.rmarin17.mercadoapp.ui.models.ProductUiModel
import io.reactivex.rxjava3.core.Single

/**
 * Interface to handle all the methods related products interactor.
 */
interface FetchProductsInteractor {

    fun getDefaultsProducts(): Single<List<ProductUiModel>>
    fun getProductsByQuery(query: String) : Single<List<ProductUiModel>>
}
