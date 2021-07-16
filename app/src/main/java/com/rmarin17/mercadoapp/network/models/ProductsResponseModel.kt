package com.rmarin17.mercadoapp.network.models

import com.google.gson.annotations.SerializedName

/**
 * Model to handle the response of multiples items from a query.
 */
data class ProductsResponseModel(
    @SerializedName("site_id") val siteId: String,
    @SerializedName("results") val results: List<ProductDetailResponseModel>
)
