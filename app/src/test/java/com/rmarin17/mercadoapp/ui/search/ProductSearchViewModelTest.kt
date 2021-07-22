package com.rmarin17.mercadoapp.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rmarin17.mercadoapp.andTrigger
import com.rmarin17.mercadoapp.common.logger.Logger
import com.rmarin17.mercadoapp.domain.interactors.FetchProductsInteractor
import com.rmarin17.mercadoapp.test.RxAndroidTrampolineRule
import com.rmarin17.mercadoapp.test.RxTestSchedulerRule
import com.rmarin17.mercadoapp.ui.models.ProductUiModel
import com.rmarin17.mercadoapp.ui.navigator.HomeNavigator
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit test of ProductSearchViewModel.
 */
@RunWith(MockitoJUnitRunner::class)
class ProductSearchViewModelTest {

    private val fetchProductsInteractor: FetchProductsInteractor = mock()
    private val logger: Logger = mock()
    private val navigator: HomeNavigator = mock()

    private lateinit var viewModel: ProductSearchViewModel

    @get:Rule
    val androidTrampolineRule = RxAndroidTrampolineRule()

    @get:Rule
    val testScheduler = RxTestSchedulerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val scheduler = testScheduler.scheduler

    private val PRODUCT_1 = mockProduct("1")
    private val PRODUCT_2 = mockProduct("2")

    @Before
    fun setUp() {
        viewModel = ProductSearchViewModel(
            fetchProductsInteractor,
            logger,
            navigator
        )
    }

    @Test
    fun `when getDefaultProducts is called, with view in null state, should change productsSearchState to Loading`() {
        { viewModel.getDefaultProducts() } andTrigger scheduler
        assertTrue(viewModel.productsSearchState.value is ProductSearchState.Loading)
    }

    @Test
    fun `when getDefaultProducts is called without errors, with view loading state, should change productsSearchState to ProductResultSuccess`() {
        whenever(fetchProductsInteractor.getDefaultsProducts()).thenReturn(Single.just(mockProducts(false, PRODUCT_1, PRODUCT_2)))
        viewModel.advanceToFetchDefaultProducts(scheduler)
        assertTrue(viewModel.productsSearchState.value is ProductSearchState.ProductResultSuccess)
        val firstValue = (viewModel.productsSearchState.value as ProductSearchState.ProductResultSuccess).products.first()
        assertTrue(firstValue.id == PRODUCT_1.id)
        assertTrue(firstValue.title == PRODUCT_1.title)
        assertTrue(firstValue.image == PRODUCT_1.image)
        assertTrue(firstValue.price == PRODUCT_1.price)
        assertTrue(firstValue.soldQuantity == PRODUCT_1.soldQuantity)
        assertTrue(firstValue.availableQuantity == PRODUCT_1.availableQuantity)
        assertTrue(firstValue.isFreeShipping == PRODUCT_1.isFreeShipping)
        assertTrue(firstValue.linkProduct == PRODUCT_1.linkProduct)
    }

    @Test
    fun `when getDefaultProducts is called, result is empty list, with view loading state, should change productsSearchState to ProductResultSuccess`() {
        whenever(fetchProductsInteractor.getDefaultsProducts()).thenReturn(Single.just(mockProducts(true)))
        viewModel.advanceToFetchDefaultProducts(scheduler)
        assertTrue(viewModel.productsSearchState.value is ProductSearchState.ProductResultSuccess)
        val products = (viewModel.productsSearchState.value as ProductSearchState.ProductResultSuccess).products
        assertTrue(products.isEmpty())
    }

    @Test
    fun `when getDefaultProducts is called with errors, with view loading state, should change productsSearchState to ProductResultFailure`() {
        whenever(fetchProductsInteractor.getDefaultsProducts()).thenReturn(Single.error(Throwable("test error")))
        viewModel.advanceToFetchDefaultProducts(scheduler)
        assertTrue(viewModel.productsSearchState.value is ProductSearchState.ProductResultFailure)
    }

    @Test
    fun `when searchProductsByQuery is called, with view null state, should change productsSearchState to Loading`() {
        { viewModel.searchProductsByQuery("test") } andTrigger scheduler
        assertTrue(viewModel.productsSearchState.value is ProductSearchState.Loading)
    }

    @Test
    fun `when searchProductsByQuery is called without errors, with view loading state, should change productsSearchState to ProductResultSuccess`() {
        whenever(fetchProductsInteractor.getProductsByQuery(any())).thenReturn(Single.just(mockProducts(false, PRODUCT_1, PRODUCT_2)))
        viewModel.advanceToSearchProducts(scheduler)
        val firstValue = (viewModel.productsSearchState.value as ProductSearchState.ProductResultSuccess).products.first()
        assertTrue(firstValue.id == PRODUCT_1.id)
        assertTrue(firstValue.title == PRODUCT_1.title)
        assertTrue(firstValue.image == PRODUCT_1.image)
        assertTrue(firstValue.price == PRODUCT_1.price)
        assertTrue(firstValue.soldQuantity == PRODUCT_1.soldQuantity)
        assertTrue(firstValue.availableQuantity == PRODUCT_1.availableQuantity)
        assertTrue(firstValue.isFreeShipping == PRODUCT_1.isFreeShipping)
        assertTrue(firstValue.linkProduct == PRODUCT_1.linkProduct)
    }

    @Test
    fun `when searchProductsByQuery is called, result empty list, with view loading state, should change productsSearchState to ProductResultSuccess`() {
        whenever(fetchProductsInteractor.getProductsByQuery(any())).thenReturn(Single.just(mockProducts(true)))
        viewModel.advanceToSearchProducts(scheduler)
        assertTrue(viewModel.productsSearchState.value is ProductSearchState.ProductResultSuccess)
        val products = (viewModel.productsSearchState.value as ProductSearchState.ProductResultSuccess).products
        assertTrue(products.isEmpty())
    }

    @Test
    fun `when navigateToProductDetail is called, then check navigator navigateToProductDetail is called`() {
        whenever(fetchProductsInteractor.getProductsByQuery(any())).thenReturn(Single.error(Throwable("test error")))
        viewModel.advanceToSearchProducts(scheduler)
        assertTrue(viewModel.productsSearchState.value is ProductSearchState.ProductResultFailure)
    }

    @Test
    fun `when searchProductsByQuery is called, with view loading state, should change productsSearchState to ProductResultFailure`() {
        doNothing().whenever(navigator).navigateToProductDetail(any())
        viewModel.navigateToProductDetail(PRODUCT_2)
        verify(navigator).navigateToProductDetail(any())
    }

    private fun ProductSearchViewModel.advanceToFetchDefaultProducts(scheduler: TestScheduler) {
        { this.getDefaultProducts() } andTrigger scheduler
        { (this.productsSearchState.value as ProductSearchState.Loading).loadedAction.invoke() } andTrigger scheduler
    }

    private fun ProductSearchViewModel.advanceToSearchProducts(scheduler: TestScheduler) {
        { this.searchProductsByQuery("test") } andTrigger scheduler
        { (this.productsSearchState.value as ProductSearchState.Loading).loadedAction.invoke() } andTrigger scheduler
    }

    private fun mockProducts(needsEmptyList: Boolean, vararg products: ProductUiModel): List<ProductUiModel> {
        return if (needsEmptyList) {
            emptyList()
        } else {
            products.asList()
        }
    }

    private fun mockProduct(id: String): ProductUiModel {
        return ProductUiModel(
            id,
            "title",
            "image",
            1,
            "CPO",
            "new",
            1,
            1,
            true,
            "www.link.com"
        )
    }

}
