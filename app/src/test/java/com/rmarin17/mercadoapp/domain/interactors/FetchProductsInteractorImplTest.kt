package com.rmarin17.mercadoapp.domain.interactors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rmarin17.mercadoapp.data.network.ProductServices
import com.rmarin17.mercadoapp.data.network.models.AddressResultModel
import com.rmarin17.mercadoapp.data.network.models.ProductDetailResponseModel
import com.rmarin17.mercadoapp.data.network.models.ProductsResponseModel
import com.rmarin17.mercadoapp.data.network.models.SellerResponseModel
import com.rmarin17.mercadoapp.data.network.models.ShippingResponseModel
import com.rmarin17.mercadoapp.test.RxTestSchedulerRule
import io.reactivex.rxjava3.core.Observable
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit test of FetchProductsInteractorImpl.
 */
@RunWith(MockitoJUnitRunner::class)
class FetchProductsInteractorImplTest {

    private val productsService: ProductServices = mock()

    private lateinit var fetchProductsInteractor: FetchProductsInteractor

    private val PRODUCT_1 = mockProductDetailResponseModel("1")

    @get:Rule
    val testScheduler = RxTestSchedulerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        fetchProductsInteractor = FetchProductsInteractorImpl(productsService)
    }

    @Test
    fun `when getDefaultsProducts is called and result is success, the check response`() {
        whenever(productsService.getProductsByCategory(any(), any())).thenReturn(Observable.just(mockProductResponseModel()))
        val result = fetchProductsInteractor.getDefaultsProducts().blockingGet().first()
        assertTrue(result.id == PRODUCT_1.id)
        assertTrue(result.title == PRODUCT_1.title)
        assertTrue(result.image == PRODUCT_1.thumbnail)
        assertTrue(result.price == PRODUCT_1.price)
        assertTrue(result.soldQuantity == PRODUCT_1.soldQuantity)
        assertTrue(result.availableQuantity == PRODUCT_1.availableQuantity)
        assertTrue(result.isFreeShipping == PRODUCT_1.shipping.freeShipping)
        assertTrue(result.linkProduct == PRODUCT_1.linkProduct)
    }

    @Test(expected = Throwable::class)
    fun `when getDefaultsProducts is called and result is error, the check response`() {
        whenever(productsService.getProductsByCategory(any(), any())).thenReturn(Observable.error(Throwable("test")))
        fetchProductsInteractor.getDefaultsProducts().blockingGet()
    }

    @Test
    fun `when getProductsByQuery is called and result is success, the check response`() {
        whenever(productsService.getProductsByQuery(any(), any())).thenReturn(Observable.just(mockProductResponseModel()))
        val result = fetchProductsInteractor.getProductsByQuery("test").blockingGet().first()
        assertTrue(result.id == PRODUCT_1.id)
        assertTrue(result.title == PRODUCT_1.title)
        assertTrue(result.image == PRODUCT_1.thumbnail)
        assertTrue(result.price == PRODUCT_1.price)
        assertTrue(result.soldQuantity == PRODUCT_1.soldQuantity)
        assertTrue(result.availableQuantity == PRODUCT_1.availableQuantity)
        assertTrue(result.isFreeShipping == PRODUCT_1.shipping.freeShipping)
        assertTrue(result.linkProduct == PRODUCT_1.linkProduct)
    }

    @Test(expected = Throwable::class)
    fun `when getProductsByQuery is called and result is error, the check response`() {
        whenever(productsService.getProductsByQuery(any(), any())).thenReturn(Observable.error(Throwable("test")))
        fetchProductsInteractor.getProductsByQuery("test").blockingGet()
    }

    private fun mockProductResponseModel(): ProductsResponseModel {
        return ProductsResponseModel(
            "1",
            listOf(PRODUCT_1)
        )
    }

    private fun mockProductDetailResponseModel(id: String): ProductDetailResponseModel {
        return ProductDetailResponseModel(
            id,
            "1",
            "title",
            SellerResponseModel(
                1,
                "prime"
            ),
            1,
            "COP",
            1,
            "new",
            "www.image.com",
            ShippingResponseModel(
                true,
                "test",
                "test",
                true
            ),
            AddressResultModel(
                "Valle",
                "Cali"
            ),
            1,
            "www.product.com"
        )
    }

}