# Keep all public classes and methods in the core module
-keep public class com.dogusipeksac.maintenance.core.** { public *; }

# Keep Parcelable implementation
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Keep data class generated methods
-keepclassmembers class com.dogusipeksac.maintenance.core.MaintenanceConfig {
    public <init>(...);
    public *** component*();
    public *** copy(...);
}

# Keep SAM interface
-keep interface com.dogusipeksac.maintenance.core.OnRetryListener {
    public *;
}

# Keep sealed class hierarchy
-keep class com.dogusipeksac.maintenance.core.MaintenanceState { *; }
-keep class com.dogusipeksac.maintenance.core.MaintenanceState$* { *; }
