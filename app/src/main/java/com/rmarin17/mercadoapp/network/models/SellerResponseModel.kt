package com.rmarin17.mercadoapp.network.models

import com.google.gson.annotations.SerializedName

/**
 * Data class to handle the seller response.
 */
data class SellerResponseModel(
    @SerializedName("id") val id: Int,
    @SerializedName("power_seller_status") val sellerStatus: String
)
