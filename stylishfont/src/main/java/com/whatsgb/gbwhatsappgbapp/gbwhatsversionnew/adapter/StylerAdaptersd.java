package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.interfaces.RecyclerViewItemcc;

public class StylerAdaptersd extends RecyclerView.Adapter<StylerAdaptersd.StylersViewHolder> {
    Context context;
    String[] items;
    int type = 0;
    RecyclerViewItemcc recyclerViewItemcc;

    public StylerAdaptersd(Context context, int i, String[] list, RecyclerViewItemcc recyclerViewItemcc) {
        this.context = context;
        this.type = i;
        this.items = list;
        this.recyclerViewItemcc = recyclerViewItemcc;
    }

    @NonNull
    public StylersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StylersViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.whatsitem_sheet_ty, viewGroup, false));
    }

    public void onBindViewHolder(final StylersViewHolder stylersViewHolder, final int i) {
        stylersViewHolder.txt_content.setText(items[i]);
        stylersViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewItemcc.onItemClick(stylersViewHolder.getAdapterPosition(),items[i]);
            }
        });
    }


    public int getItemCount() {
        return items.length;
    }
    public static class StylersViewHolder extends ViewHolder {
        View itemView;
        public TextView txt_content;

        public StylersViewHolder(View view) {
            super(view);
            this.txt_content = view.findViewById(R.id.symboltxt);
            this.itemView = view;
        }
    }

}
