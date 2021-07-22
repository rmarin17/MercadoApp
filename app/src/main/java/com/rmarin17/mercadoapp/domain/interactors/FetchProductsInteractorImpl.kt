package com.rmarin17.mercadoapp.domain.interactors

import com.rmarin17.mercadoapp.data.network.ProductServices
import com.rmarin17.mercadoapp.ui.models.ProductUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Implementation of FetchProductsInteractor
 */
class FetchProductsInteractorImpl @Inject constructor(private val productsService: ProductServices) : FetchProductsInteractor {
    override fun getDefaultsProducts(): Single<List<ProductUiModel>> {
        return productsService.getProductsByCategory(DEFAULT_SITE, DEFAULT_CATEGORY)
            .firstElement()
            .map { it.transformToListOfProductsUiModel() }
            .toSingle()
    }

    override fun getProductsByQuery(query: String): Single<List<ProductUiModel>> {
        return productsService.getProductsByQuery(DEFAULT_SITE, query)
            .firstElement()
            .map { it.transformToListOfProductsUiModel() }
            .toSingle()
    }

    companion object {
        const val DEFAULT_CATEGORY = "MCO1055"
        const val DEFAULT_SITE = "MCO"
    }

}
