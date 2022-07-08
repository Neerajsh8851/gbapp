package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model.EmotiModeldsfdsf;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.BottomSheetdsfs;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.SharedPreferencesad;

import java.util.List;

public class EmoticonsAdaptersdfs extends RecyclerView.Adapter<EmoticonsAdaptersdfs.MyViewHolder> {

    //Prefrance
    private static PrefManagersd prf;

    private Activity context;
    List<EmotiModeldsfdsf> emotiModeldsfdsfs;
    SharedPreferencesad sharedPreferencesad;

    public EmoticonsAdaptersdfs(List<EmotiModeldsfdsf> emotiModeldsfdsfs, Activity context) {
        this.emotiModeldsfdsfs = emotiModeldsfdsfs;
        this.context = context;
        sharedPreferencesad = new SharedPreferencesad();
        prf = new PrefManagersd(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.whatsitem_emoticons, parent, false);
        return new MyViewHolder(row);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        EmotiModeldsfdsf emotiModeldsfdsf = emotiModeldsfdsfs.get(position);
        holder.emoticons.setText(emotiModeldsfdsf.getName());
        holder.emoticons.setSelected(true);
        // Count List Number
        holder.number.setText(String.valueOf(position + 1));
        final CopyHandlersds copyHandlersds = new CopyHandlersds(context);
        final String text = holder.emoticons.getText().toString();
        // Copy Button
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                copyHandlersds.copy(text);
            }});
        // Share Button
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandlersds.Share(text);
            }
        });
        // Preview text Bottomsheet
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetdsfs bottomSheetdsfs = new BottomSheetdsfs();
                bottomSheetdsfs.styleBottom(context,text);
            }
        });

        if (checkFavoriteItem(emotiModeldsfdsf)) {
            holder.favBtn.setImageResource(R.drawable.favourite_icon_fillwhats);
            holder.favBtn.setTag("red");
        } else {
            holder.favBtn.setImageResource(R.drawable.favourite_icon_whats);
            holder.favBtn.setTag("grey");
        }
        // favourite button
        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = holder.favBtn.getTag().toString();
                if (tag.equalsIgnoreCase("grey")) {
                    sharedPreferencesad.addFavorite(context, emotiModeldsfdsfs.get(position));

                    holder.favBtn.setTag("red");
                    holder.favBtn.setImageResource(R.drawable.favourite_icon_fillwhats);
                } else {
                    sharedPreferencesad.removeFavorite(context, emotiModeldsfdsfs.get(position));
                    holder.favBtn.setTag("grey");
                    holder.favBtn.setImageResource(R.drawable.favourite_icon_whats);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return emotiModeldsfdsfs.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView emoticons,number;
        ImageButton copy,share,favBtn;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.numtxt);
            favBtn = itemView.findViewById(R.id.favBtn);
            copy = itemView.findViewById(R.id.copy2);
            share = itemView.findViewById(R.id.btn_share2);
            emoticons = itemView.findViewById(R.id.emoti);
            cardView = itemView.findViewById(R.id.root1);

        }
    }
    // Favourite list item check
    public boolean checkFavoriteItem(EmotiModeldsfdsf checkEmoti) {
        boolean check = false;
        List<EmotiModeldsfdsf> favorites = sharedPreferencesad.getFavorites(context);
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

    // Item Add in Favourite list
    public void add(EmotiModeldsfdsf emotiModeldsfdsf) {
        emotiModeldsfdsfs.add(emotiModeldsfdsf);
        notifyDataSetChanged();
    }


}
