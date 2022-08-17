package com.nibodev.mobileads;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConfigDownloader {
  private String TAG = ConfigDownloader.class.getSimpleName();
  private static ConfigDownloader instance;
  private final String link = "place url to json config";

  private final SharedPreferences preferences;

  private ConfigDownloader(Context context) {
    preferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
  }

  public static void createInstance(Context context) {
    instance = new ConfigDownloader(context);
  }

  public static ConfigDownloader getInstance() {
    return instance;
  }

  public void downloadConfig() {
    new Thread(() -> {
      InputStream in = null;
      try {
        URL url = new URL(link);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        in = urlConnection.getInputStream();
        JsonReader jsonReader = new JsonReader(new InputStreamReader(in));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
          String name = jsonReader.nextName();
          String value = jsonReader.nextString();
          preferences.edit().putString(name, value).apply();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }finally {
        if (in != null) {
          try {
            in.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }

  public String getValue(String key) {
    return preferences.getString(key, null);
  }
}
