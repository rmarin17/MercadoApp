package com.rmarin17.mercadoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rmarin17.mercadoapp.common.di.ActivityInjector
import com.rmarin17.mercadoapp.common.ext.appComponent
import com.rmarin17.mercadoapp.databinding.ActivityHomeBinding
import com.rmarin17.mercadoapp.di.HomeComponent
import com.rmarin17.mercadoapp.ui.search.SearchFragment

/**
 * Activity entry point.
 */
class HomeActivity : AppCompatActivity(), ActivityInjector {

    private lateinit var homeComponent: HomeComponent

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        homeComponent = appComponent().homeComponentFactory.create(this).apply {
            inject(this@HomeActivity)
        }
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun <T> inject(fragment: T) {
        when (fragment) {
            is SearchFragment -> homeComponent.inject(fragment)
        }
    }
}
