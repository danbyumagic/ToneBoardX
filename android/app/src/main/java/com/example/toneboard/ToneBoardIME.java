package com.example.toneboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import android.view.KeyEvent;

public class ToneBoardIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard;
    private InputConnection inputConnection;
    private String currentPinyin = "";
    private ToneDictionary dictionary; // Your dictionary class

    @Override
    public void onCreate() {
        super.onCreate();
        dictionary = new ToneDictionary(this); // Load data from assets
    }

    @Override
    public View onCreateInputView() {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInput(attribute, restarting);
        inputConnection = getCurrentInputConnection();
        currentPinyin = "";
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;

        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                // Standard backspace behavior
                ic.deleteSurroundingText(1, 0);
                currentPinyin = ""; // Reset internal state on delete for now
                break;

            case Keyboard.KEYCODE_DONE:
            case 10: // Enter key
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
                break;

            case 49: // Tone 1
            case 50: // Tone 2
            case 51: // Tone 3
            case 52: // Tone 4
                // For now, let's just output the number so you see "something" happens
                // Later you can hook this back to your dictionary logic
                char code = (char) primaryCode;
                ic.commitText(String.valueOf(code), 1);
                break;

            default:
                // Handle standard letters
                char letter = (char) primaryCode;

                // OPTION A: Direct Typing (English style) - USE THIS TO TEST FIRST
                ic.commitText(String.valueOf(letter), 1);

            /*
            // OPTION B: Buffered Typing (Pinyin style) - Use this later
            currentPinyin += code;
            ic.setComposingText(currentPinyin, 1); // This shows the underlined text
            updateCandidates();
            */
        }
    }


    private void appendTone(int tone) {
        if (!currentPinyin.isEmpty()) {
            currentPinyin += tone;
            if (dictionary.isValidPinyin(currentPinyin)) {
                String candidate = dictionary.getCandidate(currentPinyin);
                inputConnection.commitText(candidate, 1);
                currentPinyin = "";
            }
        }
    }

    private void updateCandidates() {
        // Update candidate view with suggestions (expand for full app)
    }

    private void commitCurrentPinyin() {
        if (!currentPinyin.isEmpty() && dictionary.isValidPinyin(currentPinyin)) {
            String candidate = dictionary.getCandidate(currentPinyin);
            inputConnection.commitText(candidate, 1);
            currentPinyin = "";
        }
    }

    @Override
    public void onPress(int primaryCode) {}

    @Override
    public void onRelease(int primaryCode) {}

    @Override
    public void onText(CharSequence text) {}

    @Override
    public void swipeDown() {}

    @Override
    public void swipeLeft() {}

    @Override
    public void swipeRight() {}

    @Override
    public void swipeUp() {}
}