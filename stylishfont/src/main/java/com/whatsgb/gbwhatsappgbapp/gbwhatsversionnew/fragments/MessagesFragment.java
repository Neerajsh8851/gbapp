package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;


public class MessagesFragment extends Fragment {

	// fonts
	static Typeface custom_font;

	// new
	Context context;
			
    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();
//		custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/SystemSanFranciscoDisplayRegular.ttf");
		
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.whatsfragment_messages, container, false);

        TextView site = (TextView) rootView.findViewById(R.id.fragment_messages_title);
		TextView email = (TextView) rootView.findViewById(R.id.fragment_messages_email);
		TextView emailt = (TextView) rootView.findViewById(R.id.fragment_messages_email_text);

//		site.setTypeface(custom_font);
//		email.setTypeface(custom_font);
//		emailt.setTypeface(custom_font);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
