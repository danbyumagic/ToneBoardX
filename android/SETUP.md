# ToneBoard Android - Setup and Build Guide

This guide will help you build and run the ToneBoard Android app.

## Prerequisites

Before you begin, ensure you have the following installed:

1. **Android Studio** (Arctic Fox 2020.3.1 or later)
   - Download from: https://developer.android.com/studio

2. **Java Development Kit (JDK)** 8 or higher
   - Usually bundled with Android Studio
   - Or download from: https://www.oracle.com/java/technologies/downloads/

3. **Android SDK** with the following components:
   - Android SDK Platform 34 (or latest)
   - Android SDK Build-Tools 34.0.0 (or latest)
   - Android SDK Platform-Tools
   - Android Emulator (if testing on emulator)

## Project Setup

### Step 1: Clone or Extract the Project

If you received this as a ZIP file:
```bash
unzip ToneBoardAndroid.zip
cd ToneBoardAndroid
```

If cloning from Git:
```bash
git clone <repository-url>
cd ToneBoardAndroid
```

### Step 2: Open in Android Studio

1. Launch Android Studio
2. Click "Open" (or File → Open)
3. Navigate to the ToneBoardAndroid directory
4. Click "OK"

### Step 3: Sync Gradle

1. Android Studio will automatically prompt to sync Gradle
2. If not, click "File → Sync Project with Gradle Files"
3. Wait for the sync to complete (this may take a few minutes on first run)

### Step 4: Resolve Any Dependencies

If you encounter dependency issues:

1. Open `build.gradle` (Project level)
2. Check that repositories include:
   ```gradle
   repositories {
       google()
       mavenCentral()
   }
   ```

3. If needed, update Gradle:
   - File → Project Structure → Project
   - Update Gradle Version to latest

## Building the App

### Debug Build (for Testing)

1. Connect an Android device via USB (with USB debugging enabled)
   OR start an Android emulator:
   - Tools → Device Manager → Create Device

2. Click the "Run" button (green triangle) or press Shift+F10

3. Select your device when prompted

4. The app will build and install automatically

### Release Build (for Distribution)

To create a signed APK for distribution:

1. Build → Generate Signed Bundle / APK
2. Select "APK" and click "Next"
3. Create or select a keystore
4. Fill in the key information
5. Select "release" build variant
6. Click "Finish"

The APK will be generated in: `app/build/outputs/apk/release/`

## First Run and Testing

### On Device/Emulator

After the app installs:

1. **Launch ToneBoard** from the app drawer

2. **Complete the Tutorial** (optional but recommended)
   - Tap "Tutorial" card
   - Follow the interactive lessons

3. **Enable the Keyboard**
   - Tap "Enable Keyboard" card
   - This opens Android Settings
   - Enable "ToneBoard" in the keyboard list
   - Grant any requested permissions

4. **Select the Keyboard**
   - Tap "Select Keyboard" card
   - OR tap the keyboard icon in the navigation bar when typing
   - Select "ToneBoard - Chinese Keyboard"

5. **Test in Any App**
   - Open any app with text input (Messages, Notes, etc.)
   - Tap a text field
   - The ToneBoard keyboard should appear
   - Try typing: `ni3hao3` → 你好

## Troubleshooting

### Gradle Sync Fails

**Problem**: "Failed to resolve" errors

**Solution**:
1. Check your internet connection
2. File → Invalidate Caches / Restart
3. Update Gradle: File → Project Structure → Project → Gradle Version

### Keyboard Not Appearing

**Problem**: ToneBoard doesn't show when typing

**Solutions**:
1. Verify keyboard is enabled in Settings → Languages & Input
2. Try switching keyboards using the keyboard picker
3. Restart the app
4. Restart the device

### Build Errors

**Problem**: "Compilation failed" errors

**Solutions**:
1. Build → Clean Project
2. Build → Rebuild Project
3. Check that all dependencies are synced
4. Update Android Gradle Plugin if needed

### App Crashes on Launch

**Problem**: App crashes immediately

**Solutions**:
1. Check LogCat for error messages (View → Tool Windows → Logcat)
2. Verify minimum SDK version (API 24) is supported by your device
3. Try uninstalling and reinstalling
4. Clear app data: Settings → Apps → ToneBoard → Storage → Clear Data

## Development Tips

### Viewing Logs

To debug keyboard behavior:

1. Open Logcat: View → Tool Windows → Logcat
2. Filter by "ToneBoard" or package name
3. Add logging statements:
   ```kotlin
   import android.util.Log
   Log.d("ToneBoard", "Debug message")
   ```

### Hot Reload

While developing:
- Use "Apply Changes" (Ctrl+F10 / Cmd+F10) for quick iterations
- For IME changes, you may need to disable and re-enable the keyboard

### Testing Keyboard

You can test the keyboard directly in the tutorial without enabling it system-wide.

### Modifying Dictionary

To add more words:
1. Edit `PinyinDictionary.kt`
2. Add entries to the dictionary map
3. Rebuild and test

## Advanced Configuration

### Changing Package Name

If you want to publish your own version:

1. Update package name in:
   - `build.gradle` (applicationId)
   - `AndroidManifest.xml` (package attribute)
   - Refactor package structure in Android Studio

2. Update references in:
   - IME service declarations
   - Activity references

### Customizing Keyboard Layout

To modify the keyboard:

1. Edit `res/xml/qwerty.xml`
2. Adjust key codes, labels, and sizes
3. Modify `ToneBoardIME.kt` to handle new keys

### Adding More Languages

To support traditional Chinese or other languages:

1. Create new keyboard layouts in `res/xml/`
2. Add dictionary data for the language
3. Create language selection in settings
4. Update IME to switch between layouts

## Testing Checklist

Before release, verify:

- [ ] App installs successfully
- [ ] Keyboard can be enabled in settings
- [ ] Keyboard appears when typing
- [ ] Keys respond to taps
- [ ] Candidates appear for valid pinyin
- [ ] Chinese characters insert correctly
- [ ] Delete key works
- [ ] Space selects first candidate
- [ ] Tutorial functions correctly
- [ ] Settings are accessible
- [ ] App doesn't crash

## Performance Optimization

For better performance:

1. **Optimize Dictionary Loading**
   - Load dictionary asynchronously
   - Cache frequently used entries

2. **Reduce Memory Usage**
   - Use efficient data structures
   - Clear unused candidates

3. **Improve Response Time**
   - Minimize computation in key handlers
   - Use background threads for heavy operations

## Next Steps

After successfully building:

1. Test thoroughly on different devices
2. Add more dictionary entries
3. Implement word frequency ranking
4. Consider adding analytics (with user consent)
5. Prepare for Play Store release

## Resources

- [Android IME Documentation](https://developer.android.com/develop/ui/views/touch-and-input/creating-input-method)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Material Design Guidelines](https://material.io/design)
- [CC-CEDICT Dictionary](https://cc-cedict.org/)

## Support

For issues specific to this build:
1. Check LogCat for errors
2. Review this guide
3. Search Android Studio documentation
4. Open an issue on GitHub

Happy coding! 🚀
