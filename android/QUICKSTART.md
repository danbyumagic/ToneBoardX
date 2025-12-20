# ToneBoard Android - Quick Start Guide

Welcome to ToneBoard for Android! This guide will get you up and running quickly.

## What is ToneBoard?

ToneBoard is a Chinese keyboard that helps you learn Mandarin tones by requiring you to type tone numbers along with pinyin. Instead of typing `nihao` and selecting from many options, you type `ni3hao3` to get 你好.

## For Users

### Installation (from APK)

1. Download the ToneBoard APK file
2. Enable "Install from Unknown Sources" if needed
3. Open the APK and tap "Install"
4. Launch ToneBoard from your app drawer

### Setup (5 minutes)

#### Step 1: Try the Tutorial
- Open ToneBoard app
- Tap "Tutorial" card
- Complete the interactive lessons
- This lets you practice without setup!

#### Step 2: Enable the Keyboard
- Tap "Enable Keyboard" card
- Toggle ToneBoard ON
- Allow any requested permissions

#### Step 3: Select ToneBoard
- Open any app (Messages, Notes, etc.)
- Tap a text field to show keyboard
- Tap the keyboard icon (🌐 or ⌨️)
- Select "ToneBoard - Chinese Keyboard"

### Using ToneBoard

#### Typing Basics
- Type letters as normal (a-z)
- Add tone numbers (1-5) after each syllable
- Example: `ni3` → 你, `hao3` → 好
- Tap space to select the first candidate
- Tap a candidate to insert it directly

#### Tone Numbers
- 1 = First tone (flat): mā
- 2 = Second tone (rising): má
- 3 = Third tone (dipping): mǎ
- 4 = Fourth tone (falling): mà
- 5 = Neutral tone: ma

#### Example Phrases
Try typing these:
- `ni3hao3` → 你好 (hello)
- `xie4xie5` → 谢谢 (thank you)
- `zai4jian4` → 再见 (goodbye)
- `wo3ai4ni3` → 我爱你 (I love you)
- `zhong1guo2` → 中国 (China)

### Tips

- **Can't remember the tone?** The dictionary in the app is limited, so some words might not appear. This helps you focus on common words!
- **Wrong character?** Double-check your tone numbers - tones matter!
- **Need help?** Go back to the tutorial in the app

## For Developers

### Quick Build

```bash
# Clone or extract the project
cd ToneBoardAndroid

# Open in Android Studio
# OR build from command line:
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

### Project Structure

```
app/src/main/
├── java/com/toneboard/android/
│   ├── ToneBoardIME.kt          # Main keyboard
│   ├── PinyinDictionary.kt      # Word lookup
│   ├── MainActivity.kt          # Home screen
│   ├── TutorialActivity.kt      # Tutorial
│   └── SettingsActivity.kt      # Settings
├── res/
│   ├── layout/                  # UI layouts
│   ├── xml/                     # Keyboard & prefs
│   ├── values/                  # Strings & colors
│   └── drawable/                # Icons & shapes
└── AndroidManifest.xml
```

### Key Files to Modify

**Add more words**: Edit `PinyinDictionary.kt`
```kotlin
"pinyin1" to listOf("汉字", "options")
```

**Change keyboard layout**: Edit `res/xml/qwerty.xml`

**Update colors**: Edit `res/values/colors.xml`

**Modify strings**: Edit `res/values/strings.xml`

### Building for Production

1. Create a keystore:
```bash
keytool -genkey -v -keystore toneboard.keystore -alias toneboard -keyalg RSA -keysize 2048 -validity 10000
```

2. Build signed APK:
   - Build → Generate Signed Bundle/APK
   - Select your keystore
   - Choose "release" variant

3. Test thoroughly before publishing

### Testing

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Or use Android Studio's test runner
```

## Common Issues

### Keyboard Not Showing

**Problem**: ToneBoard doesn't appear when typing

**Solutions**:
1. Check Settings → Languages & Input → Virtual Keyboard
2. Ensure ToneBoard is enabled
3. Try switching keyboards using keyboard picker
4. Restart the app
5. Reboot device if needed

### No Candidates Appearing

**Problem**: Typing but no Chinese characters show up

**Solutions**:
1. Make sure you're including tone numbers (1-5)
2. The demo dictionary is limited - try common words
3. Check the tutorial for working examples

### Tutorial Not Working

**Problem**: Can't complete tutorial lessons

**Solutions**:
1. Make sure you type EXACTLY as shown (including numbers)
2. Use lowercase letters
3. Don't add spaces within words
4. Try restarting the tutorial

### Build Errors

**Problem**: App won't compile

**Solutions**:
1. File → Sync Project with Gradle Files
2. Build → Clean Project
3. Check Android Studio version (Arctic Fox+)
4. Ensure JDK 8+ is installed

## Frequently Asked Questions

### Q: Why do I need to type tone numbers?

A: This helps you learn and remember tones! Traditional keyboards let you type without tones, which means you can use Chinese without really learning the tones. ToneBoard forces you to practice.

### Q: Can I use ToneBoard for traditional Chinese?

A: Not yet - the current version only supports simplified Chinese. Traditional support could be added in the future.

### Q: Why don't all words appear?

A: The demo version has a limited dictionary. This is intentional - it focuses on common words to help learning. For a full dictionary, the app would need to integrate CC-CEDICT data.

### Q: Does ToneBoard collect my data?

A: No! ToneBoard doesn't collect any data, doesn't access the internet, and doesn't track your usage. It's completely private.

### Q: Can I add my own words?

A: In the current version, no. Future versions may include custom dictionary support.

### Q: Is this better than Pleco or other apps?

A: ToneBoard is different - it's specifically designed for learning tones through typing practice. It's complementary to other learning tools.

## Next Steps

### For Users
- Practice daily with the tutorial
- Use it for Duolingo or other apps
- Focus on common words first
- Review tone patterns

### For Developers
- Integrate CC-CEDICT for full dictionary
- Add word frequency ranking
- Implement traditional Chinese
- Create custom themes
- Add statistics tracking

## Resources

- Full documentation: See `README.md`
- Architecture details: See `ARCHITECTURE.md`
- Setup guide: See `SETUP.md`
- Original iOS version: https://github.com/bellkev/ToneBoard

## Support

- Tutorial in the app
- Documentation files in the project
- GitHub issues for bugs

## About

This is an Android port of the iOS ToneBoard keyboard by Kevin Bell. The goal is to help Chinese language learners remember tones through typing practice.

Happy learning! 加油！ (jiā yóu - keep it up!)
