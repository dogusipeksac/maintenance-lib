# Project Structure

Complete file tree of the Maintenance Mode Library project.

```
maintenance-lib/
│
├── 📁 core/                                    # Core module (shared logic)
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   ├── consumer-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       └── java/com/dogus/maintenance/core/
│           ├── MaintenanceConfig.kt            # Configuration data class
│           ├── MaintenanceState.kt             # State sealed class
│           ├── OnRetryListener.kt              # SAM interface
│           └── MaintenanceConstants.kt         # Constants
│
├── 📁 maintenance-view/                        # Android View module
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   ├── consumer-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/dogus/maintenance/view/
│       │   ├── MaintenanceActivity.kt          # Full-screen activity
│       │   ├── MaintenanceDialog.kt            # Dialog
│       │   ├── MaintenanceFragment.kt          # Fragment
│       │   └── MaintenanceView.kt              # Custom view
│       └── res/
│           ├── drawable/
│           │   └── ic_maintenance.xml          # Default icon
│           ├── layout/
│           │   ├── activity_maintenance.xml    # Activity layout
│           │   ├── view_maintenance.xml        # Custom view layout
│           │   └── dialog_maintenance.xml      # Dialog layout
│           ├── values/
│           │   ├── attrs.xml                   # Custom attributes
│           │   ├── strings.xml                 # Strings
│           │   └── styles.xml                  # Styles (light theme)
│           └── values-night/
│               └── styles.xml                  # Styles (dark theme)
│
├── 📁 maintenance-compose/                     # Jetpack Compose module
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   ├── consumer-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       └── java/com/dogus/maintenance/compose/
│           ├── MaintenanceScreen.kt            # Screen composable
│           ├── MaintenanceDialog.kt            # Dialog composable
│           ├── MaintenanceComposeActivity.kt   # Activity wrapper
│           └── MaintenanceState.kt             # State helpers
│
├── 📁 sample-kotlin/                           # Kotlin sample app
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/dogus/maintenance/sample/kotlin/
│       │   └── MainActivity.kt                 # Sample activity
│       └── res/
│           ├── layout/
│           │   └── activity_main.xml           # Sample layout
│           ├── values/
│           │   ├── strings.xml
│           │   ├── colors.xml
│           │   └── themes.xml
│           └── mipmap-*/                       # App icons
│
├── 📁 sample-java/                             # Java sample app
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/dogus/maintenance/sample/java/
│       │   └── MainActivity.java               # Sample activity
│       └── res/
│           ├── layout/
│           │   └── activity_main.xml           # Sample layout
│           ├── values/
│           │   ├── strings.xml
│           │   ├── colors.xml
│           │   └── themes.xml
│           └── mipmap-*/                       # App icons
│
├── 📁 sample-compose/                          # Compose sample app
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/dogus/maintenance/sample/compose/
│       │   └── MainActivity.kt                 # Sample activity
│       ├── res/
│       │   ├── values/
│       │   │   └── strings.xml
│       │   └── mipmap-*/                       # App icons
│
├── 📁 gradle/                                  # Gradle wrapper
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
│
├── 📄 build.gradle.kts                         # Root build file
├── 📄 settings.gradle.kts                      # Settings file
├── 📄 gradle.properties                        # Gradle properties
├── 📄 gradlew                                  # Gradle wrapper (Unix)
├── 📄 gradlew.bat                              # Gradle wrapper (Windows)
│
├── 📄 README.md                                # Main documentation
├── 📄 CHANGELOG.md                             # Version history
├── 📄 LICENSE                                  # MIT License
├── 📄 CONTRIBUTING.md                          # Contribution guide
├── 📄 JITPACK_PUBLISH.md                       # JitPack guide
├── 📄 GITHUB_SETUP.md                          # GitHub setup guide
├── 📄 PROJECT_STRUCTURE.md                     # This file
│
├── 📄 jitpack.yml                              # JitPack configuration
├── 📄 .gitignore                               # Git ignore rules
└── 📄 local.properties                         # Local SDK path (ignored)
```

