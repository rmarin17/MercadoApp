package com.rmarin17.mercadoapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rmarin17.mercadoapp.common.ext.activityInjector
import com.rmarin17.mercadoapp.common.observers.ActivityLifeCycleObserver
import com.rmarin17.mercadoapp.databinding.FragmentSearchBinding

/**
 * Fragment to search products.
 */
class SearchFragment : Fragment() {

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
}
