package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew;

import static com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.WhatsHomeActivitysadas.TAG_NEWVERSION;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.MessagesFragment;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.ShareFragmentNewasd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsHomeFragmentsdsad;


public class WhatsMainActivityasdf extends AppCompatActivity implements FragmentDrawersad.FragmentDrawerListener {

	//new
	public static Context context;

    private Toolbar mToolbar;
    private FragmentDrawersad drawerFragment;

	//Prefrance
	private static PrefManagersd prf;

    // check internet
	// flag for Internet connection status
	Boolean isInternetPresent = false;

//	// Connection detector class
//	ConnectionDetector cd;
		
	//back
	public static int positionr=0;
	public static int backbackexit=1;

	private int rds = 0;
	public static int rajan = 0;

    private Dialog dialog;
	private  Boolean DialogOpened = false;
    private TextView text_view_go_pro;

    private TextView restore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatsactivity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
		setupActionBar();

        drawerFragment = (FragmentDrawersad)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

		context = getApplicationContext();

		prf = new PrefManagersd(context);


		try {
			PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			String version = pInfo.versionName;

			System.out.println("Rajan_version" + "old:" + version + " new:" + prf.getString(TAG_NEWVERSION));
			if (Float.parseFloat(version) < Float.parseFloat(prf.getString(TAG_NEWVERSION))) {
				Intent intent = new Intent(WhatsMainActivityasdf.this, WhatsAppUpdaterActivitysad.class);
				intent.putExtra("newversion", prf.getString(TAG_NEWVERSION));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
      displayView(0);

    }

	public void bckpressed() {
		super.onBackPressed();
	}
    
    @Override
	public void onBackPressed() {
		if (getSupportFragmentManager().getBackStackEntryCount() == 0) {

			Fragment fragment = null;
			Fragment fragmentr = getSupportFragmentManager().findFragmentById(R.id.container_body);
			String title = getString(R.string.app_name);
			switch (positionr) {
			case 0:
				fragment = new WhatsHomeFragmentsdsad();
				title = getString(R.string.title_home);
				break;
			case 1:
				fragment = new ShareFragmentNewasd();
				title = getString(R.string.title_share);
				break;
			case 3:
				fragment = new MessagesFragment();
				title = getString(R.string.title_messages);
				break;
			default:
				break;
			}

			if (fragmentr.getClass().equals(fragment.getClass()) && fragmentr.isVisible()) {
//				if (backbackexit >= 2) {
//
//					// Creating alert Dialog with three Buttons
//
//					AlertDialog.Builder alertDialog = new AlertDialog.Builder(
//							MainActivity.this);
//
//					// Setting Dialog Title
//					alertDialog.setTitle("Fonts For WhatsApp");
//
//					// Setting Dialog Message
//					alertDialog.setMessage("Rate us on Playstore?");
//
//					// Setting Icon to Dialog
//					alertDialog.setIcon(R.drawable.ic_launcher);
//
//					// Setting Positive Yes Button
//					alertDialog.setPositiveButton("YES",
//							new DialogInterface.OnClickListener() {
//
//								public void onClick(DialogInterface dialog,
//										int which) {
//									startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getApplicationContext().getPackageName())));
//								}
//							});
//					// Setting Positive Yes Button
//					alertDialog.setNeutralButton("NO",
//							new DialogInterface.OnClickListener() {
//
//								public void onClick(DialogInterface dialog,
//										int which) {
//									finish();
//								}
//							});
//					// Setting Positive "Cancel" Button
//					alertDialog.setNegativeButton("Cancel",
//							new DialogInterface.OnClickListener() {
//
//								public void onClick(DialogInterface dialog,
//										int which) {
//									// User pressed Cancel button. Write Logic Here
//									Toast.makeText(getApplicationContext(),
//											"Cancel",
//											Toast.LENGTH_SHORT).show();
//								}
//							});
//					// Showing Alert Message
//					alertDialog.show();
////					super.onBackPressed();
//				} else {
//					backbackexit++;
//					Toast.makeText(getBaseContext(), "Press again to Exit", Toast.LENGTH_SHORT).show();
//				}
//				System.out.println("backbacksame");

//				if (((backbackexit % 2) == 0) && DialogOpened) {
//					backbackexit++;
//					DialogOpened=false;
//					dialog.dismiss();
//					finish();
//				} else {
//					backbackexit++;
//					if (dialog != null) {
//						dialog.show();
//						DialogOpened=true;
//					}
//				}

//				final Intent i = new Intent(MainActivity.this, FirstActivity.class);

			} else {
				// Fragment fragment = new MessagesFragment();
				FragmentManager fragmentManager = getSupportFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.container_body, fragment);
				fragmentTransaction.commitAllowingStateLoss();

				// set the toolbar title
				getSupportActionBar().setTitle(title);

				// Works with either the framework FragmentManager or the
				// support package FragmentManager (getSupportFragmentManager).
				// final FragmentTransaction FragmentTransaction =
				// getSupportFragmentManager().beginTransaction();
				// FragmentTransaction.add(fragment, "detail");
				// // Add this transaction to the back stack
				// FragmentTransaction.addToBackStack("Rajan");
				// FragmentTransaction.commit();
				System.out.println("backback");
			}
		} else {
			getFragmentManager().popBackStack();
			System.out.println("backback1");
		}
	}

