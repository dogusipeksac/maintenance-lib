package com.dogus.maintenance.core

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

/**
 * Configuration class for maintenance mode display.
 *
 * @property title The title text to display
 * @property message The message text to display
 * @property iconRes Optional drawable resource ID for the icon
 * @property backgroundColor Optional background color
 * @property titleColor Optional title text color
 * @property messageColor Optional message text color
 * @property showRetryButton Whether to show a retry button
 * @property retryButtonText Text for the retry button
 * @property customData Optional custom data map for extensions
 */
@Parcelize
data class MaintenanceConfig @JvmOverloads constructor(
    val title: String = "Bakım Modu",
    val message: String = "Uygulama şu anda bakımdadır",
    @DrawableRes val iconRes: Int? = null,
    @ColorInt val backgroundColor: Int? = null,
    @ColorInt val titleColor: Int? = null,
    @ColorInt val messageColor: Int? = null,
    val showRetryButton: Boolean = false,
    val retryButtonText: String = "Tekrar Dene",
    val customData: Map<String, String>? = null
) : Parcelable {
    
    /**
     * Builder class for creating MaintenanceConfig instances with a fluent API.
     * Useful for Java interoperability and complex configurations.
     */
    class Builder {
        private var title: String = "Bakım Modu"
        private var message: String = "Uygulama şu anda bakımdadır"
        private var iconRes: Int? = null
        private var backgroundColor: Int? = null
        private var titleColor: Int? = null
        private var messageColor: Int? = null
        private var showRetryButton: Boolean = false
        private var retryButtonText: String = "Tekrar Dene"
        private var customData: Map<String, String>? = null
        
        fun title(title: String) = apply { this.title = title }
        fun message(message: String) = apply { this.message = message }
        fun iconRes(@DrawableRes iconRes: Int?) = apply { this.iconRes = iconRes }
        fun backgroundColor(@ColorInt backgroundColor: Int?) = apply { this.backgroundColor = backgroundColor }
        fun titleColor(@ColorInt titleColor: Int?) = apply { this.titleColor = titleColor }
        fun messageColor(@ColorInt messageColor: Int?) = apply { this.messageColor = messageColor }
        fun showRetryButton(showRetryButton: Boolean) = apply { this.showRetryButton = showRetryButton }
        fun retryButtonText(retryButtonText: String) = apply { this.retryButtonText = retryButtonText }
        fun customData(customData: Map<String, String>?) = apply { this.customData = customData }
        
        fun build() = MaintenanceConfig(
            title = title,
            message = message,
            iconRes = iconRes,
            backgroundColor = backgroundColor,
            titleColor = titleColor,
            messageColor = messageColor,
            showRetryButton = showRetryButton,
            retryButtonText = retryButtonText,
            customData = customData
        )
    }
    
    companion object {
        /**
         * Creates a new Builder instance for Java interoperability.
         */
        @JvmStatic
        fun builder() = Builder()
    }
}
