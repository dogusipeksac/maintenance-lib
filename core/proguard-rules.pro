# Keep all public classes and methods in the core module
-keep public class com.dogus.maintenance.core.** { public *; }

# Keep Parcelable implementation
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Keep data class generated methods
-keepclassmembers class com.dogus.maintenance.core.MaintenanceConfig {
    public <init>(...);
    public *** component*();
    public *** copy(...);
}

# Keep SAM interface
-keep interface com.dogus.maintenance.core.OnRetryListener {
    public *;
}

# Keep sealed class hierarchy
-keep class com.dogus.maintenance.core.MaintenanceState { *; }
-keep class com.dogus.maintenance.core.MaintenanceState$* { *; }
