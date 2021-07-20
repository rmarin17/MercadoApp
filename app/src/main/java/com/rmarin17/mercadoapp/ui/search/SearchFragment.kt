package com.rmarin17.mercadoapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rmarin17.mercadoapp.common.di.ViewModelFactory
import com.rmarin17.mercadoapp.common.ext.activityInjector
import com.rmarin17.mercadoapp.common.ext.getViewModel
import com.rmarin17.mercadoapp.common.ext.observe
import com.rmarin17.mercadoapp.common.ext.visible
import com.rmarin17.mercadoapp.common.observers.ActivityLifeCycleObserver
import com.rmarin17.mercadoapp.databinding.FragmentSearchBinding
import javax.inject.Inject

/**
 * Fragment to search products.
 */
class SearchFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<ProductSearchViewModel>

    private val viewModel by lazy { getViewModel<ProductSearchViewModel>(viewModelFactory) }

    private lateinit var binding: FragmentSearchBinding

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
        setUpObservers()
        lifecycle.addObserver(viewModel)
        viewModel.getDefaultProducts()
    }

    private fun setUpObservers() {
        observe(viewModel.productsSearchState, ::onSearchState)
    }

    private fun onSearchState(state: ProductSearchState) {
        when (state) {
            is ProductSearchState.Loading -> showLoadingView()
        }
    }

    private fun showLoadingView() {
        binding.progressBar.visible()
        binding.productsRecycler.visible(false)
    }
}
