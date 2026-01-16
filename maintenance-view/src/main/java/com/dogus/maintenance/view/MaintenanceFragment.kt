package com.dogus.maintenance.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dogus.maintenance.core.MaintenanceConfig
import com.dogus.maintenance.core.MaintenanceConstants.EXTRA_CONFIG
import com.dogus.maintenance.core.OnRetryListener
import com.dogus.maintenance.view.databinding.ViewMaintenanceBinding

/**
 * Fragment for displaying maintenance mode.
 * 
 * This fragment can be used in ViewPager, Navigation Component, or any other fragment container.
 *
 * Example:
 * ```kotlin
 * val config = MaintenanceConfig(
 *     title = "Bakım Modu",
 *     message = "Sistem güncelleniyor"
 * )
 * val fragment = MaintenanceFragment.newInstance(config)
 * 
 * supportFragmentManager.beginTransaction()
 *     .replace(R.id.container, fragment)
 *     .commit()
 * ```
 */
class MaintenanceFragment : Fragment() {
    
    private var _binding: ViewMaintenanceBinding? = null
    private val binding get() = _binding!!
    
    private var onRetryListener: OnRetryListener? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewMaintenanceBinding.inflate(inflater, container!!)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val config = arguments?.getParcelable<MaintenanceConfig>(EXTRA_CONFIG)
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
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object {
        /**
         * Creates a new instance of MaintenanceFragment with the given configuration.
         * 
         * @param config The maintenance configuration
         * @return A new MaintenanceFragment instance
         */
        @JvmStatic
        fun newInstance(config: MaintenanceConfig): MaintenanceFragment {
            return MaintenanceFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_CONFIG, config)
                }
            }
        }
        
        /**
         * Creates a new instance of MaintenanceFragment with individual parameters.
         * 
         * @param title The title text
         * @param message The message text
         * @param showRetryButton Whether to show retry button
         * @return A new MaintenanceFragment instance
         */
        @JvmStatic
        @JvmOverloads
        fun newInstance(
            title: String,
            message: String,
            showRetryButton: Boolean = false
        ): MaintenanceFragment {
            val config = MaintenanceConfig(
                title = title,
                message = message,
                showRetryButton = showRetryButton
            )
            return newInstance(config)
        }
    }
}
