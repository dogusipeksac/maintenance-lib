# Keep all public classes and methods in the view module
-keep public class com.dogus.maintenance.view.** { public *; }

# Keep ViewBinding classes
-keep class com.dogus.maintenance.view.databinding.** { *; }

# Keep custom view constructors
-keepclassmembers class com.dogus.maintenance.view.MaintenanceView {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Keep Activity and Fragment
-keep class com.dogus.maintenance.view.MaintenanceActivity { *; }
-keep class com.dogus.maintenance.view.MaintenanceFragment { *; }

# Keep Builder classes
-keep class com.dogus.maintenance.view.MaintenanceActivity$Builder { *; }
-keep class com.dogus.maintenance.view.MaintenanceDialog$Builder { *; }

# Keep extension functions
-keep class com.dogus.maintenance.view.MaintenanceActivityKt { *; }
