# Maintenance Mode Library 🔧

[![](https://jitpack.io/v/dogusipeksac/maintenance-lib.svg)](https://jitpack.io/#dogusipeksac/maintenance-lib)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

A modern, modular Android library for displaying maintenance mode with support for:
- ✅ **Kotlin** (Android View - XML)
- ✅ **Java** (Android View - XML)  
- ✅ **Jetpack Compose**

## 📱 Screenshots

| Activity | Dialog | Custom View | Compose |
|----------|--------|-------------|---------|
| 📱 **Activity** | 💬 **Dialog** | 🎨 **Custom View** | ⚡ **Compose** |
| Full-screen maintenance mode | Modal dialog overlay | Custom view component | Declarative Compose UI |

## ✨ Features

- 🎨 **Material Design 3** - Modern and beautiful UI
- 🌙 **Dark Mode Support** - Automatic theme switching
- 📱 **Responsive Design** - Works on all screen sizes
- ✨ **Smooth Animations** - Polished user experience
- 🔧 **Fully Customizable** - Colors, icons, messages, and more
- 🌍 **Multi-language Support** - Easy localization
- 🎯 **Type-safe DSL** (Kotlin) - Intuitive API
- ☕ **Java-friendly API** - Builder pattern support
- 🚀 **Jetpack Compose Ready** - Modern declarative UI
- 📦 **Modular Architecture** - Use only what you need
- 🔒 **ProGuard Ready** - Optimized for production
- 🧪 **Well Tested** - Reliable and stable

## 📦 Installation

### Step 1: Add JitPack repository

Add JitPack to your root `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Or in `build.gradle` (old style):

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add dependency

Choose the module(s) you need:

#### For Android View (Kotlin/Java)
```kotlin
dependencies {
    implementation("com.github.dogusipeksac.maintenance-lib:maintenance-view:1.0.0")
}
```

#### For Jetpack Compose
```kotlin
dependencies {
    implementation("com.github.dogusipeksac.maintenance-lib:maintenance-compose:1.0.0")
}
```

#### For Both
```kotlin
dependencies {
    implementation("com.github.dogusipeksac.maintenance-lib:maintenance-view:1.0.0")
    implementation("com.github.dogusipeksac.maintenance-lib:maintenance-compose:1.0.0")
}
```

## 🚀 Quick Start

### Kotlin (Android View)

#### 1. Full-screen Activity
```kotlin
// Simple usage
MaintenanceActivity.show(
    context = this,
    title = "Bakım Modu",
    message = "Uygulama güncelleniyor",
    showRetryButton = true
)

// With config
val config = MaintenanceConfig(
    title = "Bakım Modu",
    message = "Sistem güncelleniyor",
    showRetryButton = true,
    retryButtonText = "Tekrar Dene"
)
MaintenanceActivity.show(this, config)

// With Kotlin DSL
showMaintenance {
    title("Bakım Modu")
    message("Sistem güncelleniyor")
    showRetryButton(true)
}
```

#### 2. Dialog
```kotlin
val config = MaintenanceConfig(
    title = "Bakım Çalışması",
    message = "Lütfen daha sonra tekrar deneyin",
    showRetryButton = true
)

MaintenanceDialog.show(this, config) {
    // Retry button clicked
    checkServerStatus()
}
```

#### 3. Fragment
```kotlin
val config = MaintenanceConfig(
    title = "Bakım Modu",
    message = "Uygulama güncelleniyor"
)

val fragment = MaintenanceFragment.newInstance(config)
fragment.setOnRetryListener {
    checkServerStatus()
}

supportFragmentManager.beginTransaction()
    .replace(R.id.container, fragment)
    .commit()
```

#### 4. Custom View (XML)
```xml
<com.dogusipeksac.maintenance.view.MaintenanceView
    android:id="@+id/maintenanceView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:maintenanceTitle="Bakım Modu"
    app:maintenanceMessage="Uygulama güncelleniyor"
    app:showRetryButton="true"
    app:retryButtonText="Tekrar Dene" />
```

```kotlin
// In code
maintenanceView.setOnRetryClickListener {
    checkServerStatus()
}
```

### Java

#### 1. Full-screen Activity
```java
// Simple usage
MaintenanceActivity.show(
    this,
    "Bakım Modu",
    "Uygulama güncelleniyor",
    true,
    null
);

// With Builder pattern
new MaintenanceActivity.Builder(this)
    .setTitle("Bakım Modu")
    .setMessage("Sistem güncelleniyor")
    .setShowRetryButton(true)
    .setOnRetryListener(() -> checkServerStatus())
    .show();

// With Config
MaintenanceConfig config = new MaintenanceConfig(
    "Bakım Modu",
    "Sistem güncelleniyor",
    null, null, null, null,
    true,
    "Tekrar Dene",
    null
);
MaintenanceActivity.show(this, config);

// With Config Builder
MaintenanceConfig config = MaintenanceConfig.builder()
    .title("Bakım Modu")
    .message("Sistem güncelleniyor")
    .showRetryButton(true)
    .build();
MaintenanceActivity.show(this, config);
```

#### 2. Dialog
```java
new MaintenanceDialog.Builder(this)
    .setTitle("Bakım Çalışması")
    .setMessage("Lütfen daha sonra tekrar deneyin")
    .setShowRetryButton(true)
    .setOnRetryListener(() -> checkServerStatus())
    .show();
```

#### 3. Custom View
```java
MaintenanceView maintenanceView = findViewById(R.id.maintenanceView);
maintenanceView.setTitle("Bakım Modu");
maintenanceView.setMessage("Uygulama güncelleniyor");
maintenanceView.setShowRetryButton(true);
maintenanceView.setOnRetryClickListener(() -> checkServerStatus());
```

### Jetpack Compose

#### 1. Full-screen Screen
```kotlin
@Composable
fun MyScreen() {
    MaintenanceScreen(
        title = "Bakım Modu",
        message = "Uygulama güncelleniyor",
        showRetryButton = true,
        onRetry = { checkServerStatus() }
    )
}

// With config
val config = MaintenanceConfig(
    title = "Bakım Modu",
    message = "Sistem güncelleniyor",
    showRetryButton = true
)

MaintenanceScreen(
    config = config,
    onRetry = { checkServerStatus() }
)
```

#### 2. Dialog
```kotlin
var showDialog by remember { mutableStateOf(true) }

if (showDialog) {
    MaintenanceDialog(
        title = "Bakım Çalışması",
        message = "Lütfen daha sonra tekrar deneyin",
        showRetryButton = true,
        onDismissRequest = { showDialog = false },
        onRetry = { checkServerStatus() }
    )
}

// With config
MaintenanceDialog(
    config = MaintenanceConfig(
        title = "Bakım Modu",
        message = "Sistem güncelleniyor",
        showRetryButton = true
    ),
    onDismissRequest = { showDialog = false },
    onRetry = { checkServerStatus() }
)
```

#### 3. Activity Wrapper
```kotlin
// Launch from traditional Activity/Fragment
val config = MaintenanceConfig(
    title = "Bakım Modu",
    message = "Sistem güncelleniyor"
)
MaintenanceComposeActivity.show(context, config)
```

#### 4. State Management
```kotlin
val maintenanceState = rememberMaintenanceState()

when (val state = maintenanceState.value) {
    is MaintenanceState.Active -> {
        MaintenanceScreen(
            config = state.config,
            onRetry = {
                maintenanceState.value = MaintenanceState.Resolved
            }
        )
    }
    else -> {
        // Show normal content
        NormalContent()
    }
}
```

## 🎨 Customization

### Colors and Styling

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
```

### Custom Attributes (XML)

```xml
<com.dogusipeksac.maintenance.view.MaintenanceView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:maintenanceTitle="Bakım Modu"
    app:maintenanceMessage="Özelleştirilmiş mesaj"
    app:maintenanceIcon="@drawable/ic_custom"
    app:maintenanceBackgroundColor="@color/custom_bg"
    app:maintenanceTitleColor="@color/custom_title"
    app:maintenanceMessageColor="@color/custom_message"
    app:showRetryButton="true"
    app:retryButtonText="Tekrar Dene" />
```

## 📚 Advanced Usage

### Network Status Check

```kotlin
class MainActivity : AppCompatActivity() {
    
    private fun checkMaintenanceStatus() {
        lifecycleScope.launch {
            try {
                val response = api.getMaintenanceStatus()
                
                if (response.isInMaintenance) {
                    val config = MaintenanceConfig(
                        title = response.title,
                        message = response.message,
                        showRetryButton = true
                    )
                    
                    MaintenanceActivity.show(this@MainActivity, config)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
```

### With ViewModel (Compose)

```kotlin
class MainViewModel : ViewModel() {
    private val _maintenanceState = MutableStateFlow<MaintenanceState>(MaintenanceState.Idle)
    val maintenanceState: StateFlow<MaintenanceState> = _maintenanceState
    
    fun checkMaintenance() {
        viewModelScope.launch {
            _maintenanceState.value = MaintenanceState.Loading
            
            try {
                val response = api.getMaintenanceStatus()
                
                if (response.isInMaintenance) {
                    val config = MaintenanceConfig(
                        title = response.title,
                        message = response.message,
                        showRetryButton = true
                    )
                    _maintenanceState.value = MaintenanceState.Active(config)
                } else {
                    _maintenanceState.value = MaintenanceState.Resolved
                }
            } catch (e: Exception) {
                _maintenanceState.value = MaintenanceState.Idle
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val state by viewModel.maintenanceState.collectAsState()
    
    when (val currentState = state) {
        is MaintenanceState.Active -> {
            MaintenanceScreen(
                config = currentState.config,
                onRetry = { viewModel.checkMaintenance() }
            )
        }
        MaintenanceState.Loading -> {
            LoadingScreen()
        }
        else -> {
            NormalContent()
        }
    }
}
```

## 🏗️ Architecture

```
maintenance-lib/
├── core/                    # Core models and interfaces
│   ├── MaintenanceConfig    # Configuration data class
│   ├── MaintenanceState     # State sealed class
│   ├── OnRetryListener      # SAM interface
│   └── MaintenanceConstants # Constants
│
├── maintenance-view/        # Android View module
│   ├── MaintenanceActivity  # Full-screen activity
│   ├── MaintenanceDialog    # Dialog
│   ├── MaintenanceFragment  # Fragment
│   └── MaintenanceView      # Custom view
│
├── maintenance-compose/     # Jetpack Compose module
│   ├── MaintenanceScreen    # Composable screen
│   ├── MaintenanceDialog    # Composable dialog
│   └── MaintenanceComposeActivity # Activity wrapper
│
├── sample-kotlin/           # Kotlin sample app
├── sample-java/             # Java sample app
└── sample-compose/          # Compose sample app
```

## 🔒 ProGuard

The library includes consumer ProGuard rules. No additional configuration needed.

If you want to add custom rules:

```proguard
-keep class com.dogusipeksac.maintenance.** { *; }
```

## 🌍 Localization

Create localized strings in your app:

```xml
<!-- res/values/strings.xml -->
<string name="maintenance_title">Maintenance Mode</string>
<string name="maintenance_message">App is under maintenance</string>

<!-- res/values-tr/strings.xml -->
<string name="maintenance_title">Bakım Modu</string>
<string name="maintenance_message">Uygulama bakımdadır</string>
```

Use in code:

```kotlin
val config = MaintenanceConfig(
    title = getString(R.string.maintenance_title),
    message = getString(R.string.maintenance_message)
)
```

## 🧪 Testing

### Unit Testing

```kotlin
@Test
fun `test maintenance config creation`() {
    val config = MaintenanceConfig(
        title = "Test Title",
        message = "Test Message",
        showRetryButton = true
    )
    
    assertEquals("Test Title", config.title)
    assertEquals("Test Message", config.message)
    assertTrue(config.showRetryButton)
}
```

### UI Testing (Compose)

```kotlin
@Test
fun maintenanceScreenDisplaysCorrectly() {
    composeTestRule.setContent {
        MaintenanceScreen(
            title = "Test Title",
            message = "Test Message",
            showRetryButton = true
        )
    }
    
    composeTestRule.onNodeWithText("Test Title").assertIsDisplayed()
    composeTestRule.onNodeWithText("Test Message").assertIsDisplayed()
}
```

## 📖 Documentation

- [API Documentation](docs/API.md)
- [Migration Guide](docs/MIGRATION.md)
- [Changelog](CHANGELOG.md)
- [Contributing](CONTRIBUTING.md)

## 🤝 Contributing

Contributions are welcome! Please read our [Contributing Guide](CONTRIBUTING.md) for details.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

```
MIT License

Copyright (c) 2024 Doğuş İpeksaç

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 👨‍💻 Author

**Doğuş İpeksaç**
- GitHub: [@dogusipeksac](https://github.com/dogusipeksac)

## ⭐ Support

If you find this library helpful, please give it a ⭐ on GitHub!

## 📱 Sample Apps

Check out the sample apps in the repository:
- `sample-kotlin` - Kotlin + Android View examples
- `sample-java` - Java + Android View examples
- `sample-compose` - Jetpack Compose examples

## 🔗 Links

- [GitHub Repository](https://github.com/dogusipeksac/maintenance-lib)
- [Issue Tracker](https://github.com/dogusipeksac/maintenance-lib/issues)
- [JitPack](https://jitpack.io/#dogusipeksac/maintenance-lib)

---

Made with ❤️ by Doğuş İpeksaç
