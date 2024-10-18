package com.svbneelmane.cryptotracker.core.data.networking

/**
 * File that contains the method to construct the URL
 * @author shivaprasad-bhat
 *
 */
import com.svbneelmane.cryptotracker.BuildConfig

/**
 * function that construct the URL based on the input
 * Base URL will be referred from build file
 * @return URL [String]
 */
fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}