package com.rmarin17.mercadoapp.network

import com.rmarin17.mercadoapp.network.ApiConstants.GET_ALL_PRODUCTS
import com.rmarin17.mercadoapp.network.ApiConstants.GET_PRODUCT
import com.rmarin17.mercadoapp.network.ApiConstants.PRODUCT_ID
import com.rmarin17.mercadoapp.network.models.ProductResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Interface to define the services call to retrofit
 */
interface ProductServices {

    @GET(GET_ALL_PRODUCTS)
    fun getProducts(): Observable<List<ProductResponseModel>>

    @GET(GET_PRODUCT)
    fun getProductById(@Path(PRODUCT_ID) productId: Int): Observable<ProductResponseModel>

}