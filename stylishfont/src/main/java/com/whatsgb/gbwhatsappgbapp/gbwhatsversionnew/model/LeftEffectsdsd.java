package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model;

import androidx.annotation.NonNull;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.interfaces.Style;


public class LeftEffectsdsd implements Style {

    private String left;

    public LeftEffectsdsd(String left) {
        this.left = left;
    }

    @NonNull
    @Override
    public String generate(@NonNull String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result.append(left).append(" ");
            } else {
                result.append(left).append(text.charAt(i));
            }
        }
        result.append(left);
        return result.toString();
    }

    @Override
    public int hashCode() {
        return left.hashCode();
    }
}
