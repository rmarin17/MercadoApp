package com.rmarin17.mercadoapp.network

/**
 * Constants of the api service.
 */
object ApiConstants {
    const val URL_BASE = "https://api.mercadolibre.com/"
    const val SITE_ID = "site_id"
    const val QUERY = "q"
    const val SEARCH_PATCH ="/sites/{$SITE_ID}/search"
    const val PRODUCT_ID = "id_product"
    const val ITEM_DETAIL_SEARCH = "/items/{$PRODUCT_ID}"
}
