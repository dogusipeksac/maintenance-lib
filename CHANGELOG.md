# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2024-01-16

### Added
- 🎉 Initial release of Maintenance Mode Library
- ✅ Core module with shared models and interfaces
  - `MaintenanceConfig` data class with Parcelable support
  - `MaintenanceState` sealed class for state management
  - `OnRetryListener` SAM interface for Java compatibility
  - `MaintenanceConstants` for shared constants
- ✅ Maintenance-View module for Android View (Kotlin + Java)
  - `MaintenanceActivity` - Full-screen maintenance activity
  - `MaintenanceDialog` - Modal dialog for maintenance
  - `MaintenanceFragment` - Fragment for flexible integration
  - `MaintenanceView` - Custom view with XML attributes
  - Kotlin DSL support with `showMaintenance` extension
  - Builder pattern for Java-friendly API
- ✅ Maintenance-Compose module for Jetpack Compose
  - `MaintenanceScreen` composable for full-screen display
  - `MaintenanceDialog` composable for modal dialogs
  - `MaintenanceComposeActivity` wrapper for traditional navigation
  - `rememberMaintenanceState` for state management
  - Compose preview support
- ✅ Sample applications
  - Kotlin sample with all View-based examples
  - Java sample with Builder pattern examples
  - Compose sample with modern declarative UI examples
- 🎨 Material Design 3 support
- 🌙 Dark mode support
- 📱 Responsive design for all screen sizes
- 🔧 Fully customizable colors, icons, and messages
- 🌍 Multi-language support
- 🔒 ProGuard rules included
- 📚 Comprehensive documentation
- 🧪 Preview support for Compose

### Features
- Modular architecture - use only what you need
- Type-safe Kotlin DSL
- Java-friendly Builder pattern
- Jetpack Compose ready
- SAM interface for lambda support in Java
- Parcelable configuration for easy passing between components
- ViewBinding support
- Custom XML attributes for easy styling
- Smooth animations
- Material 3 theming

### Technical Details
- Minimum SDK: 21 (Android 5.0 Lollipop)
- Target SDK: 34 (Android 14)
- Kotlin: 1.9.22
- Compose Compiler: 1.5.8
- Compose BOM: 2024.01.00
- Material 3: 1.11.0
- Java: 11

### Documentation
- Complete README with usage examples
- API documentation with KDoc and Javadoc
- Sample apps for all use cases
- ProGuard configuration
- Migration guide
- Contributing guidelines

## [Unreleased]

### Planned Features
- 🔄 Remote configuration support
- 📊 Analytics integration
- 🎨 More customization options
- 🌐 More localization support
- 📱 Tablet-optimized layouts
- ⚡ Performance optimizations
- 🧪 More unit tests
- 📸 UI tests
- 🎬 Animation customization
- 🔔 Notification support

---

[1.0.0]: https://github.com/dogusipeksac/maintenance-lib/releases/tag/v1.0.0
