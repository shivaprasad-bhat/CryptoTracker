package com.svbneelmane.cryptotracker.core.domain.util

/**
 * Enum class that contains different types of network errors
 * @author shivaprasad-bhat
 * @creation on 18-Oct-2024
 */
enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    UNKNOWN
}

