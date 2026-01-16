package com.dogus.maintenance.core

/**
 * Sealed class representing different states of maintenance mode.
 * Useful for state management in ViewModels and Compose state hoisting.
 */
sealed class MaintenanceState {
    /**
     * Initial state, no maintenance mode active.
     */
    object Idle : MaintenanceState()
    
    /**
     * Loading state, checking maintenance status.
     */
    object Loading : MaintenanceState()
    
    /**
     * Maintenance mode is active with the given configuration.
     *
     * @property config The maintenance configuration to display
     */
    data class Active(val config: MaintenanceConfig) : MaintenanceState()
    
    /**
     * Maintenance mode has been resolved/dismissed.
     */
    object Resolved : MaintenanceState()
}
