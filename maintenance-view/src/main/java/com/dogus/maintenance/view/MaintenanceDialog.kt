package com.dogusipeksac.maintenance.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.dogusipeksac.maintenance.core.MaintenanceConfig
import com.dogusipeksac.maintenance.core.OnRetryListener
import com.dogusipeksac.maintenance.view.databinding.DialogMaintenanceBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Dialog for displaying maintenance mode as a modal dialog.
 * 
 * Example (Kotlin):
 * ```kotlin
 * val config = MaintenanceConfig(
 *     title = "Bakım Modu",
 *     message = "Sistem güncelleniyor",
 *     showRetryButton = true
 * )
 * MaintenanceDialog.show(this, config)
 * ```
 *
 * Example (Java):
 * ```java
 * MaintenanceConfig config = new MaintenanceConfig(
 *     "Bakım Modu",
 *     "Sistem güncelleniyor",
 *     null, null, null, null,
 *     true, "Tekrar Dene", null
 * );
 * MaintenanceDialog.show(this, config);
 * ```
 */
class MaintenanceDialog private constructor(
    context: Context,
    private val config: MaintenanceConfig,
    private val onRetryListener: OnRetryListener?
) {
    
    private val dialog: AlertDialog
    private val binding: DialogMaintenanceBinding
    
    init {
        binding = DialogMaintenanceBinding.inflate(LayoutInflater.from(context))
        
        dialog = MaterialAlertDialogBuilder(context)
            .setView(binding.root)
            .setCancelable(false)
            .create()
        
        applyConfig()
        setupListeners()
    }
    
    private fun applyConfig() {
        with(binding) {
            tvTitle.text = config.title
            tvMessage.text = config.message
            
            config.iconRes?.let {
                iconMaintenance.setImageResource(it)
            }
            
            config.titleColor?.let {
                tvTitle.setTextColor(it)
            }
            
            config.messageColor?.let {
                tvMessage.setTextColor(it)
            }
            
            if (config.showRetryButton) {
                btnRetry.visibility = View.VISIBLE
                btnRetry.text = config.retryButtonText
            } else {
                btnRetry.visibility = View.GONE
            }
        }
    }
    
    private fun setupListeners() {
        binding.btnRetry.setOnClickListener {
            onRetryListener?.onRetry()
            dismiss()
        }
    }
    
    /**
     * Shows the dialog.
     */
    fun show() {
        dialog.show()
    }
    
    /**
     * Dismisses the dialog.
     */
    fun dismiss() {
        dialog.dismiss()
    }
    
    companion object {
        /**
         * Creates and shows a maintenance dialog with the given configuration.
         * 
         * @param context The context to create the dialog in
         * @param config The maintenance configuration
         * @param onRetry Optional retry listener
         * @return The created dialog instance
         */
        @JvmStatic
        @JvmOverloads
        fun show(
            context: Context,
            config: MaintenanceConfig,
            onRetry: OnRetryListener? = null
        ): MaintenanceDialog {
            return MaintenanceDialog(context, config, onRetry).apply { show() }
        }
        
        /**
         * Creates and shows a maintenance dialog with individual parameters.
         * 
         * @param context The context to create the dialog in
         * @param title The title text
         * @param message The message text
         * @param showRetryButton Whether to show retry button
         * @param onRetry Optional retry listener
         * @return The created dialog instance
         */
        @JvmStatic
        @JvmOverloads
        fun show(
            context: Context,
            title: String,
            message: String,
            showRetryButton: Boolean = false,
            onRetry: OnRetryListener? = null
        ): MaintenanceDialog {
            val config = MaintenanceConfig(
                title = title,
                message = message,
                showRetryButton = showRetryButton
            )
            return show(context, config, onRetry)
        }
    }
    
    /**
     * Builder class for creating MaintenanceDialog.
     * Provides a fluent API for Java interoperability.
     *
     * Example:
     * ```java
     * new MaintenanceDialog.Builder(this)
     *     .setTitle("Bakım Modu")
     *     .setMessage("Sistem güncelleniyor")
     *     .setShowRetryButton(true)
     *     .setOnRetryListener(() -> checkServerStatus())
     *     .show();
     * ```
     */
    class Builder(private val context: Context) {
        private var title: String = context.getString(R.string.default_maintenance_title)
        private var message: String = context.getString(R.string.default_maintenance_message)
        private var iconRes: Int? = null
        private var titleColor: Int? = null
        private var messageColor: Int? = null
        private var showRetryButton: Boolean = false
        private var retryButtonText: String = context.getString(R.string.default_retry_button)
        private var onRetryListener: OnRetryListener? = null
        
        fun setTitle(title: String) = apply { this.title = title }
        fun setMessage(message: String) = apply { this.message = message }
        fun setIconRes(iconRes: Int?) = apply { this.iconRes = iconRes }
        fun setTitleColor(titleColor: Int?) = apply { this.titleColor = titleColor }
        fun setMessageColor(messageColor: Int?) = apply { this.messageColor = messageColor }
        fun setShowRetryButton(show: Boolean) = apply { this.showRetryButton = show }
        fun setRetryButtonText(text: String) = apply { this.retryButtonText = text }
        fun setOnRetryListener(listener: OnRetryListener?) = apply { this.onRetryListener = listener }
        
        fun show(): MaintenanceDialog {
            val config = MaintenanceConfig(
                title = title,
                message = message,
                iconRes = iconRes,
                titleColor = titleColor,
                messageColor = messageColor,
                showRetryButton = showRetryButton,
                retryButtonText = retryButtonText
            )
            return show(context, config, onRetryListener)
        }
    }
}
