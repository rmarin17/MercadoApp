package com.rmarin17.mercadoapp.common.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rmarin17.mercadoapp.R

/**
 * File that contains all the glide extensions functions.
 */

/**
 * Extension function to load an image from URL into ImageView.
 */
fun ImageView.displayImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_default_product_image)
        .error(R.drawable.ic_broken_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .skipMemoryCache(false)
        .into(this)
}
