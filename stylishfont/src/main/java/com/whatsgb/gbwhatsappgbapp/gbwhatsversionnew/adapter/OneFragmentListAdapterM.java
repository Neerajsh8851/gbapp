package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.OneFragmentDataM;

import java.util.ArrayList;

public class OneFragmentListAdapterM extends BaseAdapter {
	
	Context context;
	LayoutInflater lInflater;
	ArrayList<OneFragmentDataM> objects;
	
	// fonts
		static Typeface custom_font,custom_font1;
    
	
	public OneFragmentListAdapterM(Context context, ArrayList<OneFragmentDataM> drr) {
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
			view = lInflater.inflate(R.layout.fragment_one_next, parent, false);
		}
		
		TextView fonttext = (TextView) view.findViewById(R.id.fragment_one_next_fontd);

		OneFragmentDataM p = getOrder(position);
		
		System.out.println("RRRRRRRRRRRRfonts/"+p.no+".ttf");
//		custom_font = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/"+p.no+".ttf");
//		custom_font1 = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/SystemSanFranciscoDisplayRegular.ttf");
//		fonttext.setTypeface(custom_font);
//		title.setTypeface(custom_font1);

		((TextView) view.findViewById(R.id.fragment_one_next_nod)).setText(p.no);
		((TextView) view.findViewById(R.id.fragment_one_next_fontd)).setText(p.fontname);

//		CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
//		cbBuy.setOnCheckedChangeListener(myCheckChangList);
//		cbBuy.setTag(position);
//		cbBuy.setChecked(p.box);
		return view;
	}
	
	OneFragmentDataM getOrder(int position) {
		return ((OneFragmentDataM) getItem(position));
	}

	ArrayList<OneFragmentDataM> getBox() {
		ArrayList<OneFragmentDataM> box = new ArrayList<OneFragmentDataM>();
		for (OneFragmentDataM p : objects) {
			if (p.box)
				box.add(p);
		}
		return box;
	}

}
