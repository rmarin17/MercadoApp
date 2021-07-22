package com.rmarin17.mercadoapp.common.ext

/**
 * File that contains all the network extensions functions.
 */

/**
 * Extension function to replace http to https in a url.
 */
// TODO - Remove this workaround when the service is updated and use https instead of http.
fun String.fixUrl(): String {
    return if (!this.contains("https")) {
        this.replace("http://", "https://")
    } else {
        this
    }
}
