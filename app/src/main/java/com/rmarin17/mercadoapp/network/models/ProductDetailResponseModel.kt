package com.rmarin17.mercadoapp.network.models

import com.google.gson.annotations.SerializedName
import com.rmarin17.mercadoapp.ui.models.ProductListUiModel

/**
 * Data class to handle the product response.
 */
data class ProductDetailResponseModel(
    @SerializedName("id") val id: String,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("title") val title: String,
    @SerializedName("seller") val seller: SellerResponseModel,
    @SerializedName("price") val price: String,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("available_quantity") val availableQuantity: Int,
    @SerializedName("condition") val condition: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("shipping") val shipping: ShippingResponseModel,
    @SerializedName("address") val address: AddressResultModel
) {
    fun transformToProductListUiModel(): ProductListUiModel {
        return ProductListUiModel(
            id = id,
            title = title,
            image = thumbnail,
            price = price
        )
    }
}

