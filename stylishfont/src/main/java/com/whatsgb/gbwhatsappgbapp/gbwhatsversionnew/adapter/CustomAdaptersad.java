package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;

public class CustomAdaptersad extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] logo;
    private int[] icon;


    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public CustomAdaptersad(Context context, String[] strArr, int[] icon) {
        this.context = context;
        this.logo = strArr;
        this.inflater = LayoutInflater.from(context);
        this.icon = icon;
    }

    public int getCount() {
        return logo.length;
    }

    @SuppressLint({"ViewHolder", "UseCompatLoadingForDrawables"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("InflateParams") View inflate = inflater.inflate(R.layout.whatsgridview_ty,  null);
        ImageView imageView = inflate.findViewById(R.id.image);
        imageView.setImageDrawable(context.getDrawable(icon[i]));
        ((TextView) inflate.findViewById(R.id.name)).setText(logo[i]);
        return inflate;
    }
}
