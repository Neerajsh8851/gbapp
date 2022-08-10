package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.nibodev.androidutil.AndroidUtility;
import com.nibodev.mobileads.MobileAd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.WhatsEmoticonGridActivitysds;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.WhatsMainActivityasdf;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.WhatsShowActivityasdsadsa;


public class WhatsHomeFragmentsaff extends Fragment implements View.OnClickListener {
    //Prefrance
    private static PrefManagersd prf;

    Activity context;
    CardView item0, item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11;
    String KEY = "Home";
    int index;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        prf = new PrefManagersd(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.whatsfragment_homenewapp, container, false);
        item0 = view.findViewById(R.id.item0);
        item1 = view.findViewById(R.id.item1);
        item2 = view.findViewById(R.id.item2);
        item3 = view.findViewById(R.id.item3);
        item4 = view.findViewById(R.id.item4);
        item5 = view.findViewById(R.id.item5);
        item6 = view.findViewById(R.id.item6);
        item7 = view.findViewById(R.id.item7);
        item8 = view.findViewById(R.id.item8);
        item9 = view.findViewById(R.id.item9);
        item10 = view.findViewById(R.id.item10);
        item11 = view.findViewById(R.id.item11);
        item0.setOnClickListener(this);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
        item6.setOnClickListener(this);
        item7.setOnClickListener(this);
        item8.setOnClickListener(this);
        item9.setOnClickListener(this);
        item10.setOnClickListener(this);
        item11.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Class<?> dest = null;
        if (v == item0){
            dest =  WhatsMainActivityasdf.class;
            index = 0;
        }
        if (v == item1) {
            dest = WhatsShowActivityasdsadsa.class;
            index = 1;
        }
        if (v == item2) {
            dest = WhatsShowActivityasdsadsa.class;
            index = 2;
        }
        if (v == item3) {
            dest = WhatsEmoticonGridActivitysds.class;
            index = 3;
        }
        if (v == item4) {
            dest = WhatsShowActivityasdsadsa.class;
            index = 4;
        }
        if (v == item5) {
            dest = WhatsShowActivityasdsadsa.class;
            index = 5;
        }
        if (v == item6) {
            dest = WhatsShowActivityasdsadsa.class;
            index = 6;
        }
        if (v == item7) {
            dest = WhatsShowActivityasdsadsa.class;
            index = 7;
        }
        if (v == item8) {
            dest = WhatsShowActivityasdsadsa.class;
            index = 8;
        }
        if (v == item9) {
            dest = WhatsShowActivityasdsadsa.class;
            index = 9;
        }
        if (v == item10) {
            dest = WhatsShowActivityasdsadsa.class;
            index = 10;
        }
        if (v == item11) {
            dest = WhatsShowActivityasdsadsa.class;
            index = 11;
        }

        Class<?> finalDest = dest;
        MobileAd.loadInterAd(requireActivity(), ()-> {
            Intent intent   = new Intent(requireActivity(), finalDest);
            intent.putExtra(KEY, index);
            startActivity(intent);
        });
    }
}