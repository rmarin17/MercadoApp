package com.rmarin17.mercadoapp.data.network.models

import com.google.gson.annotations.SerializedName

/**
 * Data class to handle the shipping response.
 */
data class ShippingResponseModel(
    @SerializedName("free_shipping") val freeShipping: Boolean,
    @SerializedName("logistic_type") val logisticType: String,
    @SerializedName("mode") val mode: String,
    @SerializedName("store_pick_up") val storePickUp: Boolean
)
