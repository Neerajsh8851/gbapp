package com.nibodev.gd.gbversion;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.JsonReader;
import android.util.JsonToken;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IPLocation
{
    private final String TAG = getClass().getSimpleName();
    private final String PREF_NAME = "IP-ADD";
    private final int PREF_MODE = Context.MODE_PRIVATE;
    private SharedPreferences pref;
    private String ip_api_url = "http://ip-api.com/json/";

    public IPLocation(Context context)
    {
        pref = context.getSharedPreferences(PREF_NAME, PREF_MODE);
    }

    private void request_location()
    {
        new Thread(() -> {
            try
            {
                URL url = new URL(ip_api_url);
                HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();

                // using java json reader to parse the json response
                JsonReader reader = new JsonReader(new InputStreamReader(url_connection.getInputStream()));

                reader.beginObject();
                while (reader.hasNext())
                {
                    String name = reader.nextName();
                    JsonToken jToken = reader.peek();
                    if (jToken.name().equals(JsonToken.STRING.name())) {
                        pref.edit().putString(name, reader.nextString()).apply();
                    } else if (jToken.name().equals( JsonToken.BOOLEAN.name())) {
                        pref.edit().putBoolean(name, reader.nextBoolean()).apply();
                    } else if (jToken.name().equals(JsonToken.NUMBER.name())) {
                        pref.edit().putLong(name, reader.nextLong()).apply();
                    }
                }
                synchronized (this) {
                    notifyAll();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }).start();
    }

    public  String get_country_code()
    {
        String country = pref.getString("countryCode", null);
        if (country == null)
        {
            synchronized (this)
            {
                request_location();
                try {
                    wait(10000);
                    country = pref.getString("countryCode", "");
                }catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return country;
    }
}