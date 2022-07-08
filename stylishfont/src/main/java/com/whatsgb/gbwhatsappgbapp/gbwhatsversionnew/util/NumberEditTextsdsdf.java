package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util;

import android.text.Editable;
import android.text.TextWatcher;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsNumberStyleFragmentyfgdf;


public class NumberEditTextsdsdf implements TextWatcher {
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.length() == 0) {
            WhatsNumberStyleFragmentyfgdf.name_number = " ";
            WhatsNumberStyleFragmentyfgdf.numberAdapterdsf.setName(WhatsNumberStyleFragmentyfgdf.name_number, WhatsNumberStyleFragmentyfgdf.type);
            WhatsNumberStyleFragmentyfgdf.numberAdapterdsf.notifyDataSetChanged();
        } else if (charSequence.length() >= 1) {
            WhatsNumberStyleFragmentyfgdf.name_number = charSequence.toString();
            WhatsNumberStyleFragmentyfgdf.numberAdapterdsf.setName(WhatsNumberStyleFragmentyfgdf.name_number, WhatsNumberStyleFragmentyfgdf.type);
        }
    }

    public void afterTextChanged(Editable editable) {
        WhatsNumberStyleFragmentyfgdf.name_number = "0123456789";
        WhatsNumberStyleFragmentyfgdf.numberAdapterdsf.notifyDataSetChanged();
    }
}
