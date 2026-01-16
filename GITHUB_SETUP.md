# GitHub Setup and Publishing Guide

Complete guide to set up your repository and publish to GitHub.

## Step 1: Initialize Git Repository

If not already initialized:

```bash
cd /Users/dogus.ipeksac/AndroidStudioProjects/MaintenanceModeGeneric
git init
```

## Step 2: Create .gitignore

The `.gitignore` file is already created. Verify it contains:

```
*.iml
.gradle
/local.properties
/.idea/
.DS_Store
/build
/captures
.externalNativeBuild
.cxx
local.properties
```

## Step 3: Initial Commit

```bash
# Add all files
git add .

# Create initial commit
git commit -m "Initial commit: Maintenance Mode Library v1.0.0

- Add core module with MaintenanceConfig, MaintenanceState, OnRetryListener
- Add maintenance-view module for Android View (Kotlin + Java support)
- Add maintenance-compose module for Jetpack Compose
- Add sample-kotlin app with comprehensive examples
- Add sample-java app with Builder pattern examples
- Add sample-compose app with modern UI examples
- Add complete documentation (README, CHANGELOG, LICENSE)
- Add ProGuard rules for all modules
- Add JitPack publishing configuration"
```

## Step 4: Create GitHub Repository

### Option A: Using GitHub CLI (gh)

```bash
# Install GitHub CLI if not installed
# macOS: brew install gh

# Login to GitHub
gh auth login

# Create repository
gh repo create maintenance-lib --public --source=. --remote=origin

# Push code
git push -u origin main
```

### Option B: Using GitHub Web Interface

1. Go to [https://github.com/new](https://github.com/new)
2. Repository name: `maintenance-lib`
3. Description: "Modern Android library for displaying maintenance mode with support for Kotlin, Java, and Jetpack Compose"
4. Choose **Public**
5. **Don't** initialize with README, .gitignore, or license (we already have them)
6. Click "Create repository"

Then connect your local repository:

```bash
git remote add origin https://github.com/dogusates/maintenance-lib.git
git branch -M main
git push -u origin main
```

## Step 5: Create First Release

### 5.1 Create and push tag

```bash
# Create annotated tag
git tag -a v1.0.0 -m "Release version 1.0.0

Features:
- Modular architecture with core, view, and compose modules
- Full Kotlin, Java, and Jetpack Compose support
- Material Design 3 with dark mode
- Comprehensive documentation and samples
- ProGuard ready
- JitPack publishing support"

# Push tag to GitHub
git push origin v1.0.0
```

### 5.2 Create GitHub Release

#### Using GitHub CLI:

```bash
gh release create v1.0.0 \
  --title "v1.0.0 - Initial Release" \
  --notes "## 🎉 Initial Release

### Features
- ✅ Core module with shared models and interfaces
- ✅ Maintenance-View module for Android View (Kotlin + Java)
- ✅ Maintenance-Compose module for Jetpack Compose
- ✅ Sample applications for all platforms
- 🎨 Material Design 3 support
- 🌙 Dark mode support
- 📱 Responsive design
- 🔧 Fully customizable
- 🌍 Multi-language support
- 🔒 ProGuard ready

### Installation

\`\`\`kotlin
// Add JitPack repository
maven { url = uri(\"https://jitpack.io\") }

// Add dependency
implementation(\"com.github.dogusates.maintenance-lib:maintenance-view:1.0.0\")
implementation(\"com.github.dogusates.maintenance-lib:maintenance-compose:1.0.0\")
\`\`\`

See [README.md](https://github.com/dogusates/maintenance-lib/blob/main/README.md) for complete documentation."
```

#### Using GitHub Web Interface:

1. Go to your repository on GitHub
2. Click "Releases" → "Create a new release"
3. Choose tag: `v1.0.0`
4. Release title: `v1.0.0 - Initial Release`
5. Add release notes (see above)
6. Click "Publish release"

## Step 6: Configure Repository Settings

### 6.1 Add Topics

Go to repository → Settings → Topics, add:
- `android`
- `kotlin`
- `java`
- `jetpack-compose`
- `android-library`
- `maintenance-mode`
- `material-design`
- `jitpack`

### 6.2 Add Description

Repository description:
```
Modern Android library for displaying maintenance mode with support for Kotlin, Java, and Jetpack Compose
```

Website: `https://jitpack.io/#dogusates/maintenance-lib`

### 6.3 Enable Issues

Settings → Features → Check "Issues"

### 6.4 Add README Preview

GitHub will automatically show README.md on the repository page.

## Step 7: Verify JitPack Build

1. Go to [https://jitpack.io](https://jitpack.io)
2. Enter: `https://github.com/dogusates/maintenance-lib`
3. Click "Look up"
4. Find `v1.0.0` and click "Get it"
5. Wait for build to complete (green badge = success)

## Step 8: Update README Badge

Once JitPack build succeeds, the badge will work:

```markdown
[![](https://jitpack.io/v/dogusates/maintenance-lib.svg)](https://jitpack.io/#dogusates/maintenance-lib)
```

## Complete Command Sequence

Here's the complete sequence of commands:

```bash
# Navigate to project
cd /Users/dogus.ipeksac/AndroidStudioProjects/MaintenanceModeGeneric

# Initialize git (if not already done)
git init

# Add all files
git add .

# Initial commit
git commit -m "Initial commit: Maintenance Mode Library v1.0.0"

# Create GitHub repository (using gh CLI)
gh repo create maintenance-lib --public --source=. --remote=origin

# Push to GitHub
git push -u origin main

# Create and push tag
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0

# Create GitHub release
gh release create v1.0.0 \
  --title "v1.0.0 - Initial Release" \
  --notes-file CHANGELOG.md
```

## Alternative: Manual GitHub Setup

If you prefer not to use GitHub CLI:

```bash
# After creating repository on GitHub web interface
git remote add origin https://github.com/dogusates/maintenance-lib.git
git branch -M main
git push -u origin main
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

Then create the release manually on GitHub web interface.

## Troubleshooting

### Authentication Issues

```bash
# For HTTPS
git config --global credential.helper osxkeychain

# For SSH (recommended)
ssh-keygen -t ed25519 -C "your_email@example.com"
# Add SSH key to GitHub: Settings → SSH and GPG keys
```

### Push Rejected

```bash
# If remote has changes
git pull origin main --rebase
git push origin main
```

### Tag Already Exists

```bash
# Delete local tag
git tag -d v1.0.0

# Delete remote tag
git push origin :refs/tags/v1.0.0

# Create new tag
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

## Next Steps

1. ✅ Repository created and code pushed
2. ✅ First release published
3. ✅ JitPack build verified
4. 📢 Share your library:
   - Post on Reddit (r/androiddev)
   - Share on Twitter/X
   - Post on LinkedIn
   - Add to Android Arsenal
5. 📝 Create documentation website (optional)
6. 🎯 Add to awesome-android lists

## Useful Git Commands

```bash
# Check status
git status

# View commit history
git log --oneline

# View tags
git tag -l

# View remote
git remote -v

# Pull latest changes
git pull origin main

# Create new branch
git checkout -b feature/new-feature

# Merge branch
git checkout main
git merge feature/new-feature

# Delete branch
git branch -d feature/new-feature
```

## Resources

- [GitHub Docs](https://docs.github.com)
- [Git Documentation](https://git-scm.com/doc)
- [JitPack Documentation](https://jitpack.io/docs/)
- [Semantic Versioning](https://semver.org/)

---

Good luck with your library! 🚀