## Module Dependencies

```
sample-kotlin ──────► maintenance-view ──────► core
                                                 ▲
sample-java ────────► maintenance-view ──────────┘
                                                 │
sample-compose ─────► maintenance-compose ───────┘
```

## Key Files Description

### Core Module
- **MaintenanceConfig.kt**: Parcelable configuration class with all customization options
- **MaintenanceState.kt**: Sealed class for state management (Idle, Loading, Active, Resolved)
- **OnRetryListener.kt**: SAM interface for retry callbacks (Java-friendly)
- **MaintenanceConstants.kt**: Shared constants across modules

### Maintenance-View Module
- **MaintenanceActivity.kt**: Full-screen activity with Builder pattern and Kotlin DSL
- **MaintenanceDialog.kt**: AlertDialog wrapper with customization
- **MaintenanceFragment.kt**: Fragment for flexible integration
- **MaintenanceView.kt**: Custom ConstraintLayout with XML attributes
- **Layouts**: Material Design 3 layouts with dark mode support
- **Styles**: Theme-aware styling

### Maintenance-Compose Module
- **MaintenanceScreen.kt**: Full-screen composable with previews
- **MaintenanceDialog.kt**: AlertDialog composable
- **MaintenanceComposeActivity.kt**: Bridge between traditional and Compose
- **MaintenanceState.kt**: Compose state management helpers

### Sample Apps
- **sample-kotlin**: Demonstrates all View-based features with Kotlin
- **sample-java**: Demonstrates all View-based features with Java
- **sample-compose**: Demonstrates all Compose features

## Build Configuration

### Root build.gradle.kts
```kotlin
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("com.android.library") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}
```

### settings.gradle.kts
```kotlin
rootProject.name = "maintenance-lib"
include(":core")
include(":maintenance-view")
include(":maintenance-compose")
include(":sample-kotlin")
include(":sample-java")
include(":sample-compose")
```

## Minimum Requirements

- **Minimum SDK**: 21 (Android 5.0 Lollipop)
- **Target SDK**: 34 (Android 14)
- **Compile SDK**: 34
- **Java**: 11
- **Kotlin**: 1.9.22
- **Gradle**: 8.2.2
- **Compose**: 1.5.8 (for compose module only)

## Size Estimates

- **core**: ~15 KB
- **maintenance-view**: ~50 KB
- **maintenance-compose**: ~40 KB

Total library size: ~105 KB (before ProGuard optimization)

## ProGuard Configuration

Each module includes:
- `proguard-rules.pro`: Module-specific rules
- `consumer-rules.pro`: Rules for library consumers

## Testing

Run all tests:
```bash
./gradlew test
```

Run specific module tests:
```bash
./gradlew :core:test
./gradlew :maintenance-view:test
./gradlew :maintenance-compose:test
```

## Building

Build all modules:
```bash
./gradlew build
```

Build specific module:
```bash
./gradlew :core:build
./gradlew :maintenance-view:build
./gradlew :maintenance-compose:build
```

## Publishing

Publish to local Maven:
```bash
./gradlew publishToMavenLocal
```

Publish to JitPack:
- Create and push a git tag
- JitPack will automatically build

See [JITPACK_PUBLISH.md](JITPACK_PUBLISH.md) for details.

## Documentation Files

- **README.md**: Main documentation with usage examples
- **CHANGELOG.md**: Version history and changes
- **LICENSE**: MIT License
- **CONTRIBUTING.md**: Contribution guidelines
- **JITPACK_PUBLISH.md**: Publishing guide
- **GITHUB_SETUP.md**: GitHub setup instructions
- **PROJECT_STRUCTURE.md**: This file

## Resources

- GitHub: https://github.com/dogusipeksac/maintenance-lib
- JitPack: https://jitpack.io/#dogusipeksac/maintenance-lib
- Issues: https://github.com/dogusipeksac/maintenance-lib/issues
