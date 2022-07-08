package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class FloatingAdaptersad extends RecyclerView.Adapter<FloatingAdaptersad.ViewHolder> {
    private Context context;
    private ArrayList<String> mItems = new ArrayList<>();

    public FloatingAdaptersad(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<String> list) {
        mItems.clear();
        mItems.addAll(list);
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.list_item_style_floating, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        final String str = mItems.get(position);
        holder.textView.setText(mItems.get(position));
        holder.count.setText(String.valueOf(position + 1));
        final CopyHandlersds copyHandlersds = new CopyHandlersds(context);
        holder.imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandlersds.copy(str);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,count;
        View imgCopy;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_result);
            count = itemView.findViewById(R.id.numflo);
            imgCopy = itemView.findViewById(R.id.btn_copy);

        }
    }
}