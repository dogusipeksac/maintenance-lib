package com.dogus.maintenance.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.dogus.maintenance.core.MaintenanceState

/**
 * Remember a mutable state for [MaintenanceState].
 * 
 * This is useful for managing maintenance mode state in Compose.
 *
 * Example usage:
 * ```kotlin
 * val maintenanceState = rememberMaintenanceState()
 * 
 * when (val state = maintenanceState.value) {
 *     is MaintenanceState.Active -> {
 *         MaintenanceScreen(config = state.config)
 *     }
 *     else -> {
 *         // Show normal content
 *     }
 * }
 * ```
 *
 * @param initialState The initial state (default is [MaintenanceState.Idle])
 * @return A mutable state holding the maintenance state
 */
@Composable
fun rememberMaintenanceState(
    initialState: MaintenanceState = MaintenanceState.Idle
): MutableState<MaintenanceState> {
    return remember { mutableStateOf(initialState) }
}
