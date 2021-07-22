package com.rmarin17.mercadoapp.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Class to handle the product information to show in UI.
 */
@Parcelize
data class ProductUiModel(
    val id: String,
    val title: String,
    val image: String,
    val price: Int,
    val currency: String,
    val condition: String,
    val soldQuantity: Int,
    val availableQuantity: Int,
    val isFreeShipping: Boolean,
    val linkProduct: String
) : Parcelable
