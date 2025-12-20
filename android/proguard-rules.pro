# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in ${sdk.dir}/tools/proguard/proguard-android.txt

# Keep IME service
-keep class com.toneboard.android.ToneBoardIME { *; }

# Keep activities
-keep class com.toneboard.android.MainActivity { *; }
-keep class com.toneboard.android.TutorialActivity { *; }
-keep class com.toneboard.android.SettingsActivity { *; }

# Keep dictionary
-keep class com.toneboard.android.PinyinDictionary { *; }

# Kotlin
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}

# Android
-keepclassmembers class * extends android.inputmethodservice.InputMethodService {
    public *;
}

# Keep custom views
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Keep preference classes
-keep class androidx.preference.** { *; }
