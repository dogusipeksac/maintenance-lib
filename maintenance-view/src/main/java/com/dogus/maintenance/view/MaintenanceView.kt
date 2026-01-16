package com.dogusipeksac.maintenance.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.dogusipeksac.maintenance.core.MaintenanceConfig
import com.dogusipeksac.maintenance.core.OnRetryListener
import com.dogusipeksac.maintenance.view.databinding.ViewMaintenanceBinding

/**
 * Custom view for displaying maintenance mode inline in layouts.
 * 
 * This view can be used directly in XML layouts with custom attributes:
 * ```xml
 * <com.dogusipeksac.maintenance.view.MaintenanceView
 *     android:layout_width="match_parent"
 *     android:layout_height="match_parent"
 *     app:maintenanceTitle="Bakım Modu"
 *     app:maintenanceMessage="Sistem güncelleniyor"
 *     app:showRetryButton="true" />
 * ```
 *
 * Or programmatically:
 * ```kotlin
 * val maintenanceView = MaintenanceView(context)
 * maintenanceView.title = "Bakım Modu"
 * maintenanceView.message = "Sistem güncelleniyor"
 * maintenanceView.showRetryButton = true
 * maintenanceView.setOnRetryClickListener { checkServerStatus() }
 * ```
 */
class MaintenanceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    
    private val binding: ViewMaintenanceBinding
    private var onRetryClickListener: OnRetryListener? = null
    
    /**
     * The title text to display.
     */
    var title: String = ""
        @JvmName("getTitle")
        get() = field
        @JvmName("setTitle")
        set(value) {
            field = value
            binding.tvTitle.text = value
        }
    
    /**
     * The message text to display.
     */
    var message: String = ""
        @JvmName("getMessage")
        get() = field
        @JvmName("setMessage")
        set(value) {
            field = value
            binding.tvMessage.text = value
        }
    
    /**
     * Whether to show the retry button.
     */
    var showRetryButton: Boolean = false
        @JvmName("getShowRetryButton")
        get() = field
        @JvmName("setShowRetryButton")
        set(value) {
            field = value
            binding.btnRetry.visibility = if (value) View.VISIBLE else View.GONE
        }
    
    /**
     * The retry button text.
     */
    var retryButtonText: String = ""
        @JvmName("getRetryButtonText")
        get() = field
        @JvmName("setRetryButtonText")
        set(value) {
            field = value
            binding.btnRetry.text = value
        }
    
    init {
        binding = ViewMaintenanceBinding.inflate(LayoutInflater.from(context), this)
        
        // Initialize with default values from resources
        title = context.getString(R.string.default_maintenance_title)
        message = context.getString(R.string.default_maintenance_message)
        retryButtonText = context.getString(R.string.default_retry_button)
        
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.MaintenanceView)
            
            try {
                title = typedArray.getString(R.styleable.MaintenanceView_maintenanceTitle) ?: title
                message = typedArray.getString(R.styleable.MaintenanceView_maintenanceMessage) ?: message
                retryButtonText = typedArray.getString(R.styleable.MaintenanceView_retryButtonText) ?: retryButtonText
                
                showRetryButton = typedArray.getBoolean(R.styleable.MaintenanceView_showRetryButton, false)
                
                val iconRes = typedArray.getResourceId(R.styleable.MaintenanceView_maintenanceIcon, -1)
                if (iconRes != -1) {
                    binding.iconMaintenance.setImageResource(iconRes)
                }
                
                if (typedArray.hasValue(R.styleable.MaintenanceView_maintenanceBackgroundColor)) {
                    val bgColor = typedArray.getColor(R.styleable.MaintenanceView_maintenanceBackgroundColor, 0)
                    setBackgroundColor(bgColor)
                }
                
                if (typedArray.hasValue(R.styleable.MaintenanceView_maintenanceTitleColor)) {
                    val titleColor = typedArray.getColor(R.styleable.MaintenanceView_maintenanceTitleColor, 0)
                    binding.tvTitle.setTextColor(titleColor)
                }
                
                if (typedArray.hasValue(R.styleable.MaintenanceView_maintenanceMessageColor)) {
                    val messageColor = typedArray.getColor(R.styleable.MaintenanceView_maintenanceMessageColor, 0)
                    binding.tvMessage.setTextColor(messageColor)
                }
            } finally {
                typedArray.recycle()
            }
        }
        
        setupListeners()
    }
    
    private fun setupListeners() {
        binding.btnRetry.setOnClickListener {
            onRetryClickListener?.onRetry()
        }
    }
    
    /**
     * Sets a listener for retry button clicks.
     * 
     * @param listener The listener to be invoked when retry is clicked
     */
    @JvmName("setOnRetryClickListener")
    fun setOnRetryClickListener(listener: OnRetryListener?) {
        this.onRetryClickListener = listener
    }
    
    /**
     * Applies a [MaintenanceConfig] to this view.
     * 
     * @param config The configuration to apply
     */
    fun applyConfig(config: MaintenanceConfig) {
        title = config.title
        message = config.message
        showRetryButton = config.showRetryButton
        retryButtonText = config.retryButtonText
        
        config.iconRes?.let {
            binding.iconMaintenance.setImageResource(it)
        }
        
        config.backgroundColor?.let {
            setBackgroundColor(it)
        }
        
        config.titleColor?.let {
            binding.tvTitle.setTextColor(it)
        }
        
        config.messageColor?.let {
            binding.tvMessage.setTextColor(it)
        }
    }
    
    /**
     * Sets the icon resource.
     * 
     * @param resId The drawable resource ID
     */
    fun setIconResource(resId: Int) {
        binding.iconMaintenance.setImageResource(resId)
    }
}
