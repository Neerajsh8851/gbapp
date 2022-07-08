package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.Artssdsd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.TextAdapter;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model.listModelsad;

import java.util.ArrayList;

public class WhatsTextDesignFragmentghsdfgs extends Fragment {
    private Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.whatsfragment_text_design, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.textdesignre);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        TextAdapter textAdapter = new TextAdapter(getlist(),context);
        recyclerView.setAdapter(textAdapter);
        return view;
    }
    //Text Arts List
    private ArrayList<listModelsad> getlist() {
        ArrayList<listModelsad> arrayList = new ArrayList<>();
        ArrayList<String> arrayList2 = new Artssdsd().get();
        for (int i = 0; i < arrayList2.size(); i++) {
            arrayList.add(new listModelsad(arrayList2.get(i)));
        }
        return arrayList;}
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
    }
}
