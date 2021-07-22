package com.rmarin17.mercadoapp.data.network.models

import com.google.gson.annotations.SerializedName
import com.rmarin17.mercadoapp.common.ext.fixUrl
import com.rmarin17.mercadoapp.ui.models.ProductUiModel

/**
 * Data class to handle the product response.
 */
data class ProductDetailResponseModel(
    @SerializedName("id") val id: String,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("title") val title: String,
    @SerializedName("seller") val seller: SellerResponseModel,
    @SerializedName("price") val price: Int,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("available_quantity") val availableQuantity: Int,
    @SerializedName("condition") val condition: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("shipping") val shipping: ShippingResponseModel,
    @SerializedName("address") val address: AddressResultModel,
    @SerializedName("sold_quantity") val soldQuantity: Int,
    @SerializedName("permalink") val linkProduct: String
) {
    fun transformToProductListUiModel(): ProductUiModel {
        return ProductUiModel(
            id = id,
            title = title,
            image = thumbnail.fixUrl(),
            price = price,
            currency = currencyId,
            condition = condition,
            soldQuantity = soldQuantity,
            availableQuantity = availableQuantity,
            isFreeShipping = shipping.freeShipping,
            linkProduct = linkProduct
        )
    }
}

