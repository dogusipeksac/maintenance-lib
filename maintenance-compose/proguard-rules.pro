# Keep all public classes and methods in the compose module
-keep public class com.dogusipeksac.maintenance.compose.** { public *; }

# Keep Compose functions
-keep class com.dogusipeksac.maintenance.compose.MaintenanceScreenKt { *; }
-keep class com.dogusipeksac.maintenance.compose.MaintenanceDialogKt { *; }
-keep class com.dogusipeksac.maintenance.compose.MaintenanceStateKt { *; }

# Keep Activity
-keep class com.dogusipeksac.maintenance.compose.MaintenanceComposeActivity { *; }

# Compose specific rules
-dontwarn androidx.compose.**
-keep class androidx.compose.** { *; }
