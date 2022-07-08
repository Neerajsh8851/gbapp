package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model;

import androidx.annotation.NonNull;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.interfaces.Style;


public class RightEffectsadsa implements Style {

    private String character;

    public RightEffectsadsa(String text) {
        this.character = text;
    }

    @NonNull
    @Override
    public String generate(@NonNull String input) {
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ' ') {
                    result.append(" ");
                } else {
                    result.append(input.charAt(i)).append(character);
                }
            }
            return result.toString();
        } catch (OutOfMemoryError e) {
            return "";
        }
    }

    @Override
    public int hashCode() {
        return character.hashCode();
    }
}
