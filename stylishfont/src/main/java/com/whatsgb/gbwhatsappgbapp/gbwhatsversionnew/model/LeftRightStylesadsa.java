package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model;

import androidx.annotation.NonNull;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.interfaces.Style;


public class LeftRightStylesadsa implements Style {
    @NonNull
    private String left, right;

    public LeftRightStylesadsa(@NonNull String left, @NonNull String right) {
        this.left = left;
        this.right = right;
    }

    @NonNull
    @Override
    public String generate(@NonNull String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result.append(" ");
            } else {
                result.append(left).append(text.charAt(i)).append(right);
            }
        }
        return result.toString();
    }


    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