    private void setupActionBar() {
		// TODO Auto-generated method stub
//    	ActionBar actionBar = getSupportActionBar();
//		actionBar.setDisplayShowTitleEnabled(true);
//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//		actionBar.setDisplayUseLogoEnabled(true);
//		actionBar.setLogo(R.drawable.icon);
//		actionBar.setDisplayHomeAsUpEnabled(false);
//		actionBar.setHomeButtonEnabled(false);
//		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
//		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#550000ff")));
		
//		SpannableString s = new SpannableString("Select Party");
//		s.setSpan(new TypefaceSpan("/fonts/SystemSanFranciscoDisplayBold.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    s.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		actionBar.setTitle(s);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.whatsmenu_main, menu);
		final MenuItem pro = menu.findItem(R.id.action_pro);

//		if (prf.getString("rd").contains("1") && SplashScreenActivity.rajan == 1) {
//			pro.setVisible(true);
//		}


//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);

		return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
//			Fragment fragment = new MessagesFragment();
//			String title = getString(R.string.app_name);
//			title = getString(R.string.title_messages);
//			
//			//back
//			positionr=6;
//			backbackexit=1;
//			
//			if (fragment != null) {
//	            FragmentManager fragmentManager = getSupportFragmentManager();
//	            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//	            fragmentTransaction.replace(R.id.container_body, fragment);
//	            fragmentTransaction.commit();
//
//	            // set the toolbar title
//	            getSupportActionBar().setTitle(title);
//	        }
//			
//			return true;
		}

        if(id == R.id.action_search){
////            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
//        	Fragment fragment = new MessagesFragment();
//			String title = getString(R.string.app_name);
//			title = getString(R.string.title_find);
//			
//			//back
//			positionr=4;
//			backbackexit=1;
//			
//			if (fragment != null) {
//	            FragmentManager fragmentManager = getSupportFragmentManager();
//	            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//	            fragmentTransaction.replace(R.id.container_body, fragment);
//	            fragmentTransaction.commit();
//
//	            // set the toolbar title
//	            getSupportActionBar().setTitle(title);
//	        }
			
            return true;
        }

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_pro) {
			if (prf.getString("SUBSCRIBED").equals("TRUE")) {
				Toast.makeText(context, "You Are Already Pro User", Toast.LENGTH_SHORT).show();
			} else {
//				showDialog();
			}

			return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void showDialog(){
//        this.dialog = new Dialog(this,
//                R.style.Theme_Dialog);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(true);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setContentView(R.layout.dialog_subscribe);
//        Window window = dialog.getWindow();
//        WindowManager.LayoutParams wlp = window.getAttributes();
//        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//        wlp.gravity = Gravity.BOTTOM;
//        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//        window.setAttributes(wlp);
//        this.text_view_go_pro=(TextView) dialog.findViewById(R.id.text_view_go_pro);
//        RelativeLayout relativeLayout_close_rate_gialog=(RelativeLayout) dialog.findViewById(R.id.relativeLayout_close_rate_gialog);
//        relativeLayout_close_rate_gialog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        this.text_view_go_pro=(TextView) dialog.findViewById(R.id.text_view_go_pro);
//        text_view_go_pro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bp.subscribe(MainActivity.this, SUBSCRIPTION_ID);
//            }
//        });
//		this.restore=(TextView) dialog.findViewById(R.id.restore);
//		restore.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				PrefManager prf= new PrefManager(getApplicationContext());
//				bp.loadOwnedPurchasesFromGoogle();
//				if(isSubscribe(SUBSCRIPTION_ID)){
//					prf.setString("SUBSCRIBED","TRUE");
//					System.out.println("SUBSCRIBED");
//					Toast.makeText(MainActivity.this, "Your Subscription Restored Successfully", Toast.LENGTH_SHORT).show();
//
//				}
//				else{
//					prf.setString("SUBSCRIBED","FALSE");
//					System.out.println("NOT SUBSCRIBED");
//					Toast.makeText(MainActivity.this, "Restore failed. You don't own this premium subscription", Toast.LENGTH_SHORT).show();
//				}
//			}
//		});
//        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
//
//            @Override
//            public boolean onKey(DialogInterface arg0, int keyCode,
//                                 KeyEvent event) {
//                // TODO Auto-generated method stub
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    dialog.dismiss();
//                }
//                return true;
//            }
//        });
//        dialog.show();
//        DialogOpened=true;
//
//    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

	/**
	 * Display content corresponding to item selected in drawer
	 * @param position : index of the item
	 */
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
		switch (position) {
		case 0:
			fragment = new WhatsHomeFragmentsdsad();
			title = getString(R.string.title_home);
			break;
		case 1:
			fragment = new ShareFragmentNewasd();
			title = getString(R.string.title_share);
			break;
		case 2:
			rajan = 0;
			prf.setString("rd","0");
			Toast.makeText(context, "No More Apps", Toast.LENGTH_SHORT).show();
			break;
		case 3:
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://infinityworldvideodownloader.blogspot.com/2019/04/privacy-policy.html"));
			startActivity(browserIntent);
			break;
		case 4:
			fragment = new MessagesFragment();
			title = getString(R.string.title_messages);
			break;
		default:
			break;
		}

        if (fragment != null) {
        	
        	//back
        	positionr=position;
        	backbackexit=1;

			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.container_body, fragment);
			fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

	//Method checks if any problem when Intent

	public boolean MyStartActivity(Intent aIntent) {
		try {
			startActivity(aIntent);
			return true;
		} catch (ActivityNotFoundException e) {
			return false;
		}
	}

}