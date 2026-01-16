package com.dogus.maintenance.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dogus.maintenance.core.MaintenanceConfig
import com.dogus.maintenance.core.MaintenanceConstants.EXTRA_CONFIG
import com.dogus.maintenance.core.OnRetryListener
import com.dogus.maintenance.view.databinding.ActivityMaintenanceBinding

/**
 * Full-screen activity for displaying maintenance mode.
 * 
 * This activity can be launched in multiple ways:
 * - Using [show] with a [MaintenanceConfig] object
 * - Using [show] with individual parameters
 * - Using [Builder] for Java-friendly API
 * - Using [showMaintenance] extension function for Kotlin DSL
 *
 * Example (Kotlin):
 * ```kotlin
 * MaintenanceActivity.show(this, MaintenanceConfig(
 *     title = "Bakım Modu",
 *     message = "Sistem güncelleniyor"
 * ))
 * ```
 *
 * Example (Java):
 * ```java
 * new MaintenanceActivity.Builder(this)
 *     .setTitle("Bakım Modu")
 *     .setMessage("Sistem güncelleniyor")
 *     .show();
 * ```
 */
class MaintenanceActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMaintenanceBinding
    private var onRetryListener: OnRetryListener? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaintenanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val config = intent.getParcelableExtra<MaintenanceConfig>(EXTRA_CONFIG)
            ?: MaintenanceConfig(
                title = getString(R.string.default_maintenance_title),
                message = getString(R.string.default_maintenance_message),
                retryButtonText = getString(R.string.default_retry_button)
            )
        
        applyConfig(config)
        setupListeners()
    }
    
    private fun applyConfig(config: MaintenanceConfig) {
        with(binding) {
            tvTitle.text = config.title
            tvMessage.text = config.message
            
            config.iconRes?.let {
                iconMaintenance.setImageResource(it)
            }
            
            config.backgroundColor?.let {
                rootLayout.setBackgroundColor(it)
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
        }
    }
    
    /**
     * Sets a listener for retry button clicks.
     * 
     * @param listener The listener to be invoked when retry is clicked
     */
    @JvmName("setOnRetryListener")
    fun setOnRetryListener(listener: OnRetryListener?) {
        this.onRetryListener = listener
    }
    
    companion object {
        /**
         * Shows the maintenance activity with the given configuration.
         * 
         * @param context The context to start the activity from
         * @param config The maintenance configuration
         */
        @JvmStatic
        fun show(context: Context, config: MaintenanceConfig) {
            val intent = Intent(context, MaintenanceActivity::class.java).apply {
                putExtra(EXTRA_CONFIG, config)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
        
        /**
         * Shows the maintenance activity with individual parameters.
         * 
         * @param context The context to start the activity from
         * @param title The title text
         * @param message The message text
         * @param showRetryButton Whether to show retry button
         * @param onRetry Optional retry listener
         */
        @JvmStatic
        @JvmOverloads
        fun show(
            context: Context,
            title: String,
            message: String,
            showRetryButton: Boolean = false,
            onRetry: OnRetryListener? = null
        ) {
            val config = MaintenanceConfig(
                title = title,
                message = message,
                showRetryButton = showRetryButton
            )
            show(context, config)
        }
    }
    
    /**
     * Builder class for creating and showing MaintenanceActivity.
     * Provides a fluent API for Java interoperability.
     *
     * Example:
     * ```java
     * new MaintenanceActivity.Builder(this)
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
        private var backgroundColor: Int? = null
        private var titleColor: Int? = null
        private var messageColor: Int? = null
        private var showRetryButton: Boolean = false
        private var retryButtonText: String = context.getString(R.string.default_retry_button)
        private var onRetryListener: OnRetryListener? = null
        
        fun setTitle(title: String) = apply { this.title = title }
        fun setMessage(message: String) = apply { this.message = message }
        fun setIconRes(iconRes: Int?) = apply { this.iconRes = iconRes }
        fun setBackgroundColor(backgroundColor: Int?) = apply { this.backgroundColor = backgroundColor }
        fun setTitleColor(titleColor: Int?) = apply { this.titleColor = titleColor }
        fun setMessageColor(messageColor: Int?) = apply { this.messageColor = messageColor }
        fun setShowRetryButton(show: Boolean) = apply { this.showRetryButton = show }
        fun setRetryButtonText(text: String) = apply { this.retryButtonText = text }
        fun setOnRetryListener(listener: OnRetryListener?) = apply { this.onRetryListener = listener }
        
        fun show() {
            val config = MaintenanceConfig(
                title = title,
                message = message,
                iconRes = iconRes,
                backgroundColor = backgroundColor,
                titleColor = titleColor,
                messageColor = messageColor,
                showRetryButton = showRetryButton,
                retryButtonText = retryButtonText
            )
            show(context, config)
        }
    }
}

/**
 * Kotlin DSL extension function for showing maintenance mode.
 *
 * Example:
 * ```kotlin
 * context.showMaintenance {
 *     title = "Bakım Modu"
 *     message = "Sistem güncelleniyor"
 *     showRetryButton = true
 *     onRetry = { checkServerStatus() }
 * }
 * ```
 */
inline fun Context.showMaintenance(block: MaintenanceConfig.Builder.() -> Unit) {
    val config = MaintenanceConfig.Builder().apply(block).build()
    MaintenanceActivity.show(this, config)
}
