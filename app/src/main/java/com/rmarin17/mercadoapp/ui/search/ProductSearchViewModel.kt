package com.rmarin17.mercadoapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rmarin17.mercadoapp.common.BaseViewModel
import com.rmarin17.mercadoapp.common.ext.applyIOSubscribeMainThread
import com.rmarin17.mercadoapp.domain.interactors.FetchProductsInteractor
import javax.inject.Inject

/**
 * Class to acquire and keep the information that is necessary to show in fragment search.
 */
class ProductSearchViewModel @Inject constructor(private val fetchProductsInteractor: FetchProductsInteractor) : BaseViewModel() {

    private val _productsSearchState = MutableLiveData<ProductSearchState>()
    val productsSearchState: LiveData<ProductSearchState> get() = _productsSearchState

    fun getDefaultProducts() {
        _productsSearchState.value = ProductSearchState.Loading
        addDisposable(
            fetchProductsInteractor.getDefaultsProducts()
                .applyIOSubscribeMainThread()
                .subscribe(
                    { products ->
                        // TODO - notify the view about the products.
                        products
                    }, {
                        Log.e(this.javaClass.name, "Error on getDefaultProducts due to: ${it.localizedMessage}")
                    }
                )
        )
    }
}
