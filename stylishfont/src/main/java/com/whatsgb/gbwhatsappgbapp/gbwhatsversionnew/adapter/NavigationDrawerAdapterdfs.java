package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model.NavDrawerItemasd;

import java.util.Collections;
import java.util.List;


public class NavigationDrawerAdapterdfs extends RecyclerView.Adapter<NavigationDrawerAdapterdfs.MyViewHolder> {
    List<NavDrawerItemasd> data = Collections.emptyList();
    private LayoutInflater inflater;
    public NavigationDrawerAdapterdfs(Context context, List<NavDrawerItemasd> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItemasd current = data.get(position);
        holder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
