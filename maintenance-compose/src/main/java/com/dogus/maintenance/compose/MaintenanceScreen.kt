package com.dogusipeksac.maintenance.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dogusipeksac.maintenance.core.MaintenanceConfig

/**
 * Full-screen composable for displaying maintenance mode.
 *
 * This composable displays a maintenance screen with an icon, title, message, and optional retry button.
 *
 * Example usage:
 * ```kotlin
 * MaintenanceScreen(
 *     config = MaintenanceConfig(
 *         title = "Bakım Modu",
 *         message = "Sistem güncelleniyor",
 *         showRetryButton = true
 *     ),
 *     onRetry = { checkServerStatus() }
 * )
 * ```
 *
 * @param config The maintenance configuration
 * @param modifier Modifier to be applied to the root layout
 * @param onRetry Callback invoked when retry button is clicked
 */
@Composable
fun MaintenanceScreen(
    config: MaintenanceConfig,
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null
) {
    val backgroundColor = config.backgroundColor?.let { Color(it) } 
        ?: MaterialTheme.colorScheme.background
    
    Surface(
        modifier = modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(24.dp)
            ) {
                config.iconRes?.let { iconRes ->
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = "Maintenance icon",
                        modifier = Modifier.size(120.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                }
                
                val titleColor = config.titleColor?.let { Color(it) }
                    ?: MaterialTheme.colorScheme.onBackground
                
                Text(
                    text = config.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = titleColor,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                val messageColor = config.messageColor?.let { Color(it) }
                    ?: MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                
                Text(
                    text = config.message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = messageColor,
                    textAlign = TextAlign.Center
                )
                
                if (config.showRetryButton && onRetry != null) {
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Button(onClick = onRetry) {
                        Text(config.retryButtonText)
                    }
                }
            }
        }
    }
}

/**
 * Overloaded version of MaintenanceScreen with individual parameters for easier usage.
 *
 * Example usage:
 * ```kotlin
 * MaintenanceScreen(
 *     title = "Bakım Modu",
 *     message = "Sistem güncelleniyor",
 *     showRetryButton = true,
 *     onRetry = { checkServerStatus() }
 * )
 * ```
 *
 * @param title The title text to display
 * @param message The message text to display
 * @param modifier Modifier to be applied to the root layout
 * @param icon Optional composable for custom icon
 * @param showRetryButton Whether to show the retry button
 * @param retryButtonText Text for the retry button
 * @param onRetry Callback invoked when retry button is clicked
 */
@Composable
fun MaintenanceScreen(
    title: String = "Bakım Modu",
    message: String = "Uygulama şu anda bakımdadır",
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    showRetryButton: Boolean = false,
    retryButtonText: String = "Tekrar Dene",
    onRetry: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(24.dp)
            ) {
                icon?.let {
                    it()
                    Spacer(modifier = Modifier.height(24.dp))
                }
                
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
                
                if (showRetryButton && onRetry != null) {
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Button(onClick = onRetry) {
                        Text(retryButtonText)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MaintenanceScreenPreview() {
    MaterialTheme {
        MaintenanceScreen(
            title = "Bakım Modu",
            message = "Uygulama şu anda bakımdadır. Lütfen daha sonra tekrar deneyin.",
            showRetryButton = true,
            onRetry = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MaintenanceScreenWithoutButtonPreview() {
    MaterialTheme {
        MaintenanceScreen(
            title = "Bakım Modu",
            message = "Uygulama şu anda bakımdadır. Lütfen daha sonra tekrar deneyin.",
            showRetryButton = false
        )
    }
}
