package com.rmarin17.mercadoapp.ui.home

import androidx.core.os.bundleOf
import com.rmarin17.mercadoapp.R
import com.rmarin17.mercadoapp.common.ext.findNavController
import com.rmarin17.mercadoapp.common.ext.navigateSafe
import com.rmarin17.mercadoapp.common.ext.performActionWithSafeActivity
import com.rmarin17.mercadoapp.ui.search.SearchFragment.Companion.SEAR_QUERY
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Class to handle the navigation events of home activity.
 */
class HomeNavigatorImpl @Inject constructor(activity: HomeActivity) : HomeNavigator {

    private val weakActivity = WeakReference(activity)

    override fun navigateToSearchQuery(query: String) {
        val bundle = bundleOf(SEAR_QUERY to query)
        weakActivity.performActionWithSafeActivity {
            it.findNavController(R.id.nav_host_fragment).navigateSafe(R.id.action_searchFragment_self, bundle, null)
        }
    }
}
