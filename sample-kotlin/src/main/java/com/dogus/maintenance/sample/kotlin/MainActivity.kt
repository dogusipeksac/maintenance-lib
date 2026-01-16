package com.dogusipeksac.maintenance.sample.kotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dogusipeksac.maintenance.core.MaintenanceConfig
import com.dogusipeksac.maintenance.sample.kotlin.databinding.ActivityMainBinding
import com.dogusipeksac.maintenance.view.MaintenanceActivity
import com.dogusipeksac.maintenance.view.MaintenanceDialog
import com.dogusipeksac.maintenance.view.MaintenanceFragment
import com.dogusipeksac.maintenance.view.showMaintenance

/**
 * Sample activity demonstrating all usage patterns of the Maintenance library with Kotlin.
 * 
 * This activity showcases:
 * 1. Full-screen activity
 * 2. Dialog
 * 3. Fragment
 * 4. Custom View
 * 5. Kotlin DSL
 * 6. Config-based approach
 */
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private var isCustomViewVisible = false
    private var isFragmentVisible = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupListeners()
        setupCustomView()
    }
    
    private fun setupListeners() {
        binding.btnShowActivity.setOnClickListener {
            showMaintenanceActivity()
        }
        
        binding.btnShowDialog.setOnClickListener {
            showMaintenanceDialog()
        }
        
        binding.btnShowFragment.setOnClickListener {
            toggleFragment()
        }
        
        binding.btnShowWithDsl.setOnClickListener {
            showMaintenanceWithDsl()
        }
        
        binding.btnShowWithConfig.setOnClickListener {
            showMaintenanceWithConfig()
        }
        
        binding.btnToggleCustomView.setOnClickListener {
            toggleCustomView()
        }
    }
    
    private fun setupCustomView() {
        binding.maintenanceView.setOnRetryClickListener {
            showToast("Retry clicked from Custom View!")
            checkServerStatus()
        }
    }
    
    /**
     * Example 1: Show maintenance as a full-screen activity
     */
    private fun showMaintenanceActivity() {
        MaintenanceActivity.show(
            context = this,
            title = "Bakım Modu",
            message = "Uygulama şu anda bakımdadır. Lütfen daha sonra tekrar deneyin.",
            showRetryButton = true
        )
    }
    
    /**
     * Example 2: Show maintenance as a dialog
     */
    private fun showMaintenanceDialog() {
        val config = MaintenanceConfig(
            title = "Bakım Çalışması",
            message = "Sistem güncelleniyor. Bu işlem birkaç dakika sürebilir.",
            showRetryButton = true,
            retryButtonText = "Yeniden Dene"
        )
        
        MaintenanceDialog.show(this, config) {
            showToast("Retry clicked from Dialog!")
            checkServerStatus()
        }
    }
    
    /**
     * Example 3: Show maintenance as a fragment
     */
    private fun toggleFragment() {
        if (isFragmentVisible) {
            supportFragmentManager.beginTransaction()
                .remove(supportFragmentManager.findFragmentById(binding.fragmentContainer.id)!!)
                .commit()
            binding.fragmentContainer.visibility = View.GONE
            binding.btnShowFragment.text = getString(R.string.show_fragment)
            isFragmentVisible = false
        } else {
            val config = MaintenanceConfig(
                title = "Bakım Modu",
                message = "Fragment içinde bakım modu gösterimi",
                showRetryButton = true
            )
            
            val fragment = MaintenanceFragment.newInstance(config)
            fragment.setOnRetryListener {
                showToast("Retry clicked from Fragment!")
                checkServerStatus()
            }
            
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, fragment)
                .commit()
            
            binding.fragmentContainer.visibility = View.VISIBLE
            binding.btnShowFragment.text = "Hide Fragment"
            isFragmentVisible = true
        }
    }
    
    /**
     * Example 4: Show maintenance using Kotlin DSL
     */
    private fun showMaintenanceWithDsl() {
        showMaintenance {
            title("Bakım Modu (DSL)")
            message("Kotlin DSL kullanarak bakım modu gösterimi")
            showRetryButton(true)
            retryButtonText("Tekrar Dene")
        }
    }
    
    /**
     * Example 5: Show maintenance with advanced configuration
     */
    private fun showMaintenanceWithConfig() {
        val config = MaintenanceConfig.builder()
            .title("Gelişmiş Bakım Modu")
            .message("Özelleştirilmiş renkler ve ayarlar ile bakım modu")
            .showRetryButton(true)
            .retryButtonText("Yeniden Dene")
            .build()
        
        MaintenanceActivity.show(this, config)
    }
    
    /**
     * Example 6: Toggle custom view visibility
     */
    private fun toggleCustomView() {
        if (isCustomViewVisible) {
            binding.maintenanceView.visibility = View.GONE
            binding.btnToggleCustomView.text = getString(R.string.show_custom_view)
            isCustomViewVisible = false
        } else {
            binding.maintenanceView.visibility = View.VISIBLE
            binding.btnToggleCustomView.text = getString(R.string.hide_custom_view)
            isCustomViewVisible = true
        }
    }
    
    /**
     * Simulates checking server status
     */
    private fun checkServerStatus() {
        showToast("Sunucu durumu kontrol ediliyor...")
        // In real app, make network request here
    }
    
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
