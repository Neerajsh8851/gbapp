package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.BottomSheetdsfs;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;


import java.util.ArrayList;


public class FontAdapterasd extends RecyclerView.Adapter<FontAdapterasd.MyViewHolder> {

    //Prefrance
    public static PrefManagersd prf;


    private ArrayList<String> dataList = new ArrayList<>();
    private Context activity;

    public FontAdapterasd(Context activity) {
        this.activity = activity;
        this.prf = new PrefManagersd(activity);

    }
    public void setData(ArrayList<String> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
     }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(activity).inflate(R.layout.adapter_font, parent, false);

        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.description.setText(dataList.get(position));
        holder.description.setSelected(true);
        // Count List Number
        holder.number.setText(String.valueOf(position + 1));
        final CopyHandlersds copyHandlersds = new CopyHandlersds(activity);
        final String text = holder.description.getText().toString();
        // Copy Button
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                copyHandlersds.copy(text);

            }
        });
        // Share Button
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandlersds.Share(text);
            }
        });
        // Preview text Bottomsheet
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetdsfs bottomSheetdsfs = new BottomSheetdsfs();
                bottomSheetdsfs.styleBottom(activity,text);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder   {
        TextView description,number;
        ImageView copy,share;
        LinearLayout layout;


        private MyViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.descriptionTV);
            number = itemView.findViewById(R.id.txt_number);
            copy = itemView.findViewById(R.id.btn_copy);
            share = itemView.findViewById(R.id.btn_share);
            layout = itemView.findViewById(R.id.linearclick);


        }

    }

}


