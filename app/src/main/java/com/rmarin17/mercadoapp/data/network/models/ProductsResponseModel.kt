package com.rmarin17.mercadoapp.data.network.models

import com.google.gson.annotations.SerializedName
import com.rmarin17.mercadoapp.ui.models.ProductUiModel

/**
 * Model to handle the response of multiples items from a query.
 */
data class ProductsResponseModel(
    @SerializedName("site_id") val siteId: String,
    @SerializedName("results") val results: List<ProductDetailResponseModel>
) {
    fun transformToListOfProductsUiModel(): List<ProductUiModel> {
        return results.map { it.transformToProductListUiModel() }
    }
}
