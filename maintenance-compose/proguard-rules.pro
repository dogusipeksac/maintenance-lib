# Keep all public classes and methods in the compose module
-keep public class com.dogus.maintenance.compose.** { public *; }

# Keep Compose functions
-keep class com.dogus.maintenance.compose.MaintenanceScreenKt { *; }
-keep class com.dogus.maintenance.compose.MaintenanceDialogKt { *; }
-keep class com.dogus.maintenance.compose.MaintenanceStateKt { *; }

# Keep Activity
-keep class com.dogus.maintenance.compose.MaintenanceComposeActivity { *; }

# Compose specific rules
-dontwarn androidx.compose.**
-keep class androidx.compose.** { *; }
