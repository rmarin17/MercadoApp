package com.rmarin17.mercadoapp.data.network.models

import com.google.gson.annotations.SerializedName

/**
 * Data class to handle the address response.
 */
data class AddressResultModel(
    @SerializedName("state_name") val stateName: String,
    @SerializedName("city_name") val cityName: String
)
