package com.rmarin17.mercadoapp.network.models

import com.google.gson.annotations.SerializedName

/**
 * Data class to handle the product response.
 */
data class ProductResponseModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: String,
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String
)
