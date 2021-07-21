package com.rmarin17.mercadoapp.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rmarin17.mercadoapp.common.ext.currencyFormat
import com.rmarin17.mercadoapp.common.ext.displayImage
import com.rmarin17.mercadoapp.databinding.ItemProductBinding
import com.rmarin17.mercadoapp.ui.models.ProductListUiModel

/**
 * View holder of products list adapter.
 */
class ListProductsViewHolder(
    view: View,
    private val onItemClick: (String) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(itemView)

    fun bind(product: ProductListUiModel) {
        with(product) {
            binding.itemProductImage.displayImage(image)
            binding.itemProductTitle.text = title
            binding.itemProductPrice.text = price.currencyFormat(currency)
            binding.itemProductCard.setOnClickListener {
                onItemClick.invoke(id)
            }
        }
    }

}
