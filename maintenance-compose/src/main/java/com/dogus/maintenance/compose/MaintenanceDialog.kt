package com.dogusipeksac.maintenance.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
 * Dialog composable for displaying maintenance mode as a modal dialog.
 *
 * Example usage:
 * ```kotlin
 * var showDialog by remember { mutableStateOf(true) }
 * 
 * if (showDialog) {
 *     MaintenanceDialog(
 *         config = MaintenanceConfig(
 *             title = "Bakım Modu",
 *             message = "Sistem güncelleniyor",
 *             showRetryButton = true
 *         ),
 *         onDismissRequest = { showDialog = false },
 *         onRetry = { checkServerStatus() }
 *     )
 * }
 * ```
 *
 * @param config The maintenance configuration
 * @param onDismissRequest Callback invoked when dialog is dismissed
 * @param onRetry Callback invoked when retry button is clicked
 */
@Composable
fun MaintenanceDialog(
    config: MaintenanceConfig,
    onDismissRequest: () -> Unit = {},
    onRetry: (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        icon = {
            config.iconRes?.let { iconRes ->
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = "Maintenance icon",
                    modifier = Modifier.size(48.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
        },
        title = {
            val titleColor = config.titleColor?.let { Color(it) }
                ?: MaterialTheme.colorScheme.onSurface
            
            Text(
                text = config.title,
                color = titleColor,
                textAlign = TextAlign.Center
            )
        },
        text = {
            val messageColor = config.messageColor?.let { Color(it) }
                ?: MaterialTheme.colorScheme.onSurfaceVariant
            
            Text(
                text = config.message,
                color = messageColor,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            if (config.showRetryButton && onRetry != null) {
                TextButton(
                    onClick = {
                        onRetry()
                        onDismissRequest()
                    }
                ) {
                    Text(config.retryButtonText)
                }
            }
        }
    )
}

/**
 * Overloaded version of MaintenanceDialog with individual parameters.
 *
 * Example usage:
 * ```kotlin
 * var showDialog by remember { mutableStateOf(true) }
 * 
 * if (showDialog) {
 *     MaintenanceDialog(
 *         title = "Bakım Modu",
 *         message = "Sistem güncelleniyor",
 *         showRetryButton = true,
 *         onDismissRequest = { showDialog = false },
 *         onRetry = { checkServerStatus() }
 *     )
 * }
 * ```
 *
 * @param title The title text to display
 * @param message The message text to display
 * @param icon Optional composable for custom icon
 * @param showRetryButton Whether to show the retry button
 * @param retryButtonText Text for the retry button
 * @param onDismissRequest Callback invoked when dialog is dismissed
 * @param onRetry Callback invoked when retry button is clicked
 */
@Composable
fun MaintenanceDialog(
    title: String = "Bakım Modu",
    message: String = "Uygulama şu anda bakımdadır",
    icon: (@Composable () -> Unit)? = null,
    showRetryButton: Boolean = false,
    retryButtonText: String = "Tekrar Dene",
    onDismissRequest: () -> Unit = {},
    onRetry: (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        icon = icon,
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = message,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            if (showRetryButton && onRetry != null) {
                TextButton(
                    onClick = {
                        onRetry()
                        onDismissRequest()
                    }
                ) {
                    Text(retryButtonText)
                }
            }
        }
    )
}

@Preview
@Composable
private fun MaintenanceDialogPreview() {
    MaterialTheme {
        MaintenanceDialog(
            title = "Bakım Modu",
            message = "Uygulama şu anda bakımdadır. Lütfen daha sonra tekrar deneyin.",
            showRetryButton = true,
            onRetry = {}
        )
    }
}

@Preview
@Composable
private fun MaintenanceDialogWithoutButtonPreview() {
    MaterialTheme {
        MaintenanceDialog(
            title = "Bakım Modu",
            message = "Uygulama şu anda bakımdadır. Lütfen daha sonra tekrar deneyin.",
            showRetryButton = false
        )
    }
}
