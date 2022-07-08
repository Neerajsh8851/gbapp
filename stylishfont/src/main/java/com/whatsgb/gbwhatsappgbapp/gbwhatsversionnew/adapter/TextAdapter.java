package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model.listModelsad;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.MyViewHolder> {

    //Prefrance
    public static PrefManagersd prf;

    private List<listModelsad> list;
    private Activity context;

    //ads

    public TextAdapter(List<listModelsad> arrayList, Activity context) {
        this.prf = new PrefManagersd(context);
        this.list = arrayList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(context).inflate(R.layout.whatsitem_text_ty, parent, false);
            return new MyViewHolder(row);

        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            final listModelsad listmodel = list.get(position);
            holder.arts.setText(listmodel.get_name());
            // Count List Number
            holder.num.setText(String.valueOf(position + 1));
            final CopyHandlersds copyHandlersds = new CopyHandlersds(context);
            final String text = holder.arts.getText().toString();
            // Copy Button
            holder.copy.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
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
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        static class MyViewHolder extends RecyclerView.ViewHolder {
            TextView arts,num;
            ImageButton copy,share;
            private MyViewHolder(View itemView) {
                super(itemView);
                num = itemView.findViewById(R.id.numk);
                copy = itemView.findViewById(R.id.btn_copy6);
                share = itemView.findViewById(R.id.btn_share6);
                arts = itemView.findViewById(R.id.text_designs);
            }
        }
    }


