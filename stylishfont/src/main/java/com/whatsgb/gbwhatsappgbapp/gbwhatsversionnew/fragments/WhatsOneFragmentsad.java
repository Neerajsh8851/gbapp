package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.skyfishjy.library.RippleBackground;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.Databasehelperfdsf;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.Fontsad;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.OneFragmentDatassd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.OneFragmentListAdapterM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class WhatsOneFragmentsad extends Fragment implements OnClickListener{
	public Fontsad fontsad;
	public static String[] rajan;
	public String main;
	public static String output = "R";
	
	private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2,fab3;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;	

    //spinner
    private Spinner spinner,spinnerd;
    
    ArrayList<HashMap<String, String>> offersList,offersListd;
    
	private static final String TAG_NO = "no";
	private static final String TAG_FONT = "font";
	
	private static final String TAG_NOD = "no";
	private static final String TAG_FONTD = "font";
    
    OneFragmentListAdapter adapter;
    ArrayList<OneFragmentDatassd> drr;
    
    OneFragmentListAdapterM adapterd;
    ArrayList<OneFragmentDataM> rmd;
	
	//database variable
	ArrayList<String> fontlist,fontlistd;
    
    // fonts
 	static Typeface custom_font;

	//Prefrance
	private static PrefManagersd prf;

	private int success;

    //new
	private Context context;
  	
  	//Ripple
  	RippleBackground rippleBackground;

	// ads
	public static int count=1;

	//banner ads
	private LinearLayout bannerAdContainer;
  		
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = getActivity();

		prf = new PrefManagersd(context);
        
		fontsad =new Fontsad();
		
		// custom_font = Typeface.createFromAsset(context.getAssets(),
		// "fonts/SystemSanFranciscoDisplayRegular.ttf");

		// Spinner Drop down elements
		fontlist = new ArrayList<String>();
		fontlistd = new ArrayList<String>();

//		fontlist.add("Automobile");
//		fontlist.add("Business Services");
//		fontlist.add("Computers");
		
		// database handler
		Databasehelperfdsf myDbHelper = new Databasehelperfdsf(context);

		try {
			myDbHelper.createDatabase();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		try {
			myDbHelper.openDatabase();
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}

		// Spinner Drop down elements
		fontlist = myDbHelper.getAllLabels();
		fontlistd = myDbHelper.getAllLabelsd();
	    
		drr = new ArrayList<OneFragmentDatassd>();
		rmd = new ArrayList<OneFragmentDataM>();

		// Hashmap for ListView
		offersList = new ArrayList<HashMap<String, String>>();
		offersListd = new ArrayList<HashMap<String, String>>();

		// creating new HashMap
		HashMap<String, String> map = new HashMap<String, String>();

		for (int no = 0; no < fontlist.size(); no++) {

			// adding each child node to HashMap key => value
			map.put(TAG_NO, Integer.toString(no));
			map.put(TAG_FONT, fontlist.get(no));

			// adding HashList to ArrayList
			offersList.add(map);

			drr.add(new OneFragmentDatassd(offersList.get(no).get(TAG_NO), offersList.get(no).get(TAG_FONT)));
		}
		
		// creating new HashMap
		HashMap<String, String> mapd = new HashMap<String, String>();

		for (int nod = 0; nod < fontlistd.size(); nod++) {

			// adding each child node to HashMap key => value
			mapd.put(TAG_NOD, Integer.toString(nod));
			mapd.put(TAG_FONTD, fontlistd.get(nod));
			
			// adding HashList to ArrayList
			offersListd.add(mapd);

			rmd.add(new OneFragmentDataM(offersListd.get(nod).get(TAG_NOD), offersListd.get(nod).get(TAG_FONTD)));
		}

		// listdata.clear();
		// listdata.add(new Data("Rajan"));

		// for (int i = 0; i < fontlist.size(); i++) {
		// listdata.add(new OneFragmentData(fontlist.get(i)));
		// }

	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	final View rootViewone = inflater.inflate(R.layout.whatsfragment_one, container, false);

		//banner_ads
		bannerAdContainer = (LinearLayout) rootViewone.findViewById(R.id.banner_container);
		//Admob banner ads load without adunitid
		//Admob banner ads load with adunitid
		//adControllerlocalInstance.loadBannerAdwithAdUnitId(MainActivity.this,bannerAdContainer,prf.getString(TAG_BANNERMAINS));

		LinearLayout lyr = (LinearLayout)rootViewone.findViewById(R.id.lyr);
        rippleBackground=(RippleBackground)rootViewone.findViewById(R.id.content);
    	spinner = (Spinner) rootViewone.findViewById(R.id.spinner);
    	spinnerd = (Spinner) rootViewone.findViewById(R.id.spinnerd);
    	
//    	spinner.setOnItemSelectedListener(new SpinnerActivity());
    	
//    	int colorFrom = Color.RED;
//    	int colorTo = Color.GREEN;
//    	int duration = 1000;
//    	ObjectAnimator.ofObject(rootViewone, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo)
//    	    .setDuration(duration)
//    	    .start();
    	
		adapter = new OneFragmentListAdapter(context,drr);
		adapterd = new OneFragmentListAdapterM(context,rmd);
		
 
        // attaching data adapter to spinner
        spinner.setAdapter(adapter);
        spinnerd.setAdapter(adapterd);
     		
//     	rippleBackground.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				if(isFabOpen){				
//					animateFAB();	
//				}
//			}
//		});
     	
        final TextView txt = (TextView) rootViewone.findViewById(R.id.inputtext);
        final TextView txtr = (TextView) rootViewone.findViewById(R.id.inputtextr);

//      spinner.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				animateFAB();
//			}
//		});
      
      // Spinner click listener
   	spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// getting values from selected ListItem
				try {
					String no = ((TextView) view.findViewById(R.id.fragment_one_next_no)).getText().toString();

					System.out.println("Rjn_no" + no);
					rajan = fontsad.init(context, Integer.parseInt(no));

					String myString = txt.getText().toString();
					if (!myString.isEmpty()) {
						main = myString;
						output = fontsad.convertText(main, rajan);
						txtr.setText(output.toString().trim());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
   	
		// Spinnerd click listener
		spinnerd.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// getting values from selected ListItem
				try {
					String no = ((TextView) view.findViewById(R.id.fragment_one_next_nod)).getText().toString();
					String sml = ((TextView) view.findViewById(R.id.fragment_one_next_fontd)).getText().toString();

					if (Integer.parseInt(no) != 0) {
						System.out.println("Rjn_no" + no);
						// rajan = font.init(context, Integer.parseInt(no) + 1);

						String myString = txt.getText().toString();
						StringBuilder sb = new StringBuilder(myString);

						if (!myString.isEmpty()) {
							String result = sb.append(" ").append(sml).toString();

							main = result;
							output = result;
							txt.setText(output.toString().trim());
							txtr.setText(output.toString().trim());
						} else {
							main = sml.toString();
							output = sml.toString();
							txt.setText(output.toString().trim());
							txtr.setText(output.toString().trim());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
   	
//        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/0.ttf");
//        txt.setTypeface(font);
        
        txt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isFabOpen){
					animateFAB();
				}

				if (count == 3) {
					count = 1;
//					adControllerlocalInstance.showInterAdOnly((Activity) context, 101);
				} else {
					count++;
				}
			}
		});
        
        txtr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isFabOpen){
					animateFAB();
				}
			}
		});
        
        txt.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
