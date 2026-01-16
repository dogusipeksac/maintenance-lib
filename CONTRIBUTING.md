# Contributing to Maintenance Mode Library

First off, thank you for considering contributing to Maintenance Mode Library! 🎉

## Code of Conduct

This project and everyone participating in it is governed by our Code of Conduct. By participating, you are expected to uphold this code.

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check the existing issues as you might find out that you don't need to create one. When you are creating a bug report, please include as many details as possible:

* **Use a clear and descriptive title**
* **Describe the exact steps which reproduce the problem**
* **Provide specific examples to demonstrate the steps**
* **Describe the behavior you observed after following the steps**
* **Explain which behavior you expected to see instead and why**
* **Include screenshots and animated GIFs if possible**
* **Include your Android version and device information**

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, please include:

* **Use a clear and descriptive title**
* **Provide a step-by-step description of the suggested enhancement**
* **Provide specific examples to demonstrate the steps**
* **Describe the current behavior and explain which behavior you expected to see instead**
* **Explain why this enhancement would be useful**

### Pull Requests

* Fill in the required template
* Do not include issue numbers in the PR title
* Follow the Kotlin/Java style guides
* Include thoughtfully-worded, well-structured tests
* Document new code
* End all files with a newline

## Development Setup

1. Fork the repository
2. Clone your fork:
   ```bash
   git clone https://github.com/YOUR_USERNAME/maintenance-lib.git
   cd maintenance-lib
   ```

3. Open the project in Android Studio

4. Build the project:
   ```bash
   ./gradlew build
   ```

5. Run tests:
   ```bash
   ./gradlew test
   ```

## Project Structure

```
maintenance-lib/
├── core/                    # Core module
├── maintenance-view/        # Android View module
├── maintenance-compose/     # Jetpack Compose module
├── sample-kotlin/           # Kotlin sample
├── sample-java/             # Java sample
└── sample-compose/          # Compose sample
```

## Coding Standards

### Kotlin

* Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
* Use meaningful variable and function names
* Add KDoc comments for public APIs
* Use `@JvmStatic`, `@JvmOverloads`, and `@JvmName` for Java interoperability

### Java

* Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
* Add Javadoc comments for public APIs
* Use Builder pattern for complex constructors

### Compose

* Follow [Compose API Guidelines](https://github.com/androidx/androidx/blob/androidx-main/compose/docs/compose-api-guidelines.md)
* Add `@Preview` annotations for composables
* Use `remember` for state management

## Testing

* Write unit tests for new features
* Ensure all tests pass before submitting PR
* Add UI tests for Compose components when applicable

## Documentation

* Update README.md if you change functionality
* Update CHANGELOG.md following [Keep a Changelog](https://keepachangelog.com/)
* Add KDoc/Javadoc comments for public APIs
* Update sample apps if you add new features

## Commit Messages

* Use the present tense ("Add feature" not "Added feature")
* Use the imperative mood ("Move cursor to..." not "Moves cursor to...")
* Limit the first line to 72 characters or less
* Reference issues and pull requests liberally after the first line

Example:
```
Add dark mode support for MaintenanceView

- Implement theme detection
- Add night mode resources
- Update sample apps
- Add tests for theme switching

Fixes #123
```

## Release Process

1. Update version in all `build.gradle.kts` files
2. Update CHANGELOG.md
3. Create a new release on GitHub
4. Tag the release with version number (e.g., `v1.0.0`)

## Questions?

Feel free to open an issue with your question or reach out to the maintainers.

## License

By contributing, you agree that your contributions will be licensed under the MIT License.
