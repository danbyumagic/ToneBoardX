# ToneBoard Android - Complete Directory Structure

## Standard Android Studio Project Structure

```
ToneBoardAndroid/                          # Root project directory
│
├── app/                                   # Main application module
│   ├── src/                              # Source code root
│   │   └── main/                         # Main source set
│   │       ├── java/                     # Java/Kotlin code
│   │       │   └── com/                  # Package structure
│   │       │       └── toneboard/
│   │       │           └── android/
│   │       │               ├── MainActivity.kt
│   │       │               ├── TutorialActivity.kt
│   │       │               ├── SettingsActivity.kt
│   │       │               ├── ToneBoardIME.kt
│   │       │               └── PinyinDictionary.kt
│   │       │
│   │       ├── res/                      # Android resources
│   │       │   ├── drawable/             # Graphics and shapes
│   │       │   │   ├── key_background.xml
│   │       │   │   └── key_preview_background.xml
│   │       │   │
│   │       │   ├── layout/               # UI layouts
│   │       │   │   ├── activity_main.xml
│   │       │   │   ├── activity_tutorial.xml
│   │       │   │   ├── activity_settings.xml
│   │       │   │   ├── keyboard_layout.xml
│   │       │   │   └── key_preview.xml
│   │       │   │
│   │       │   ├── values/               # String and style resources
│   │       │   │   ├── strings.xml       # Text strings
│   │       │   │   ├── colors.xml        # Color definitions
│   │       │   │   └── themes.xml        # App themes
│   │       │   │
│   │       │   ├── xml/                  # XML configurations
│   │       │   │   ├── qwerty.xml        # Keyboard layout
│   │       │   │   ├── method.xml        # IME metadata
│   │       │   │   └── preferences.xml   # Settings
│   │       │   │
│   │       │   └── mipmap/               # App icons (need to add)
│   │       │       ├── ic_launcher.png   # Various densities
│   │       │       └── ...
│   │       │
│   │       └── AndroidManifest.xml       # App manifest
│   │
│   ├── build.gradle                      # Module build configuration
│   └── proguard-rules.pro               # ProGuard rules
│
├── gradle/                               # Gradle wrapper files
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
│
├── build.gradle                          # Project build configuration
├── settings.gradle                       # Project settings
├── gradle.properties                     # Gradle properties
├── gradlew                              # Gradle wrapper script (Unix)
├── gradlew.bat                          # Gradle wrapper script (Windows)
│
├── .gitignore                           # Git ignore rules
│
├── README.md                            # Main documentation
├── SETUP.md                             # Setup guide
├── QUICKSTART.md                        # Quick start guide
├── ARCHITECTURE.md                      # Technical documentation
├── PROJECT_SUMMARY.md                   # Project summary
└── LICENSE                              # MIT License

```

## What Each Directory Contains

### `/app/src/main/java/com/toneboard/android/`
**Purpose**: All Kotlin/Java source code
- Activities for UI screens
- IME service for keyboard
- Business logic classes

### `/app/src/main/res/`
**Purpose**: All Android resources

#### `/res/drawable/`
- Vector graphics
- Shape definitions
- Backgrounds
- Icons

#### `/res/layout/`
- XML layout files for screens
- Keyboard UI layouts
- Reusable components

#### `/res/values/`
- strings.xml - All text strings
- colors.xml - Color palette
- themes.xml - App styling

#### `/res/xml/`
- Keyboard layouts
- IME configuration
- App preferences

#### `/res/mipmap/` (Missing - need to add)
- App launcher icons
- Different densities (mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi)

### Root Level Files

- **build.gradle** - Project-level build configuration
- **settings.gradle** - Defines which modules to include
- **gradle.properties** - Gradle build properties
- **gradlew** / **gradlew.bat** - Gradle wrapper scripts

## Files That Need to Be Added

For a complete Android Studio project, you also need:

### 1. Gradle Wrapper Files
```
gradle/wrapper/
├── gradle-wrapper.jar
└── gradle-wrapper.properties
```

### 2. Gradle Scripts
```
gradlew          (Linux/Mac executable)
gradlew.bat      (Windows batch file)
```

### 3. App Icons
```
app/src/main/res/
├── mipmap-mdpi/ic_launcher.png
├── mipmap-hdpi/ic_launcher.png
├── mipmap-xhdpi/ic_launcher.png
├── mipmap-xxhdpi/ic_launcher.png
├── mipmap-xxxhdpi/ic_launcher.png
├── mipmap-mdpi/ic_launcher_round.png
└── ... (round icons for each density)
```

### 4. Optional Test Directories
```
app/src/
├── androidTest/java/          # Instrumented tests
│   └── com/toneboard/android/
│       └── ExampleInstrumentedTest.kt
└── test/java/                 # Unit tests
    └── com/toneboard/android/
        └── ExampleUnitTest.kt
```

## How to Generate Missing Files

### Generate Gradle Wrapper
In the project root directory, run:
```bash
gradle wrapper --gradle-version 8.2
```

This creates:
- `gradle/wrapper/gradle-wrapper.jar`
- `gradle/wrapper/gradle-wrapper.properties`
- `gradlew`
- `gradlew.bat`

### Generate App Icons
1. Use Android Studio:
   - Right-click `res` folder
   - New → Image Asset
   - Configure launcher icons
   - Generate all densities

2. Or use online tools:
   - https://romannurik.github.io/AndroidAssetStudio/
   - Upload icon image
   - Download all densities

## Minimal Working Structure

At minimum, you need these files for the project to build:

```
ToneBoardAndroid/
├── app/
│   ├── src/main/
│   │   ├── java/com/toneboard/android/*.kt    ✓ (Have)
│   │   ├── res/...                            ✓ (Have)
│   │   └── AndroidManifest.xml                ✓ (Have)
│   └── build.gradle                           ✓ (Have)
├── build.gradle                               ✓ (Have)
├── settings.gradle                            ✓ (Have)
└── gradle.properties                          ✓ (Have)
```

**Missing for Android Studio**:
- Gradle wrapper files (can be generated)
- App icons (can use defaults)

## Import into Android Studio

When you open this project in Android Studio:

1. **File → Open**
2. Select the `ToneBoardAndroid` folder
3. Android Studio will:
   - Detect it's a Gradle project
   - Offer to create gradle wrapper if missing
   - Sync dependencies
   - Index files
   - Generate build files

4. If icons are missing:
   - Studio will use default Android icon
   - You can add custom icons later

## Current Status

✅ **Complete**:
- All source code files
- All resource files
- Build configuration
- Documentation

⚠️ **Missing** (but not critical):
- Gradle wrapper files
- Custom app icons
- Test files

🔧 **Android Studio will**:
- Auto-generate missing gradle wrapper
- Use default icons until you add custom ones
- Create test structure if needed

## Quick Start

Just open the `ToneBoardAndroid` folder in Android Studio and it will handle the rest!
