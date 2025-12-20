# ToneBoard for Android

An Android port of the iOS ToneBoard keyboard - a Chinese keyboard that helps language learners remember Mandarin tones by requiring tone input while typing.

## About

ToneBoard is a Chinese keyboard that requires you to enter the correct tones while typing simplified Chinese with Pinyin. This Android version is a port of the original iOS app by Kevin Bell.

### Key Features

- **Tone-Required Input**: Type pinyin with tone numbers (1-5) to practice tone recall
- **Interactive Tutorial**: Learn to use the keyboard with in-app lessons
- **Simplified Chinese Support**: Focus on learning simplified characters
- **No Word Prediction**: Avoid shortcuts to ensure genuine learning

## How It Works

Unlike standard Pinyin keyboards that let you type without tones, ToneBoard requires you to include tone numbers with your pinyin input:

- Type `ni3hao3` to get 你好 (hello)
- Type `xie4xie5` to get 谢谢 (thank you)
- Type `zhong1guo2` to get 中国 (China)

The tone numbers (1-5) correspond to:
1. First tone (flat) - ā
2. Second tone (rising) - á
3. Third tone (falling-rising) - ǎ
4. Fourth tone (falling) - à
5. Neutral tone - a

## Installation

### Prerequisites

- Android Studio Arctic Fox (2020.3.1) or later
- Android SDK API level 24 or higher
- Kotlin 1.9.0 or later

### Building from Source

1. Clone this repository:
```bash
git clone https://github.com/yourusername/ToneBoard-Android.git
cd ToneBoard-Android
```

2. Open the project in Android Studio

3. Sync Gradle files

4. Build and run the app:
   - Connect an Android device or start an emulator
   - Click "Run" (or press Shift+F10)

### Installing the APK

After building, you can install the generated APK on any Android device running API level 24 (Android 7.0) or higher.

## Setup Instructions

After installing the app:

1. **Open ToneBoard** - Launch the app from your app drawer
2. **Complete Tutorial** - Try the interactive tutorial to learn how the keyboard works
3. **Enable Keyboard** - Go to Settings → Languages & Input → Virtual Keyboard → Manage Keyboards → Enable ToneBoard
4. **Select Keyboard** - When typing, switch to ToneBoard using the keyboard selector
5. **Start Learning!** - Use ToneBoard in any app to practice your Chinese tones

## Project Structure

```
ToneBoardAndroid/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/toneboard/android/
│   │   │   │   ├── ToneBoardIME.kt          # Main keyboard service
│   │   │   │   ├── PinyinDictionary.kt      # Dictionary handler
│   │   │   │   ├── MainActivity.kt          # Main app screen
│   │   │   │   ├── TutorialActivity.kt      # Tutorial screen
│   │   │   │   └── SettingsActivity.kt      # Settings screen
│   │   │   ├── res/
│   │   │   │   ├── layout/                  # UI layouts
│   │   │   │   ├── xml/                     # Keyboard & preferences
│   │   │   │   ├── values/                  # Strings, colors, themes
│   │   │   │   └── drawable/                # Visual resources
│   │   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## Key Components

### ToneBoardIME
The main Input Method Editor (IME) service that handles:
- Key input processing
- Candidate display
- Text composition
- Integration with Android's input system

### PinyinDictionary
Manages the conversion from pinyin with tones to Chinese characters. Currently includes a basic dictionary - can be extended with:
- CC-CEDICT data
- Word frequency information
- Additional character mappings

### MainActivity
The app's main interface showing:
- Keyboard enablement status
- Quick access to tutorial
- Settings and configuration
- Setup instructions

### TutorialActivity
Interactive in-app tutorial that lets users practice the keyboard before using it system-wide.

## Extending the Dictionary

The current implementation includes a basic dictionary for demonstration. To add more words:

1. Open `PinyinDictionary.kt`
2. Add entries to the `dictionary` map in the format:
   ```kotlin
   "pinyin1with2tones3" to listOf("汉字", "more", "options")
   ```

For a production app, you should:
- Load CC-CEDICT data from a file or database
- Implement word frequency ranking
- Add support for multi-character words
- Include proper noun support

## Customization

### Keyboard Layout
Modify `app/src/main/res/xml/qwerty.xml` to change:
- Key positions
- Key sizes
- Special keys
- Layout arrangement

### Theme Colors
Edit `app/src/main/res/values/colors.xml` to customize:
- Primary colors
- Accent colors
- Key colors
- Text colors

### Settings
Add new preferences in:
- `app/src/main/res/xml/preferences.xml` (UI)
- `SettingsActivity.kt` (logic)

## Differences from iOS Version

This Android port maintains the core functionality of the iOS version while adapting to Android's platform requirements:

- **Architecture**: Uses Android's IME framework instead of iOS keyboard extensions
- **UI Framework**: Built with Android Views/Kotlin instead of Swift/UIKit
- **Dictionary**: Simplified dictionary for demonstration (can be extended)
- **Settings**: Android-native preferences instead of iOS Settings integration
- **Tutorial**: Inline tutorial within the app (Android doesn't restrict keyboard testing)

## Future Enhancements

Potential improvements for future versions:

- [ ] Full CC-CEDICT integration
- [ ] Word frequency ordering
- [ ] Traditional Chinese support
- [ ] Custom dictionary additions
- [ ] Cloud sync for learned words
- [ ] Statistics and progress tracking
- [ ] Theme customization
- [ ] Alternative keyboard layouts
- [ ] Audio tone examples
- [ ] Handwriting input support

## Data Sources

When implementing a full dictionary, consider these sources:

- **CC-CEDICT**: Free Chinese-English dictionary (https://cc-cedict.org/)
- **Google Ngram**: Word frequency data
- **Unihan Database**: Character information

## License

This project is licensed under the MIT License - see the LICENSE file for details.

Based on the original iOS ToneBoard by Kevin Bell (https://github.com/bellkev/ToneBoard)

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

If you encounter issues or have questions:
1. Check the tutorial within the app
2. Review the setup instructions
3. Open an issue on GitHub

## Acknowledgments

- Original iOS ToneBoard by Kevin Bell
- CC-CEDICT for dictionary data
- Android IME framework documentation
- Material Design components

## Contact

For questions or feedback about this Android port, please open an issue on GitHub.

---

**Note**: This is a port of the iOS ToneBoard app. The original concept and implementation is by Kevin Bell.
