package com.rmarin17.mercadoapp.ui.home

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.rmarin17.mercadoapp.R
import com.rmarin17.mercadoapp.common.ext.findNavController
import com.rmarin17.mercadoapp.common.ext.navigateSafe
import com.rmarin17.mercadoapp.common.ext.performActionWithSafeActivity
import com.rmarin17.mercadoapp.ui.detail.DetailProductFragment.Companion.PRODUCT_DETAIL
import com.rmarin17.mercadoapp.ui.models.ProductUiModel
import com.rmarin17.mercadoapp.ui.search.SearchFragment.Companion.SEAR_QUERY
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Class to handle the navigation events of home activity.
 */
class HomeNavigatorImpl @Inject constructor(activity: HomeActivity) : HomeNavigator {

    private val weakActivity = WeakReference(activity)

    private fun Activity.findHomeNavController(): NavController {
        return findNavController(R.id.nav_host_fragment)
    }

    override fun navigateToSearchQuery(query: String) {
        val bundle = bundleOf(SEAR_QUERY to query)
        weakActivity.performActionWithSafeActivity {
            it.findHomeNavController().navigateSafe(R.id.action_searchFragment_self, bundle, null)
        }
    }

    override fun navigateToProductDetail(product: ProductUiModel) {
        val bundle = bundleOf(PRODUCT_DETAIL to product)
        weakActivity.performActionWithSafeActivity {
            it.findHomeNavController().navigateSafe(R.id.action_searchFragment_to_detailProductFragment, bundle, null)
        }
    }
}
