package com.dogusipeksac.maintenance.core

/**
 * SAM (Single Abstract Method) interface for retry button click events.
 * This interface is Java-friendly and can be used with lambda expressions in both Kotlin and Java.
 *
 * Example usage in Kotlin:
 * ```kotlin
 * val listener = OnRetryListener { checkServerStatus() }
 * ```
 *
 * Example usage in Java:
 * ```java
 * OnRetryListener listener = () -> checkServerStatus();
 * ```
 */
fun interface OnRetryListener {
    /**
     * Called when the retry button is clicked.
     */
    fun onRetry()
}
