package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.stylish.FloatingStylishOpenShortCutActivitysad;


public class WhatsBubbleFragmenttgdsf extends Fragment {

    //Prefrance
    public static PrefManagersd prf;

    Context context;

    public WhatsBubbleFragmenttgdsf() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bubble, container, false);

        // Floating Stylish Text Dialog Popup
        AppCompatButton appCompatButton = view.findViewById(R.id.floatingbutton);
        appCompatButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), FloatingStylishOpenShortCutActivitysad.class);
            startActivityForResult(intent, 101);
        });
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.prf = new PrefManagersd(context);
    }
}