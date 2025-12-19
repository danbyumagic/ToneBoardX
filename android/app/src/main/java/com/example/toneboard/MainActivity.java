package com.example.toneboard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enableButton = findViewById(R.id.enable_button);
        enableButton.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
        });
    }
}