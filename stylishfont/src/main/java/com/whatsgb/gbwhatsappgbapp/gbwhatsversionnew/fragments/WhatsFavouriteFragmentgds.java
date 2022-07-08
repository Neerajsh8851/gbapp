package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.FavAdapterdfd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model.EmotiModeldsfdsf;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.SharedPreferencesad;

import java.util.List;

public class WhatsFavouriteFragmentgds extends Fragment {
    RecyclerView recyclerView;
    Context context;
    TextView textView;
    SharedPreferencesad sharedPreferencesad;
    FavAdapterdfd favAdapterdfd;
    List<EmotiModeldsfdsf> favorites;
    MaterialToolbar materialToolbar;
    public WhatsFavouriteFragmentgds() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatsactivity_emoticons, container, false);
        sharedPreferencesad = new SharedPreferencesad();
        textView = view.findViewById(R.id.nofavtext);
        materialToolbar = view.findViewById(R.id.toolbar);
        favorites = sharedPreferencesad.getFavorites(context);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Check Favourite Item and Show in Favourite List
        if (favorites != null) {
            favAdapterdfd = new FavAdapterdfd(context,favorites);
            recyclerView.setAdapter(favAdapterdfd); }
        if (favorites == null){
            textView.setVisibility(View.VISIBLE);
        }else if (favorites.size() == 0){
            textView.setVisibility(View.VISIBLE);

        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }



}