package com.example.toneboard;

import android.content.Context;
import android.content.res.AssetManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ToneDictionary {
    private Map<String, List<String>> dict;

    public ToneDictionary(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("dict.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, List<String>>>(){}.getType();
            dict = gson.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidPinyin(String pinyin) {
        return dict.containsKey(pinyin);
    }

    public String getCandidate(String pinyin) {
        List<String> candidates = dict.get(pinyin);
        return candidates != null && !candidates.isEmpty() ? candidates.get(0) : "";
    }
}