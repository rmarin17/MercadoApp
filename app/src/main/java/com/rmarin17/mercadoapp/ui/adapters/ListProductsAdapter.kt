package com.rmarin17.mercadoapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rmarin17.mercadoapp.R
import com.rmarin17.mercadoapp.common.ext.inflate
import com.rmarin17.mercadoapp.ui.adapters.viewholders.ListProductsViewHolder
import com.rmarin17.mercadoapp.ui.models.ProductListUiModel

/**
 * Adapter for list products.
 */
class ListProductsAdapter(
    private val products: MutableList<ProductListUiModel>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ListProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductsViewHolder {
        return ListProductsViewHolder(
            parent.inflate(R.layout.item_product),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: ListProductsViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateProductList(listProducts: List<ProductListUiModel>) {
        products.clear()
        products.addAll(listProducts)
        notifyDataSetChanged()
    }
}
