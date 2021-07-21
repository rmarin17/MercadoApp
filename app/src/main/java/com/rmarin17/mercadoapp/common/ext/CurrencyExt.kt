package com.rmarin17.mercadoapp.common.ext

import com.rmarin17.mercadoapp.common.logger.LoggerImpl
import java.text.NumberFormat
import java.util.Currency

/**
 * File that contains all the currency extensions functions.
 */

/**
 * Extension function to get the currency format from Int.
 */
fun Int.currencyFormat(currencyCode: String): String = try {
    val format: NumberFormat = NumberFormat.getCurrencyInstance().apply {
        maximumFractionDigits = 0
        currency = Currency.getInstance(currencyCode)
    }
    format.format(this)
} catch (e: Exception) {
    LoggerImpl().logMessage("CurrencyFormatExt", "Error due to ${e.localizedMessage}")
    ""
}
