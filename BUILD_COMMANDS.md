# Build Commands Reference

All commands you need to build, test, and publish the library.

## 🔨 Build Commands

### Build All Modules

```bash
./gradlew build
```

### Build Specific Module

```bash
./gradlew :core:build
./gradlew :maintenance-view:build
./gradlew :maintenance-compose:build
```

### Clean Build

```bash
./gradlew clean build
```

### Build Release

```bash
./gradlew assembleRelease
```

## 🧪 Test Commands

### Run All Tests

```bash
./gradlew test
```

### Run Specific Module Tests

```bash
./gradlew :core:test
./gradlew :maintenance-view:test
./gradlew :maintenance-compose:test
```

### Run Tests with Coverage

```bash
./gradlew testDebugUnitTest jacocoTestReport
```

## 📦 Publishing Commands

### Publish to Local Maven

```bash
./gradlew publishToMavenLocal
```

### Check Publication

```bash
./gradlew :core:publishToMavenLocal
./gradlew :maintenance-view:publishToMavenLocal
./gradlew :maintenance-compose:publishToMavenLocal
```

### Verify Local Installation

```bash
ls ~/.m2/repository/com/github/dogusates/
```

## 🔍 Verification Commands

### Check Dependencies

```bash
./gradlew :maintenance-view:dependencies
./gradlew :maintenance-compose:dependencies
```

### Check for Updates

```bash
./gradlew dependencyUpdates
```

### Lint Check

```bash
./gradlew lint
```

### Detekt (Kotlin Static Analysis)

```bash
./gradlew detekt
```

## 📱 Sample App Commands

### Build Sample Apps

```bash
./gradlew :sample-kotlin:assembleDebug
./gradlew :sample-java:assembleDebug
./gradlew :sample-compose:assembleDebug
```

### Install Sample Apps

```bash
./gradlew :sample-kotlin:installDebug
./gradlew :sample-java:installDebug
./gradlew :sample-compose:installDebug
```

### Run Sample Apps

```bash
# Make sure device/emulator is connected
adb devices

# Install and run
./gradlew :sample-kotlin:installDebug
adb shell am start -n com.dogus.maintenance.sample.kotlin/.MainActivity
```

## 🚀 Release Commands

### Create Release Build

```bash
# Build all release variants
./gradlew assembleRelease

# Build specific module
./gradlew :maintenance-view:assembleRelease
./gradlew :maintenance-compose:assembleRelease
```

### Generate AAR Files

```bash
./gradlew :core:assembleRelease
./gradlew :maintenance-view:assembleRelease
./gradlew :maintenance-compose:assembleRelease

# Find AAR files
find . -name "*.aar"
```

## 📊 Analysis Commands

### Generate Dependency Tree

```bash
./gradlew :maintenance-view:dependencies --configuration releaseRuntimeClasspath
```

### Check APK Size

```bash
./gradlew :sample-kotlin:assembleRelease
ls -lh sample-kotlin/build/outputs/apk/release/
```

### Analyze APK

```bash
# After building
./gradlew :sample-kotlin:assembleRelease
# Open in Android Studio: Build > Analyze APK
```

## 🔒 ProGuard Commands

### Test ProGuard Rules

```bash
./gradlew :maintenance-view:assembleRelease
./gradlew :maintenance-compose:assembleRelease
```

### Generate ProGuard Mapping

```bash
./gradlew assembleRelease
# Check: build/outputs/mapping/release/mapping.txt
```

## 🐛 Debug Commands

### Debug Build

```bash
./gradlew assembleDebug --stacktrace
./gradlew assembleDebug --info
./gradlew assembleDebug --debug
```

### Clean Gradle Cache

```bash
./gradlew clean
rm -rf ~/.gradle/caches/
```

### Refresh Dependencies

```bash
./gradlew build --refresh-dependencies
```

## 📝 Documentation Commands

### Generate KDoc

```bash
./gradlew dokkaHtml
# Output: build/dokka/html/
```

### Generate Javadoc

```bash
./gradlew javadoc
# Output: build/docs/javadoc/
```

## 🔄 Git Commands for Release

### Create Tag

```bash
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

### List Tags

```bash
git tag -l
```

### Delete Tag

```bash
# Local
git tag -d v1.0.0

# Remote
git push origin :refs/tags/v1.0.0
```

## 🌐 JitPack Commands

### Trigger JitPack Build

```bash
# After pushing tag
curl https://jitpack.io/api/builds/com.github.dogusates/maintenance-lib/v1.0.0
```

### Check JitPack Status

```bash
curl https://jitpack.io/api/builds/com.github.dogusates/maintenance-lib/latest
```

## 🔧 Gradle Wrapper Commands

### Update Gradle Wrapper

```bash
./gradlew wrapper --gradle-version=8.2.2
```

### Verify Gradle Wrapper

```bash
./gradlew --version
```

## 📱 ADB Commands

### List Devices

```bash
adb devices
```

### Install APK

```bash
adb install -r sample-kotlin/build/outputs/apk/debug/sample-kotlin-debug.apk
```

### Uninstall App

```bash
adb uninstall com.dogus.maintenance.sample.kotlin
adb uninstall com.dogus.maintenance.sample.java
adb uninstall com.dogus.maintenance.sample.compose
```

### View Logs

```bash
adb logcat | grep "MaintenanceMode"
```

### Clear App Data

```bash
adb shell pm clear com.dogus.maintenance.sample.kotlin
```

## 🎯 Complete Release Workflow

```bash
# 1. Update version numbers in build.gradle.kts files
# 2. Update CHANGELOG.md

# 3. Clean and build
./gradlew clean build

# 4. Run tests
./gradlew test

# 5. Build release
./gradlew assembleRelease

# 6. Commit changes
git add .
git commit -m "Release version 1.0.0"
git push origin main

# 7. Create and push tag
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0

# 8. Create GitHub release
gh release create v1.0.0 --title "v1.0.0" --notes-file CHANGELOG.md

# 9. Verify JitPack build
# Visit: https://jitpack.io/#dogusates/maintenance-lib
```

## 🆘 Troubleshooting Commands

### Fix Gradle Daemon Issues

```bash
./gradlew --stop
./gradlew clean build
```

### Fix Build Cache Issues

```bash
rm -rf .gradle/
rm -rf build/
./gradlew clean build --no-build-cache
```

### Fix Dependency Resolution

```bash
./gradlew build --refresh-dependencies
```

### Check Java Version

```bash
java -version
./gradlew -version
```

## 📊 Performance Commands

### Build with Profile

```bash
./gradlew build --profile
# Report: build/reports/profile/
```

### Build Scan

```bash
./gradlew build --scan
```

### Parallel Build

```bash
./gradlew build --parallel --max-workers=4
```

## 🔍 Useful Gradle Properties

Add to `gradle.properties`:

```properties
# Enable parallel builds
org.gradle.parallel=true

# Configure JVM
org.gradle.jvmargs=-Xmx2048m -XX:MaxMetaspaceSize=512m

# Enable build cache
org.gradle.caching=true

# Enable configuration cache
org.gradle.configuration-cache=true
```

---

For more information, see [PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md)
