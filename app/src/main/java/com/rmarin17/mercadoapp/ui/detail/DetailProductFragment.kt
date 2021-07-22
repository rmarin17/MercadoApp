package com.rmarin17.mercadoapp.ui.detail

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rmarin17.mercadoapp.R
import com.rmarin17.mercadoapp.common.ext.currencyFormat
import com.rmarin17.mercadoapp.common.ext.displayImage
import com.rmarin17.mercadoapp.common.ext.visible
import com.rmarin17.mercadoapp.databinding.FragmentDeatilProductBinding
import com.rmarin17.mercadoapp.ui.models.ProductUiModel

/**
 * Fragment of DetailProduct.
 */
class DetailProductFragment : Fragment() {

    private lateinit var binding: FragmentDeatilProductBinding

    private val product by lazy { arguments?.getParcelable<ProductUiModel>(PRODUCT_DETAIL) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDeatilProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        product?.let { productUI->
            with(binding) {
                productDetailTitle.text = productUI.title
                productDetailImage.displayImage(productUI.image)
                productDetailConditionSold.text = getString(R.string.product_detail_condition_sold_items, productUI.condition, productUI.soldQuantity)
                productDetailPrice.text = productUI.price.currencyFormat(productUI.currency)
                productDetailShipping.visible(productUI.isFreeShipping)
                productDetailStockQuantity.text = getString(R.string.product_detail_stock_items, productUI.availableQuantity)
                productDetailBuyButton.setOnClickListener {
                    var intent = Intent(ACTION_VIEW, Uri.parse(productUI.linkProduct))
                    startActivity(intent)
                }
            }
        }
    }

    companion object {
        const val PRODUCT_DETAIL = "product_detail"
    }

}
