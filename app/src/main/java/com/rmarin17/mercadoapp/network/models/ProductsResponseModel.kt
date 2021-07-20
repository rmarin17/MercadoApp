package com.rmarin17.mercadoapp.network.models

import com.google.gson.annotations.SerializedName
import com.rmarin17.mercadoapp.ui.models.ProductListUiModel

/**
 * Model to handle the response of multiples items from a query.
 */
data class ProductsResponseModel(
    @SerializedName("site_id") val siteId: String,
    @SerializedName("results") val results: List<ProductDetailResponseModel>
) {
    fun transformToListOfProductsUiModel(): List<ProductListUiModel> {
        return results.map { it.transformToProductListUiModel() }
    }
}
