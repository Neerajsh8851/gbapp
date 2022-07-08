package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.DecorationAdapterasd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model.Fontsadsd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.BottomSheetdsfs;

import java.util.ArrayList;

public class WhatsDecorationFragmentdsgfsd extends Fragment {

    //Prefrance
    public static PrefManagersd prf;

    public WhatsDecorationFragmentdsgfsd() {
    }

    private Activity context;
    RecyclerView fontsRV;
    private ArrayList<Fontsadsd> decorationFontsadsds = new ArrayList<>();
    private EditText editText;
    ImageView close,symbol;

    //ads

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatsfragment_decoration, container, false);


          close = view.findViewById(R.id.close22);
          symbol = view.findViewById(R.id.symbolsdec);
          // Decoration Name Number Wise
        if (decorationFontsadsds.isEmpty()) {
            final Fontsadsd f1 = new Fontsadsd("Decoration 1");
            final Fontsadsd f2 = new Fontsadsd("Decoration 2");
            final Fontsadsd f3 = new Fontsadsd("Decoration 3");
            final Fontsadsd f4 = new Fontsadsd("Decoration 4");
            final Fontsadsd f5 = new Fontsadsd("Decoration 5");
            final Fontsadsd f6 = new Fontsadsd("Decoration 6");
            final Fontsadsd f7 = new Fontsadsd("Decoration 7");
            final Fontsadsd f8 = new Fontsadsd("Decoration 8");
            final Fontsadsd f9 = new Fontsadsd("Decoration 9");
            final Fontsadsd f10 = new Fontsadsd("Decoration 10");
            final Fontsadsd f11 = new Fontsadsd("Decoration 11");
            final Fontsadsd f12 = new Fontsadsd("Decoration 12");
            final Fontsadsd f13 = new Fontsadsd("Decoration 13");
            final Fontsadsd f14 = new Fontsadsd("Decoration 14");
            final Fontsadsd f15 = new Fontsadsd("Decoration 15");
            final Fontsadsd f16 = new Fontsadsd("Decoration 16");
            final Fontsadsd f17 = new Fontsadsd("Decoration 17");
            final Fontsadsd f18 = new Fontsadsd("Decoration 18");
            final Fontsadsd f19 = new Fontsadsd("Decoration 19");
            final Fontsadsd f20 = new Fontsadsd("Decoration 20");
            final Fontsadsd f21 = new Fontsadsd("Decoration 21");
            final Fontsadsd f22 = new Fontsadsd("Decoration 22");
            final Fontsadsd f23 = new Fontsadsd("Decoration 23");
            final Fontsadsd f24 = new Fontsadsd("Decoration 24");
            final Fontsadsd f25 = new Fontsadsd("Decoration 25");
            final Fontsadsd f26 = new Fontsadsd("Decoration 26");
            final Fontsadsd f27 = new Fontsadsd("Decoration 27");
            final Fontsadsd f28 = new Fontsadsd("Decoration 28");
            final Fontsadsd f29 = new Fontsadsd("Decoration 29");
            final Fontsadsd f30 = new Fontsadsd("Decoration 30");
            final Fontsadsd f31 = new Fontsadsd("Decoration 31");
            final Fontsadsd f32 = new Fontsadsd("Decoration 32");
            final Fontsadsd f33 = new Fontsadsd("Decoration 33");
            final Fontsadsd f34 = new Fontsadsd("Decoration 34");
            final Fontsadsd f35 = new Fontsadsd("Decoration 35");
            final Fontsadsd f36 = new Fontsadsd("Decoration 36");
            final Fontsadsd f37 = new Fontsadsd("Decoration 37");
            final Fontsadsd f38 = new Fontsadsd("Decoration 38");
            final Fontsadsd f39 = new Fontsadsd("Decoration 39");
            final Fontsadsd f40 = new Fontsadsd("Decoration 40");
            final Fontsadsd f41 = new Fontsadsd("Decoration 41");
            final Fontsadsd f42 = new Fontsadsd("Decoration 42");
            final Fontsadsd f43 = new Fontsadsd("Decoration 43");
            final Fontsadsd f44 = new Fontsadsd("Decoration 44");
            final Fontsadsd f45 = new Fontsadsd("Decoration 45");
            final Fontsadsd f46 = new Fontsadsd("Decoration 46");
            final Fontsadsd f47 = new Fontsadsd("Decoration 47");
            final Fontsadsd f48 = new Fontsadsd("Decoration 48");
            final Fontsadsd f49 = new Fontsadsd("Decoration 49");
            final Fontsadsd f50 = new Fontsadsd("Decoration 50");
            final Fontsadsd f51 = new Fontsadsd("Decoration 51");
            final Fontsadsd f52 = new Fontsadsd("Decoration 52");
            final Fontsadsd f53 = new Fontsadsd("Decoration 53");
            final Fontsadsd f54 = new Fontsadsd("Decoration 54");
            final Fontsadsd f55 = new Fontsadsd("Decoration 55");
            final Fontsadsd f56 = new Fontsadsd("Decoration 56");
            final Fontsadsd f57 = new Fontsadsd("Decoration 57");
            final Fontsadsd f58 = new Fontsadsd("Decoration 58");
            final Fontsadsd f59 = new Fontsadsd("Decoration 59");
            final Fontsadsd f60 = new Fontsadsd("Decoration 60");
            final Fontsadsd f61 = new Fontsadsd("Decoration 61");
            final Fontsadsd f62 = new Fontsadsd("Decoration 62");
            final Fontsadsd f63 = new Fontsadsd("Decoration 63");
            final Fontsadsd f64 = new Fontsadsd("Decoration 64");
            final Fontsadsd f65 = new Fontsadsd("Decoration 65");
            final Fontsadsd f66 = new Fontsadsd("Decoration 66");
            final Fontsadsd f67 = new Fontsadsd("Decoration 67");
            final Fontsadsd f68 = new Fontsadsd("Decoration 68");
            final Fontsadsd f69 = new Fontsadsd("Decoration 69");
            final Fontsadsd f70 = new Fontsadsd("Decoration 70");
            final Fontsadsd f71 = new Fontsadsd("Decoration 71");
            final Fontsadsd f72 = new Fontsadsd("Decoration 72");
            final Fontsadsd f73 = new Fontsadsd("Decoration 73");
            final Fontsadsd f74 = new Fontsadsd("Decoration 74");
            final Fontsadsd f75 = new Fontsadsd("Decoration 75");
            final Fontsadsd f76 = new Fontsadsd("Decoration 76");
            final Fontsadsd f77 = new Fontsadsd("Decoration 77");
            final Fontsadsd f78 = new Fontsadsd("Decoration 78");
            final Fontsadsd f79 = new Fontsadsd("Decoration 79");
            final Fontsadsd f80 = new Fontsadsd("Decoration 80");
            final Fontsadsd f81 = new Fontsadsd("Decoration 81");
            final Fontsadsd f82 = new Fontsadsd("Decoration 82");
            final Fontsadsd f83 = new Fontsadsd("Decoration 83");
            final Fontsadsd f84 = new Fontsadsd("Decoration 84");
            final Fontsadsd f85 = new Fontsadsd("Decoration 85");
            final Fontsadsd f86 = new Fontsadsd("Decoration 86");
            final Fontsadsd f87 = new Fontsadsd("Decoration 87");
            final Fontsadsd f88 = new Fontsadsd("Decoration 88");
            final Fontsadsd f89 = new Fontsadsd("Decoration 89");
            final Fontsadsd f90 = new Fontsadsd("Decoration 90");
            final Fontsadsd f91 = new Fontsadsd("Decoration 91");
            final Fontsadsd f92 = new Fontsadsd("Decoration 92");
            final Fontsadsd f93 = new Fontsadsd("Decoration 93");
            final Fontsadsd f94 = new Fontsadsd("Decoration 94");
            final Fontsadsd f95 = new Fontsadsd("Decoration 95");
            final Fontsadsd f96 = new Fontsadsd("Decoration 96");
            final Fontsadsd f97 = new Fontsadsd("Decoration 97");
            final Fontsadsd f98 = new Fontsadsd("Decoration 98");
            final Fontsadsd f99 = new Fontsadsd("Decoration 99");


            decorationFontsadsds.add(f1);
            decorationFontsadsds.add(f2);
            decorationFontsadsds.add(f3);
            decorationFontsadsds.add(f4);
            decorationFontsadsds.add(f5);
            decorationFontsadsds.add(f6);
            decorationFontsadsds.add(f7);
            decorationFontsadsds.add(f8);
            decorationFontsadsds.add(f9);
            decorationFontsadsds.add(f10);
            decorationFontsadsds.add(f11);
            decorationFontsadsds.add(f12);
            decorationFontsadsds.add(f13);
            decorationFontsadsds.add(f14);
            decorationFontsadsds.add(f15);
            decorationFontsadsds.add(f16);
            decorationFontsadsds.add(f17);
            decorationFontsadsds.add(f18);
            decorationFontsadsds.add(f19);
            decorationFontsadsds.add(f20);
            decorationFontsadsds.add(f21);
            decorationFontsadsds.add(f22);
            decorationFontsadsds.add(f23);
            decorationFontsadsds.add(f24);
            decorationFontsadsds.add(f25);
            decorationFontsadsds.add(f26);
            decorationFontsadsds.add(f27);
            decorationFontsadsds.add(f28);
            decorationFontsadsds.add(f29);
            decorationFontsadsds.add(f30);
            decorationFontsadsds.add(f31);
            decorationFontsadsds.add(f32);
            decorationFontsadsds.add(f33);
            decorationFontsadsds.add(f34);
            decorationFontsadsds.add(f35);
            decorationFontsadsds.add(f36);
            decorationFontsadsds.add(f37);
            decorationFontsadsds.add(f38);
            decorationFontsadsds.add(f39);
            decorationFontsadsds.add(f40);
            decorationFontsadsds.add(f41);
            decorationFontsadsds.add(f42);
            decorationFontsadsds.add(f43);
            decorationFontsadsds.add(f44);
            decorationFontsadsds.add(f45);
            decorationFontsadsds.add(f46);
            decorationFontsadsds.add(f47);
            decorationFontsadsds.add(f48);
            decorationFontsadsds.add(f49);
            decorationFontsadsds.add(f50);
            decorationFontsadsds.add(f51);
            decorationFontsadsds.add(f52);
            decorationFontsadsds.add(f53);
            decorationFontsadsds.add(f54);
            decorationFontsadsds.add(f55);
            decorationFontsadsds.add(f56);
            decorationFontsadsds.add(f57);
            decorationFontsadsds.add(f58);
            decorationFontsadsds.add(f59);
            decorationFontsadsds.add(f60);
            decorationFontsadsds.add(f61);
            decorationFontsadsds.add(f62);
            decorationFontsadsds.add(f63);
            decorationFontsadsds.add(f64);
            decorationFontsadsds.add(f65);
            decorationFontsadsds.add(f66);
            decorationFontsadsds.add(f67);
            decorationFontsadsds.add(f68);
            decorationFontsadsds.add(f69);
            decorationFontsadsds.add(f70);
            decorationFontsadsds.add(f71);
            decorationFontsadsds.add(f72);
            decorationFontsadsds.add(f73);
            decorationFontsadsds.add(f74);
            decorationFontsadsds.add(f75);
            decorationFontsadsds.add(f76);
            decorationFontsadsds.add(f77);
            decorationFontsadsds.add(f78);
            decorationFontsadsds.add(f79);
            decorationFontsadsds.add(f80);
            decorationFontsadsds.add(f81);
            decorationFontsadsds.add(f82);
            decorationFontsadsds.add(f83);
            decorationFontsadsds.add(f84);
            decorationFontsadsds.add(f85);
            decorationFontsadsds.add(f86);
            decorationFontsadsds.add(f87);
            decorationFontsadsds.add(f88);
            decorationFontsadsds.add(f89);
            decorationFontsadsds.add(f90);
            decorationFontsadsds.add(f91);
            decorationFontsadsds.add(f92);
            decorationFontsadsds.add(f93);
            decorationFontsadsds.add(f94);
            decorationFontsadsds.add(f95);
            decorationFontsadsds.add(f96);
            decorationFontsadsds.add(f97);
            decorationFontsadsds.add(f98);
            decorationFontsadsds.add(f99);


        }
        else{
            decorationFontsadsds.clear();
            final Fontsadsd f1 = new Fontsadsd("Decoration 1");
            final Fontsadsd f2 = new Fontsadsd("Decoration 2");
            final Fontsadsd f3 = new Fontsadsd("Decoration 3");
            final Fontsadsd f4 = new Fontsadsd("Decoration 4");
            final Fontsadsd f5 = new Fontsadsd("Decoration 5");
            final Fontsadsd f6 = new Fontsadsd("Decoration 6");
            final Fontsadsd f7 = new Fontsadsd("Decoration 7");
            final Fontsadsd f8 = new Fontsadsd("Decoration 8");
            final Fontsadsd f9 = new Fontsadsd("Decoration 9");
            final Fontsadsd f10 = new Fontsadsd("Decoration 10");
            final Fontsadsd f11 = new Fontsadsd("Decoration 11");
            final Fontsadsd f12 = new Fontsadsd("Decoration 12");
            final Fontsadsd f13 = new Fontsadsd("Decoration 13");
            final Fontsadsd f14 = new Fontsadsd("Decoration 14");
            final Fontsadsd f15 = new Fontsadsd("Decoration 15");
            final Fontsadsd f16 = new Fontsadsd("Decoration 16");
            final Fontsadsd f17 = new Fontsadsd("Decoration 17");
            final Fontsadsd f18 = new Fontsadsd("Decoration 18");
            final Fontsadsd f19 = new Fontsadsd("Decoration 19");
            final Fontsadsd f20 = new Fontsadsd("Decoration 20");
            final Fontsadsd f21 = new Fontsadsd("Decoration 21");
            final Fontsadsd f22 = new Fontsadsd("Decoration 22");
            final Fontsadsd f23 = new Fontsadsd("Decoration 23");
            final Fontsadsd f24 = new Fontsadsd("Decoration 24");
            final Fontsadsd f25 = new Fontsadsd("Decoration 25");
            final Fontsadsd f26 = new Fontsadsd("Decoration 26");
            final Fontsadsd f27 = new Fontsadsd("Decoration 27");
            final Fontsadsd f28 = new Fontsadsd("Decoration 28");
            final Fontsadsd f29 = new Fontsadsd("Decoration 29");
            final Fontsadsd f30 = new Fontsadsd("Decoration 30");
            final Fontsadsd f31 = new Fontsadsd("Decoration 31");
            final Fontsadsd f32 = new Fontsadsd("Decoration 32");
            final Fontsadsd f33 = new Fontsadsd("Decoration 33");
            final Fontsadsd f34 = new Fontsadsd("Decoration 34");
            final Fontsadsd f35 = new Fontsadsd("Decoration 35");
            final Fontsadsd f36 = new Fontsadsd("Decoration 36");
            final Fontsadsd f37 = new Fontsadsd("Decoration 37");
            final Fontsadsd f38 = new Fontsadsd("Decoration 38");
            final Fontsadsd f39 = new Fontsadsd("Decoration 39");
            final Fontsadsd f40 = new Fontsadsd("Decoration 40");
            final Fontsadsd f41 = new Fontsadsd("Decoration 41");
            final Fontsadsd f42 = new Fontsadsd("Decoration 42");
            final Fontsadsd f43 = new Fontsadsd("Decoration 43");
            final Fontsadsd f44 = new Fontsadsd("Decoration 44");
            final Fontsadsd f45 = new Fontsadsd("Decoration 45");
            final Fontsadsd f46 = new Fontsadsd("Decoration 46");
            final Fontsadsd f47 = new Fontsadsd("Decoration 47");
            final Fontsadsd f48 = new Fontsadsd("Decoration 48");
            final Fontsadsd f49 = new Fontsadsd("Decoration 49");
            final Fontsadsd f50 = new Fontsadsd("Decoration 50");
            final Fontsadsd f51 = new Fontsadsd("Decoration 51");
            final Fontsadsd f52 = new Fontsadsd("Decoration 52");
            final Fontsadsd f53 = new Fontsadsd("Decoration 53");
            final Fontsadsd f54 = new Fontsadsd("Decoration 54");
            final Fontsadsd f55 = new Fontsadsd("Decoration 55");
            final Fontsadsd f56 = new Fontsadsd("Decoration 56");
            final Fontsadsd f57 = new Fontsadsd("Decoration 57");
            final Fontsadsd f58 = new Fontsadsd("Decoration 58");
            final Fontsadsd f59 = new Fontsadsd("Decoration 59");
            final Fontsadsd f60 = new Fontsadsd("Decoration 60");
            final Fontsadsd f61 = new Fontsadsd("Decoration 61");
            final Fontsadsd f62 = new Fontsadsd("Decoration 62");
            final Fontsadsd f63 = new Fontsadsd("Decoration 63");
            final Fontsadsd f64 = new Fontsadsd("Decoration 64");
            final Fontsadsd f65 = new Fontsadsd("Decoration 65");
            final Fontsadsd f66 = new Fontsadsd("Decoration 66");
            final Fontsadsd f67 = new Fontsadsd("Decoration 67");
            final Fontsadsd f68 = new Fontsadsd("Decoration 68");
            final Fontsadsd f69 = new Fontsadsd("Decoration 69");
            final Fontsadsd f70 = new Fontsadsd("Decoration 70");
            final Fontsadsd f71 = new Fontsadsd("Decoration 71");
            final Fontsadsd f72 = new Fontsadsd("Decoration 72");
            final Fontsadsd f73 = new Fontsadsd("Decoration 73");
            final Fontsadsd f74 = new Fontsadsd("Decoration 74");
            final Fontsadsd f75 = new Fontsadsd("Decoration 75");
            final Fontsadsd f76 = new Fontsadsd("Decoration 76");
            final Fontsadsd f77 = new Fontsadsd("Decoration 77");
            final Fontsadsd f78 = new Fontsadsd("Decoration 78");
            final Fontsadsd f79 = new Fontsadsd("Decoration 79");
            final Fontsadsd f80 = new Fontsadsd("Decoration 80");
            final Fontsadsd f81 = new Fontsadsd("Decoration 81");
            final Fontsadsd f82 = new Fontsadsd("Decoration 82");
            final Fontsadsd f83 = new Fontsadsd("Decoration 83");
            final Fontsadsd f84 = new Fontsadsd("Decoration 84");
            final Fontsadsd f85 = new Fontsadsd("Decoration 85");
            final Fontsadsd f86 = new Fontsadsd("Decoration 86");
            final Fontsadsd f87 = new Fontsadsd("Decoration 87");
            final Fontsadsd f88 = new Fontsadsd("Decoration 88");
            final Fontsadsd f89 = new Fontsadsd("Decoration 89");
            final Fontsadsd f90 = new Fontsadsd("Decoration 90");
            final Fontsadsd f91 = new Fontsadsd("Decoration 91");
            final Fontsadsd f92 = new Fontsadsd("Decoration 92");
            final Fontsadsd f93 = new Fontsadsd("Decoration 93");
            final Fontsadsd f94 = new Fontsadsd("Decoration 94");
            final Fontsadsd f95 = new Fontsadsd("Decoration 95");
            final Fontsadsd f96 = new Fontsadsd("Decoration 96");
            final Fontsadsd f97 = new Fontsadsd("Decoration 97");
            final Fontsadsd f98 = new Fontsadsd("Decoration 98");
            final Fontsadsd f99 = new Fontsadsd("Decoration 99");


            decorationFontsadsds.add(f1);
            decorationFontsadsds.add(f2);
            decorationFontsadsds.add(f3);
            decorationFontsadsds.add(f4);
            decorationFontsadsds.add(f5);
            decorationFontsadsds.add(f6);
            decorationFontsadsds.add(f7);
            decorationFontsadsds.add(f8);
            decorationFontsadsds.add(f9);
            decorationFontsadsds.add(f10);
            decorationFontsadsds.add(f11);
            decorationFontsadsds.add(f12);
            decorationFontsadsds.add(f13);
            decorationFontsadsds.add(f14);
            decorationFontsadsds.add(f15);
            decorationFontsadsds.add(f16);
            decorationFontsadsds.add(f17);
            decorationFontsadsds.add(f18);
            decorationFontsadsds.add(f19);
            decorationFontsadsds.add(f20);
            decorationFontsadsds.add(f21);
            decorationFontsadsds.add(f22);
            decorationFontsadsds.add(f23);
            decorationFontsadsds.add(f24);
            decorationFontsadsds.add(f25);
            decorationFontsadsds.add(f26);
            decorationFontsadsds.add(f27);
            decorationFontsadsds.add(f28);
            decorationFontsadsds.add(f29);
            decorationFontsadsds.add(f30);
            decorationFontsadsds.add(f31);
            decorationFontsadsds.add(f32);
            decorationFontsadsds.add(f33);
            decorationFontsadsds.add(f34);
            decorationFontsadsds.add(f35);
            decorationFontsadsds.add(f36);
            decorationFontsadsds.add(f37);
            decorationFontsadsds.add(f38);
            decorationFontsadsds.add(f39);
            decorationFontsadsds.add(f40);
            decorationFontsadsds.add(f41);
            decorationFontsadsds.add(f42);
            decorationFontsadsds.add(f43);
            decorationFontsadsds.add(f44);
            decorationFontsadsds.add(f45);
            decorationFontsadsds.add(f46);
            decorationFontsadsds.add(f47);
            decorationFontsadsds.add(f48);
            decorationFontsadsds.add(f49);
            decorationFontsadsds.add(f50);
            decorationFontsadsds.add(f51);
            decorationFontsadsds.add(f52);
            decorationFontsadsds.add(f53);
            decorationFontsadsds.add(f54);
            decorationFontsadsds.add(f55);
            decorationFontsadsds.add(f56);
            decorationFontsadsds.add(f57);
            decorationFontsadsds.add(f58);
            decorationFontsadsds.add(f59);
            decorationFontsadsds.add(f60);
            decorationFontsadsds.add(f61);
            decorationFontsadsds.add(f62);
            decorationFontsadsds.add(f63);
            decorationFontsadsds.add(f64);
            decorationFontsadsds.add(f65);
            decorationFontsadsds.add(f66);
            decorationFontsadsds.add(f67);
            decorationFontsadsds.add(f68);
            decorationFontsadsds.add(f69);
            decorationFontsadsds.add(f70);
            decorationFontsadsds.add(f71);
            decorationFontsadsds.add(f72);
            decorationFontsadsds.add(f73);
            decorationFontsadsds.add(f74);
            decorationFontsadsds.add(f75);
            decorationFontsadsds.add(f76);
            decorationFontsadsds.add(f77);
            decorationFontsadsds.add(f78);
            decorationFontsadsds.add(f79);
            decorationFontsadsds.add(f80);
            decorationFontsadsds.add(f81);
            decorationFontsadsds.add(f82);
            decorationFontsadsds.add(f83);
            decorationFontsadsds.add(f84);
            decorationFontsadsds.add(f85);
            decorationFontsadsds.add(f86);
            decorationFontsadsds.add(f87);
            decorationFontsadsds.add(f88);
            decorationFontsadsds.add(f89);
            decorationFontsadsds.add(f90);
            decorationFontsadsds.add(f91);
            decorationFontsadsds.add(f92);
            decorationFontsadsds.add(f93);
            decorationFontsadsds.add(f94);
            decorationFontsadsds.add(f95);
            decorationFontsadsds.add(f96);
            decorationFontsadsds.add(f97);
            decorationFontsadsds.add(f98);
            decorationFontsadsds.add(f99);

        }

        fontsRV = view.findViewById(R.id.recycle_view_DF);
        final DecorationAdapterasd adapter = new DecorationAdapterasd(decorationFontsadsds, context);
        fontsRV.setLayoutManager(new LinearLayoutManager(context));
        fontsRV.setAdapter(adapter);
        editText = view.findViewById(R.id.edit_text_DF);
        // Preview Text Bottomsheet Dialog
        symbol.setOnClickListener(v -> BottomSheetdsfs.showDialogbox(context,editText));
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editTextStr = editText.getText().toString();

                if (editTextStr.isEmpty()) {
                    editTextStr = "Preview text";
                }

                for (int item = 0; item < decorationFontsadsds.size(); item++) {
                    decorationFontsadsds.get(item).setPreviewText(editTextStr);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        //Text Delete from edittext
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = editText.getText().length();
                if (length > 0) {
                    editText.getText().delete(length - 1, length);
                }
            }
        });
        // All Text Delete from edittext
        close.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                editText.getText().clear();
                return false;
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
        this.prf = new PrefManagersd(context);
    }


}
