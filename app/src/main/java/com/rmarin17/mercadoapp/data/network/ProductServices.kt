package com.rmarin17.mercadoapp.data.network

import com.rmarin17.mercadoapp.data.network.ApiConstants.ITEM_DETAIL_SEARCH
import com.rmarin17.mercadoapp.data.network.ApiConstants.PRODUCT_CATEGORY
import com.rmarin17.mercadoapp.data.network.ApiConstants.PRODUCT_ID
import com.rmarin17.mercadoapp.data.network.ApiConstants.QUERY
import com.rmarin17.mercadoapp.data.network.ApiConstants.SEARCH_PATCH
import com.rmarin17.mercadoapp.data.network.ApiConstants.SITE_ID
import com.rmarin17.mercadoapp.data.network.models.ProductDetailResponseModel
import com.rmarin17.mercadoapp.data.network.models.ProductsResponseModel
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

    @GET(SEARCH_PATCH)
    fun getProductsByCategory(@Path(SITE_ID) siteId: String, @Query(PRODUCT_CATEGORY) categoryId: String): Observable<ProductsResponseModel>

}
