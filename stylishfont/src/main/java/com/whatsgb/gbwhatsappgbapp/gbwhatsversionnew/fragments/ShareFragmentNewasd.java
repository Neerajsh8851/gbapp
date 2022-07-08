package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;


public class ShareFragmentNewasd extends Fragment {

	// fonts
	static Typeface custom_font;

	// new
	Context context;
			
    public ShareFragmentNewasd() {
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
        View rootView = inflater.inflate(R.layout.whatsfragment_share, container, false);

		// Create button
		Button btnCreateParty = (Button) rootView.findViewById(R.id.fragment_share_btn);
		
		// button click event
		btnCreateParty.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id="+getActivity().getPackageName());
				sendIntent.setType("text/plain");
				startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
				
			}
		});

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
