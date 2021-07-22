package com.rmarin17.mercadoapp.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rmarin17.mercadoapp.R
import com.rmarin17.mercadoapp.common.di.ViewModelFactory
import com.rmarin17.mercadoapp.common.ext.activityInjector
import com.rmarin17.mercadoapp.common.ext.getViewModel
import com.rmarin17.mercadoapp.common.ext.observe
import com.rmarin17.mercadoapp.common.ext.visible
import com.rmarin17.mercadoapp.common.observers.ActivityLifeCycleObserver
import com.rmarin17.mercadoapp.databinding.FragmentSearchBinding
import com.rmarin17.mercadoapp.ui.adapters.ListProductsAdapter
import com.rmarin17.mercadoapp.ui.models.ProductUiModel
import javax.inject.Inject

/**
 * Fragment to search products.
 */
class SearchFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<ProductSearchViewModel>

    private val viewModel by lazy { getViewModel<ProductSearchViewModel>(viewModelFactory) }

    private lateinit var binding: FragmentSearchBinding

    private lateinit var productsAdapter: ListProductsAdapter

    private val searchQuery by lazy { arguments?.getString(SEAR_QUERY) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().lifecycle.addObserver(ActivityLifeCycleObserver {
            activityInjector().inject(this)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpObservers()
        lifecycle.addObserver(viewModel)
        searchQueryOrDefaultProduct()
    }

    private fun setUpView() {
        with(binding) {
            productsAdapter = ListProductsAdapter(mutableListOf(), ::onClickItem)
            productsRecycler.layoutManager = LinearLayoutManager(context)
            productsRecycler.adapter = productsAdapter
            val searchManager =
                requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView.apply {
                setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
                queryHint = resources.getString(R.string.query_hint_search)
                isQueryRefinementEnabled = true
            }
            searchQuery?.let {
                searchView.setQuery(it, false)
            }
        }
    }

    private fun setUpObservers() {
        observe(viewModel.productsSearchState, ::onSearchState)
    }

    private fun onSearchState(state: ProductSearchState) {
        when (state) {
            is ProductSearchState.Loading -> showHideLoadingView(true)
            is ProductSearchState.ProductResultSuccess -> {
                showHideLoadingView(false)
                if (state.products.isNotEmpty()) {
                    productsAdapter.updateProductList(state.products)
                } else {
                    binding.emptyView.visible()
                }
            }
            is ProductSearchState.ProductResultFailure -> showFailureErrorView()
        }
    }

    private fun searchQueryOrDefaultProduct() {
        searchQuery?.let {
            viewModel.searchProductsByQuery(it)
        } ?: viewModel.getDefaultProducts()
    }

    private fun onClickItem(product: ProductUiModel) {
        viewModel.navigateToProductDetail(product)
    }

    private fun showHideLoadingView(isVisible: Boolean) {
        binding.emptyView.visible(false)
        binding.errorView.visible(false)
        binding.progressBar.visible(isVisible)
        binding.productsRecycler.visible(!isVisible)
    }

    private fun showFailureErrorView() {
        showHideLoadingView(false)
        binding.errorView.visible()
        binding.errorView.errorViewRetryButton.setOnClickListener {
            searchQueryOrDefaultProduct()
        }
    }

    companion object {
        const val SEAR_QUERY = "search_query"
    }
}
