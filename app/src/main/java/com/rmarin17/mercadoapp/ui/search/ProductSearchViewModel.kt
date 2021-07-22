package com.rmarin17.mercadoapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rmarin17.mercadoapp.common.BaseViewModel
import com.rmarin17.mercadoapp.common.ext.applyIOSubscribeMainThread
import com.rmarin17.mercadoapp.common.logger.Logger
import com.rmarin17.mercadoapp.domain.interactors.FetchProductsInteractor
import com.rmarin17.mercadoapp.ui.navigator.HomeNavigator
import com.rmarin17.mercadoapp.ui.models.ProductUiModel
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

/**
 * Class to acquire and keep the information that is necessary to show in fragment search.
 */
class ProductSearchViewModel @Inject constructor(
    private val fetchProductsInteractor: FetchProductsInteractor,
    private val logger: Logger,
    private val navigator: HomeNavigator
) : BaseViewModel() {

    private val _productsSearchState = MutableLiveData<ProductSearchState>()
    val productsSearchState: LiveData<ProductSearchState> get() = _productsSearchState

    fun getDefaultProducts() {
        _productsSearchState.value = ProductSearchState.Loading {
            addDisposable(executeFetchDefaultProducts())
        }
    }

    fun searchProductsByQuery(query: String) {
        _productsSearchState.value = ProductSearchState.Loading {
            addDisposable(executeFetchProductsByQuery(query))
        }
    }

    fun navigateToProductDetail(product: ProductUiModel) {
        navigator.navigateToProductDetail(product)
    }

    private fun executeFetchDefaultProducts(): Disposable {
        return fetchProductsInteractor.getDefaultsProducts()
            .applyIOSubscribeMainThread()
            .subscribe(
                { products ->
                    _productsSearchState.value = ProductSearchState.ProductResultSuccess(products)
                }, {
                    _productsSearchState.value = ProductSearchState.ProductResultFailure
                    logger.logMessage(this.javaClass.name, "Error on getDefaultProducts due to: ${it.localizedMessage}", Logger.Level.ERROR)
                }
            )
    }

    private fun executeFetchProductsByQuery(query: String): Disposable {
        return fetchProductsInteractor.getProductsByQuery(query)
            .applyIOSubscribeMainThread()
            .subscribe(
                { products ->
                    _productsSearchState.value = ProductSearchState.ProductResultSuccess(products)
                }, {
                    _productsSearchState.value = ProductSearchState.ProductResultFailure
                    logger.logMessage(this.javaClass.name, "Error on searchProductsByQuery due to: ${it.localizedMessage}", Logger.Level.ERROR)
                }
            )
    }
}
