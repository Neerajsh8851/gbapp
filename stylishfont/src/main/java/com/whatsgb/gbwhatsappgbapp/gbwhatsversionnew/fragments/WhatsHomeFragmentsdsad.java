package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.ViewPagerAdaptersad;


public class WhatsHomeFragmentsdsad extends Fragment {
	
	public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;

    public WhatsHomeFragmentsdsad() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.whatsfragment_home, container, false);
		// tab
		/**
		 * Inflate tab_layout and setup Views.
		 */
		viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
		setupViewPager(viewPager);

		/**
		 * Now , this is a workaround , The setupWithViewPager dose't works
		 * without the runnable . Maybe a Support Library Bug .
		 */

		tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
		tabLayout.post(new Runnable() {
			@Override
			public void run() {
				tabLayout.setupWithViewPager(viewPager);
			}
		});

		// Inflate the layout for this fragment
		return rootView;
	}

    private void setupViewPager(ViewPager viewPager) {
		// TODO Auto-generated method stub
    	ViewPagerAdaptersad adapter = new ViewPagerAdaptersad(getChildFragmentManager());
    	adapter.addFragment(new WhatsOneFragmentsad(), "Fonts");
      //  adapter.addFragment(new NewTwoFragment(), "Magic Service");
//        adapter.addFragment(new ThreeFragment(), "SPONSORED");
        viewPager.setAdapter(adapter);
		
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
