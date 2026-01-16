package com.dogus.maintenance.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.dogus.maintenance.core.MaintenanceConfig
import com.dogus.maintenance.core.MaintenanceConstants.EXTRA_CONFIG

/**
 * Activity wrapper for displaying maintenance mode using Jetpack Compose.
 * 
 * This activity provides a bridge between traditional Android activities and Compose-based UI.
 * It can be launched from both Kotlin and Java code.
 *
 * Example (Kotlin):
 * ```kotlin
 * val config = MaintenanceConfig(
 *     title = "Bakım Modu",
 *     message = "Sistem güncelleniyor"
 * )
 * MaintenanceComposeActivity.show(this, config)
 * ```
 *
 * Example (Java):
 * ```java
 * MaintenanceConfig config = new MaintenanceConfig(
 *     "Bakım Modu",
 *     "Sistem güncelleniyor",
 *     null, null, null, null,
 *     false, "Tekrar Dene", null
 * );
 * MaintenanceComposeActivity.show(this, config);
 * ```
 */
class MaintenanceComposeActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val config = intent.getParcelableExtra<MaintenanceConfig>(EXTRA_CONFIG)
            ?: MaintenanceConfig()
        
        setContent {
            MaterialTheme {
                MaintenanceScreen(config = config)
            }
        }
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
            val intent = Intent(context, MaintenanceComposeActivity::class.java).apply {
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
         */
        @JvmStatic
        @JvmOverloads
        fun show(
            context: Context,
            title: String = "Bakım Modu",
            message: String = "Uygulama güncelleniyor",
            showRetryButton: Boolean = false
        ) {
            val config = MaintenanceConfig(
                title = title,
                message = message,
                showRetryButton = showRetryButton
            )
            show(context, config)
        }
    }
}
