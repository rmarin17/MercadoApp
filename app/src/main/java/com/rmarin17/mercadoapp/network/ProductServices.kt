package com.rmarin17.mercadoapp.network

import com.rmarin17.mercadoapp.network.ApiConstants.ITEM_DETAIL_SEARCH
import com.rmarin17.mercadoapp.network.ApiConstants.PRODUCT_ID
import com.rmarin17.mercadoapp.network.ApiConstants.QUERY
import com.rmarin17.mercadoapp.network.ApiConstants.SEARCH_PATCH
import com.rmarin17.mercadoapp.network.ApiConstants.SITE_ID
import com.rmarin17.mercadoapp.network.models.ProductDetailResponseModel
import com.rmarin17.mercadoapp.network.models.ProductsResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Interface to define the services call to retrofit
 */
interface ProductServices {

    @GET(SEARCH_PATCH)
    fun getProductsByQuery(@Path(SITE_ID) siteId: String, @Query(QUERY) query: String): Observable<ProductsResponseModel>

    @GET(ITEM_DETAIL_SEARCH)
    fun getProductById(@Path(PRODUCT_ID) productId: Int): Observable<ProductDetailResponseModel>

}
