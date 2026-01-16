# JitPack Publishing Guide

This guide explains how to publish the Maintenance Mode Library to JitPack.

## Prerequisites

1. GitHub account
2. Repository must be public
3. Git tags for versioning

## Step 1: Prepare Your Repository

### 1.1 Ensure build.gradle.kts is configured

All library modules should have the maven-publish plugin configured:

```kotlin
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.dogusates"
                artifactId = "maintenance-core" // or maintenance-view, maintenance-compose
                version = "1.0.0"
            }
        }
    }
}
```

### 1.2 Create jitpack.yml (Optional)

Create `jitpack.yml` in the root directory if you need custom build configuration:

```yaml
jdk:
  - openjdk11
before_install:
  - sdk install java 11.0.10-open
  - sdk use java 11.0.10-open
```

## Step 2: Create a Release

### 2.1 Commit all changes

```bash
git add .
git commit -m "Release version 1.0.0"
git push origin main
```

### 2.2 Create a Git tag

```bash
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

## Step 3: Publish to JitPack

### 3.1 Visit JitPack

Go to [https://jitpack.io](https://jitpack.io)

### 3.2 Enter your repository

Enter your GitHub repository URL:
```
https://github.com/dogusates/maintenance-lib
```

### 3.3 Look up the version

JitPack will automatically detect your tags. Click "Get it" next to your version.

### 3.4 Wait for build

JitPack will build your library. This may take a few minutes. You can see the build log.

### 3.5 Check build status

- ✅ Green badge: Build successful
- ❌ Red badge: Build failed (check logs)

## Step 4: Use the Library

Once published, users can add it to their projects:

### Add JitPack repository

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Add dependencies

```kotlin
dependencies {
    // For Android View
    implementation("com.github.dogusates.maintenance-lib:maintenance-view:1.0.0")
    
    // For Jetpack Compose
    implementation("com.github.dogusates.maintenance-lib:maintenance-compose:1.0.0")
    
    // For both
    implementation("com.github.dogusates.maintenance-lib:maintenance-view:1.0.0")
    implementation("com.github.dogusates.maintenance-lib:maintenance-compose:1.0.0")
}
```

## Version Management

### Semantic Versioning

Follow [Semantic Versioning](https://semver.org/):

- **MAJOR** version (1.x.x): Incompatible API changes
- **MINOR** version (x.1.x): Add functionality (backwards compatible)
- **PATCH** version (x.x.1): Bug fixes (backwards compatible)

### Creating New Versions

1. Update version in all `build.gradle.kts` files
2. Update CHANGELOG.md
3. Commit changes
4. Create new tag:
   ```bash
   git tag -a v1.1.0 -m "Release version 1.1.0"
   git push origin v1.1.0
   ```
5. JitPack will automatically build the new version

## Troubleshooting

### Build Failed

1. Check JitPack build logs
2. Ensure all dependencies are available
3. Verify Gradle configuration
4. Test local build: `./gradlew build`

### Module Not Found

Ensure the module name in the dependency matches the module name in `settings.gradle.kts`:

```kotlin
include(":core")
include(":maintenance-view")
include(":maintenance-compose")
```

### Version Not Showing

1. Ensure tag is pushed to GitHub
2. Wait a few minutes for JitPack to detect it
3. Try clicking "Look up" on JitPack

### ProGuard Issues

Ensure consumer ProGuard rules are included:

```kotlin
android {
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}
```

## Best Practices

1. **Always test locally before releasing**
   ```bash
   ./gradlew clean build
   ./gradlew publishToMavenLocal
   ```

2. **Use meaningful version numbers**
   - v1.0.0 for first stable release
   - v1.1.0 for new features
   - v1.0.1 for bug fixes

3. **Update documentation**
   - Update README.md with new features
   - Update CHANGELOG.md with changes
   - Update sample apps if needed

4. **Create release notes on GitHub**
   - Go to Releases → Draft a new release
   - Select your tag
   - Add release notes
   - Publish release

## Useful Commands

```bash
# Check current tags
git tag -l

# Delete local tag
git tag -d v1.0.0

# Delete remote tag
git push origin :refs/tags/v1.0.0

# Create and push tag
git tag -a v1.0.0 -m "Release 1.0.0"
git push origin v1.0.0

# Build locally
./gradlew clean build

# Publish to local Maven
./gradlew publishToMavenLocal
```

## JitPack Badge

Add a JitPack badge to your README.md:

```markdown
[![](https://jitpack.io/v/dogusates/maintenance-lib.svg)](https://jitpack.io/#dogusates/maintenance-lib)
```

## Support

- [JitPack Documentation](https://jitpack.io/docs/)
- [JitPack Building](https://jitpack.io/docs/BUILDING/)
- [GitHub Issues](https://github.com/dogusates/maintenance-lib/issues)

---

Happy Publishing! 🚀
