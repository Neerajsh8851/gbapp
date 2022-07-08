package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.NumberAdapterdsf;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.NumStylesds;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.NumberEditTextsdsdf;


public class WhatsNumberStyleFragmentyfgdf extends Fragment {

    public static String name_number = "0123456789";
    Context context;
    RecyclerView recycler_view;
    EditText inputText;
    ImageView close;
    public static NumberAdapterdsf numberAdapterdsf = null;
    public static int type = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatsfragment_number_style, container, false);
        inputText = view.findViewById(R.id.inputtext_stylish);
        recycler_view = view.findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(context));
        NumberAdapterdsf numberAdapterdsf1 = new NumberAdapterdsf(context, NumStylesds.Number, NumStylesds.numberStyle, name_number, type);
        numberAdapterdsf = numberAdapterdsf1;
        recycler_view.setAdapter(numberAdapterdsf);
        inputText.addTextChangedListener(new NumberEditTextsdsdf());
        close = view.findViewById(R.id.closebtn);
        //Text Delete from EditText
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = inputText.getText().length();
                if (length > 0) {
                    inputText.getText().delete(length - 1, length);
                }
            }
        });
        //All Text Delete from EditText
        close.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                inputText.getText().clear();
                return false;
            }
        });
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}