//				Log.i("TC", "onTC " + s.toString() + " " + s.subSequence(start, start + count).toString());

				try {
					main = s.toString();
//				ed.setText(text.substring(0, text.length() - 1));
//				You can also use following procedure....it will be more efficient.

//				int length = main.length();
//				if (length > 0) {
//				    ed.getText().delete(length - 1, length);
//				    
//					s.charAt(s.length() - 1);
//
//				}

					if (rajan != null && rajan.length > 0) {

						output = fontsad.convertText(main, rajan);

//				ed.removeTextChangedListener(this);

						txtr.setText(output.toString().trim());
//                ed.setSelection(ed.getSelectionEnd());

//				ed.setSelection(output.length());
//                ed.addTextChangedListener(this);

//				System.out.println("Rjn_after"+s.toString());
//
//				System.out.println("Rjn_on"+s.subSequence(start, start + count).toString());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}


				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
        
        lyr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isFabOpen){
					animateFAB();
				}
			}
		});
        
        fabLayout1= (LinearLayout) rootViewone.findViewById(R.id.fabLayout1);
        fabLayout2= (LinearLayout) rootViewone.findViewById(R.id.fabLayout2);
        fabLayout3= (LinearLayout) rootViewone.findViewById(R.id.fabLayout3);
        
        fab = (FloatingActionButton)rootViewone.findViewById(R.id.fab);
        fab1 = (FloatingActionButton)rootViewone.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)rootViewone.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)rootViewone.findViewById(R.id.fab3);

        fab_open = AnimationUtils.loadAnimation(context, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(context, R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(context, R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(context, R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        
        fab.bringToFront();
        fab1.bringToFront();
        fabLayout1.bringToFront();
        fab2.bringToFront();
        fabLayout2.bringToFront();
        fab3.bringToFront();
        fabLayout3.bringToFront();
        
        return rootViewone;
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
	}


	@Override
	public void onClick(View v) {
        int id = v.getId();
		if (id == R.id.fab) {
			rippleBackground.startRippleAnimation();
			animateFAB();
		} else if (id == R.id.fab1) {
			rippleBackground.stopRippleAnimation();
			animateFAB();

			ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
			cm.setText(output);
			Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
//			adControllerlocalInstance.showInterAdOnly((Activity) context, 101);
		} else if (id == R.id.fab2) {
			rippleBackground.stopRippleAnimation();
			animateFAB();

			ClipboardManager cmr = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
			cmr.setText(output);

			Intent sharingIntent = new Intent();
			sharingIntent.setAction(Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(Intent.EXTRA_TEXT, output);
			startActivity(Intent.createChooser(sharingIntent, "Share via"));
		} else if (id == R.id.fab3) {
			rippleBackground.stopRippleAnimation();
			animateFAB();

			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.setPackage("com.whatsapp");
			sendIntent.putExtra(Intent.EXTRA_TEXT, output);
			sendIntent.setType("text/plain");
			try {
				startActivity(sendIntent);
			} catch (Exception e) {
				Toast.makeText(context, "WhatsApp App is not installed in Your Phone.", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		}
    }


	private void animateFAB() {

        if(isFabOpen){

        	fabLayout1.animate().translationY(0);
            fabLayout2.animate().translationY(0);
            fabLayout3.animate().translationY(0).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if(!isFabOpen){
                        fabLayout1.setVisibility(View.GONE);
                        fabLayout2.setVisibility(View.GONE);
                        fabLayout3.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            
            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);

            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);

            rippleBackground.stopRippleAnimation();
            
            isFabOpen = false;

        } else {

        	fabLayout1.setVisibility(View.VISIBLE);
        	fabLayout2.setVisibility(View.VISIBLE);
            fabLayout3.setVisibility(View.VISIBLE);
            
        	fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_5));
            fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.standard_5));
            fabLayout3.animate().translationY(-getResources().getDimension(R.dimen.standard_5));
            
            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);

            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);

            isFabOpen = true;

        }
    }
	    
}
