# Quick Start Guide

Get started with the Maintenance Mode Library in 5 minutes!

## 📦 Installation

### Step 1: Add JitPack Repository

In your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Step 2: Add Dependencies

In your app's `build.gradle.kts`:

```kotlin
dependencies {
    // For Android View (Kotlin/Java)
    implementation("com.github.dogusipeksac.maintenance-lib:maintenance-view:1.0.0")
    
    // For Jetpack Compose
    implementation("com.github.dogusipeksac.maintenance-lib:maintenance-compose:1.0.0")
}
```

## 🚀 Basic Usage

### Kotlin (Android View)

```kotlin
import com.dogusipeksac.maintenance.view.MaintenanceActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Show maintenance mode
        MaintenanceActivity.show(
            context = this,
            title = "Bakım Modu",
            message = "Uygulama güncelleniyor",
            showRetryButton = true
        )
    }
}
```

### Java

```java
import com.dogusipeksac.maintenance.view.MaintenanceActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Show maintenance mode
        new MaintenanceActivity.Builder(this)
            .setTitle("Bakım Modu")
            .setMessage("Uygulama güncelleniyor")
            .setShowRetryButton(true)
            .show();
    }
}
```

### Jetpack Compose

```kotlin
import com.dogusipeksac.maintenance.compose.MaintenanceScreen

@Composable
fun MyApp() {
    MaintenanceScreen(
        title = "Bakım Modu",
        message = "Uygulama güncelleniyor",
        showRetryButton = true,
        onRetry = { /* Handle retry */ }
    )
}
```

## 📱 More Examples

### Dialog (Kotlin)

```kotlin
import com.dogusipeksac.maintenance.view.MaintenanceDialog
import com.dogusipeksac.maintenance.core.MaintenanceConfig

val config = MaintenanceConfig(
    title = "Bakım Çalışması",
    message = "Lütfen daha sonra tekrar deneyin",
    showRetryButton = true
)

MaintenanceDialog.show(this, config) {
    // Retry clicked
}
```

### Custom View (XML)

```xml
<com.dogusipeksac.maintenance.view.MaintenanceView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:maintenanceTitle="Bakım Modu"
    app:maintenanceMessage="Uygulama güncelleniyor"
    app:showRetryButton="true" />
```

### Compose Dialog

```kotlin
var showDialog by remember { mutableStateOf(true) }

if (showDialog) {
    MaintenanceDialog(
        title = "Bakım Modu",
        message = "Sistem güncelleniyor",
        showRetryButton = true,
        onDismissRequest = { showDialog = false },
        onRetry = { /* Handle retry */ }
    )
}
```

## 🎨 Customization

```kotlin
val config = MaintenanceConfig(
    title = "Bakım Modu",
    message = "Özelleştirilmiş görünüm",
    iconRes = R.drawable.ic_custom_icon,
    backgroundColor = Color.parseColor("#F5F5F5"),
    titleColor = Color.parseColor("#1976D2"),
    messageColor = Color.parseColor("#757575"),
    showRetryButton = true,
    retryButtonText = "Yeniden Dene"
)

MaintenanceActivity.show(this, config)
```

## 📚 Full Documentation

- [README.md](README.md) - Complete documentation
- [Sample Apps](sample-kotlin/src/main/java/com/dogus/maintenance/sample/kotlin/MainActivity.kt) - Working examples
- [API Documentation](docs/API.md) - Detailed API reference

## 🆘 Need Help?

- [GitHub Issues](https://github.com/dogusipeksac/maintenance-lib/issues)
- [Contributing Guide](CONTRIBUTING.md)

## ⭐ Support

If you find this library helpful, please give it a ⭐ on GitHub!

---

Happy coding! 🚀
