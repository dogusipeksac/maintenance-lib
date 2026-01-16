package com.dogus.maintenance.sample.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dogus.maintenance.compose.MaintenanceComposeActivity
import com.dogus.maintenance.compose.MaintenanceDialog
import com.dogus.maintenance.compose.MaintenanceScreen
import com.dogus.maintenance.compose.rememberMaintenanceState
import com.dogus.maintenance.core.MaintenanceConfig
import com.dogus.maintenance.core.MaintenanceState

/**
 * Sample activity demonstrating all usage patterns of the Maintenance library with Jetpack Compose.
 * 
 * This activity showcases:
 * 1. Full-screen maintenance screen
 * 2. Dialog
 * 3. Inline maintenance view
 * 4. State management
 * 5. Activity wrapper
 * 6. Config-based approach
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var showInlineView by remember { mutableStateOf(false) }
    val maintenanceState = rememberMaintenanceState()
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (val state = maintenanceState.value) {
            is MaintenanceState.Active -> {
                MaintenanceScreen(
                    config = state.config,
                    onRetry = {
                        showToast(context, "Retry clicked!")
                        maintenanceState.value = MaintenanceState.Resolved
                    }
                )
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Maintenance Mode Examples",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    
                    Text(
                        text = "Jetpack Compose",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(
                        onClick = {
                            val config = MaintenanceConfig(
                                title = "Bakım Modu",
                                message = "Uygulama şu anda bakımdadır. Lütfen daha sonra tekrar deneyin.",
                                showRetryButton = true
                            )
                            MaintenanceComposeActivity.show(context, config)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Show as Activity")
                    }
                    
                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Show as Dialog")
                    }
                    
                    Button(
                        onClick = { showInlineView = !showInlineView },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (showInlineView) "Hide Inline View" else "Show Inline View")
                    }
                    
                    Button(
                        onClick = {
                            val config = MaintenanceConfig(
                                title = "Bakım Modu (State)",
                                message = "State management ile bakım modu",
                                showRetryButton = true
                            )
                            maintenanceState.value = MaintenanceState.Active(config)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Show with State Management")
                    }
                    
                    Button(
                        onClick = {
                            val config = MaintenanceConfig(
                                title = "Bakım Modu",
                                message = "Basit kullanım örneği",
                                showRetryButton = true
                            )
                            maintenanceState.value = MaintenanceState.Active(config)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Show with Simple Config")
                    }
                    
                    Button(
                        onClick = {
                            val config = MaintenanceConfig.builder()
                                .title("Gelişmiş Bakım Modu")
                                .message("Builder pattern ile oluşturuldu")
                                .showRetryButton(true)
                                .retryButtonText("Yeniden Dene")
                                .build()
                            MaintenanceComposeActivity.show(context, config)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Show with Builder Pattern")
                    }
                    
                    if (showInlineView) {
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            MaintenanceScreen(
                                title = "Inline Bakım Modu",
                                message = "Bu bir inline görünümdür",
                                showRetryButton = true,
                                onRetry = {
                                    showToast(context, "Retry clicked from inline view!")
                                    showInlineView = false
                                }
                            )
                        }
                    }
                }
            }
        }
        
        if (showDialog) {
            MaintenanceDialog(
                config = MaintenanceConfig(
                    title = "Bakım Çalışması",
                    message = "Sistem güncelleniyor. Bu işlem birkaç dakika sürebilir.",
                    showRetryButton = true,
                    retryButtonText = "Yeniden Dene"
                ),
                onDismissRequest = { showDialog = false },
                onRetry = {
                    showToast(context, "Retry clicked from dialog!")
                }
            )
        }
    }
}

private fun showToast(context: android.content.Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen()
    }
}
