package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.interfaces.RecyclerViewItemcc;

import java.util.ArrayList;

public class SheetAdapterdsaf extends RecyclerView.Adapter<SheetAdapterdsaf.MyViewHolder> {
    RecyclerViewItemcc recyclerViewItemcc;
    Context context;
    ArrayList<String> symbols;
    public SheetAdapterdsaf(Context context, ArrayList<String> symbols , RecyclerViewItemcc recyclerViewItemcc) {
        this.context = context;
        this.symbols = symbols;
        this.recyclerViewItemcc = recyclerViewItemcc;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.whatsitem_sheet_ty, parent, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.symbol.setText(symbols.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewItemcc.onItemClick(holder.getAdapterPosition(),symbols.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return symbols.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView symbol;
        private MyViewHolder(View itemView) {
            super(itemView);
            symbol = itemView.findViewById(R.id.symboltxt);

        }
    }
}
