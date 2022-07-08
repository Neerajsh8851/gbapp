package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.OneFragmentDatassd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;

import java.util.ArrayList;

public class OneFragmentListAdapter extends BaseAdapter {
	
	Context context;
	LayoutInflater lInflater;
	ArrayList<OneFragmentDatassd> objects;
	
	// fonts
		static Typeface custom_font,custom_font1;
    
	
	public OneFragmentListAdapter(Context context, ArrayList<OneFragmentDatassd> drr) {
		this.context = context;
		this.objects = drr;
		lInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public int getCount() {
		return objects.size();
	}


	@Override
	public Object getItem(int position) {
		return objects.get(position);
	}


	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.whatsfragment_one_next, parent, false);
		}
		
		TextView fonttext = (TextView) view.findViewById(R.id.fragment_one_next_font);

		OneFragmentDatassd p = getOrder(position);
		
		System.out.println("RRRRRRRRRRRRfonts/"+p.no+".ttf");
//		custom_font = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/"+p.no+".ttf");
//		custom_font1 = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/SystemSanFranciscoDisplayRegular.ttf");
//		fonttext.setTypeface(custom_font);
//		title.setTypeface(custom_font1);

		((TextView) view.findViewById(R.id.fragment_one_next_no)).setText(p.no);
		((TextView) view.findViewById(R.id.fragment_one_next_font)).setText(p.fontname);

//		CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
//		cbBuy.setOnCheckedChangeListener(myCheckChangList);
//		cbBuy.setTag(position);
//		cbBuy.setChecked(p.box);
		return view;
	}
	
	OneFragmentDatassd getOrder(int position) {
		return ((OneFragmentDatassd) getItem(position));
	}

	ArrayList<OneFragmentDatassd> getBox() {
		ArrayList<OneFragmentDatassd> box = new ArrayList<OneFragmentDatassd>();
		for (OneFragmentDatassd p : objects) {
			if (p.box)
				box.add(p);
		}
		return box;
	}

}
