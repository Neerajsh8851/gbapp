package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model.EmotiModeldsfdsf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreferencesad {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public SharedPreferencesad() {
        super();
    }

    public void saveFavorites(Context context, List<EmotiModeldsfdsf> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.apply();
    }

    public void addFavorite(Context context, EmotiModeldsfdsf emotiModeldsfdsf) {
        List<EmotiModeldsfdsf> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<>();
        favorites.add(emotiModeldsfdsf);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, EmotiModeldsfdsf emotiModeldsfdsf) {
        ArrayList<EmotiModeldsfdsf> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(emotiModeldsfdsf);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<EmotiModeldsfdsf> getFavorites(Context context) {
        SharedPreferences settings;
        List<EmotiModeldsfdsf> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            EmotiModeldsfdsf[] favoriteItems = gson.fromJson(jsonFavorites,
                    EmotiModeldsfdsf[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<>(favorites);
        } else
            return null;

        return (ArrayList<EmotiModeldsfdsf>) favorites;
    }
}