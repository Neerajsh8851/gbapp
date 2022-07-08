package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model.EmotiModeldsfdsf;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.SharedPreferencesad;

import java.util.List;

public class FavAdapterdfd extends RecyclerView.Adapter<FavAdapterdfd.ViewHolder> {

    private Context mContext;
    List<EmotiModeldsfdsf> favItemList;
    SharedPreferencesad sharedPreferencesad;

    public FavAdapterdfd(Context context, List<EmotiModeldsfdsf> favItemList) {
        this.mContext = context;
        this.favItemList = favItemList;
        sharedPreferencesad = new SharedPreferencesad();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.whatsfav_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final CopyHandlersds copyHandlersds = new CopyHandlersds(mContext);
        final EmotiModeldsfdsf emotiModeldsfdsf = favItemList.get(position);
        holder.fav_text_view.setText(emotiModeldsfdsf.getName());
        holder.fav_text_view.setSelected(true);
        // Count List Number
        holder.number.setText(String.valueOf(position + 1));

        if (checkFavoriteItem(emotiModeldsfdsf)) {
            holder.fav_btn.setImageResource(R.drawable.favourite_icon_fillwhats);
            holder.fav_btn.setTag("red");
        } else {
            holder.fav_btn.setImageResource(R.drawable.favourite_icon_whats);
            holder.fav_btn.setTag("grey");
        }
        // favourite button
        holder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = holder.fav_btn.getTag().toString();
                if (tag.equalsIgnoreCase("grey")) {
                    sharedPreferencesad.addFavorite(mContext, favItemList.get(position));
                    holder.fav_btn.setTag("red");
                    holder.fav_btn.setImageResource(R.drawable.favourite_icon_fillwhats);
                } else {
                    sharedPreferencesad.removeFavorite(mContext, favItemList.get(position));
                    holder.fav_btn.setTag("grey");
                    holder.fav_btn.setImageResource(R.drawable.favourite_icon_whats);

                    remove(favItemList.get(position));
                } }
        });
        // Copy Button
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandlersds.copy(emotiModeldsfdsf.getName());
            }
        });
        // Share Button
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandlersds.Share(emotiModeldsfdsf.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return favItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView fav_text_view,number;
        ImageButton fav_btn,copy,share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fav_text_view = itemView.findViewById(R.id.emoti);
            fav_btn = itemView.findViewById(R.id.favBtn);
            number = itemView.findViewById(R.id.numtxt);
            copy = itemView.findViewById(R.id.copy);
            share = itemView.findViewById(R.id.share);

        }
    }
    // Favourite list item check
    public boolean checkFavoriteItem(EmotiModeldsfdsf checkEmoti) {
        boolean check = false;
        List<EmotiModeldsfdsf> favorites = sharedPreferencesad.getFavorites(mContext);
        if (favorites != null) {
            for (EmotiModeldsfdsf emotiModeldsfdsf : favorites) {
                if (emotiModeldsfdsf.equals(checkEmoti)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
    // Item Remove in Favourite list
    public void remove(EmotiModeldsfdsf emotiModeldsfdsf) {
        favItemList.remove(emotiModeldsfdsf);
        notifyDataSetChanged();
    }
}
