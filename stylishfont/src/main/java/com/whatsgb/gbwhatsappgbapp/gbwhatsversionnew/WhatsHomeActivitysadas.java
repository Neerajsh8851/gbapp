package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.nibodev.androidutil.Fire;
import com.nibodev.mobileads.MobileAd;
import com.nibodev.mobileads.NativeAdLoader;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsBubbleFragmenttgdsf;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsFavouriteFragmentgds;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsHomeFragmentsaff;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsSettingFragmentdsfdsf;

import java.util.Objects;


public class WhatsHomeActivitysadas extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    public static String TAG_NEWVERSION = "newversion";
    public static final String TAG_NEWAPPURL = "newappurl";
    //Prefrance
    private static PrefManagersa prf;

    //new
    private Context context;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ChipNavigationBar navBar;
    MaterialToolbar toolbar;

    TemplateView templateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatsactivity_homety);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        context = getApplicationContext();

        prf = new PrefManagersa(context);

        navBar = findViewById(R.id.chipnav);
        navBar.setOnItemSelectedListener(this);
        navBar.setItemSelected(R.id.home,true);

        final Fragment fragment = new WhatsHomeFragmentsaff();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,fragment)
                .commit();

        //Drawer Layout
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        navigationView.getMenu().getItem(0).setChecked(true);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        templateView = findViewById(R.id.template_view);
        // native ad
        String adId = Fire.getString("native_ad_id_1");
        new NativeAdLoader(adId).attachNativeAd(templateView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            templateView.destroyNativeAd();
        } catch (Exception ignore){}
    }

    //Fragment Container
    private void loadFragment(final Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,fragment)
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int i) {
        MobileAd.loadInterAd(this, null);
        Fragment fragment = null;
        if (i == R.id.home) {
            if (navigationView != null) {
                navigationView.getMenu().getItem(0).setChecked(true);
            }
            Objects.requireNonNull(getSupportActionBar()).setTitle("Home");
            fragment = new WhatsHomeFragmentsaff();
        } else if (i == R.id.favourite) {
            navigationView.getMenu().getItem(1).setChecked(true);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Favourite");
            fragment = new WhatsFavouriteFragmentgds();
        } else if (i == R.id.floatmenu) {
            navigationView.getMenu().getItem(2).setChecked(true);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Floating Bubble");
            fragment = new WhatsBubbleFragmenttgdsf();
        } else if (i == R.id.settingmenu) {
            navigationView.getMenu().getItem(3).setChecked(true);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Settings");
            fragment = new WhatsSettingFragmentdsfdsf();
        }
        loadFragment(fragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        MobileAd.loadInterAd(this, null);
        item.setChecked(!item.isChecked());
        drawerLayout.closeDrawers();

        int id = item.getItemId();
        //home menu
        if (id == R.id.nav_home) {
            navigationView.getMenu().getItem(0).setChecked(true);
            navBar.setItemSelected(R.id.home, true);
            new WhatsHomeActivitysadas();
            return true;
            //favourite list
        } else if (id == R.id.nav_fav) {
            navigationView.getMenu().getItem(1).setChecked(true);
            navBar.setItemSelected(R.id.favourite, true);
            new WhatsFavouriteFragmentgds();
            return true;
            //floating menu
        } else if (id == R.id.nav_float) {
            navigationView.getMenu().getItem(2).setChecked(true);
            navBar.setItemSelected(R.id.floatmenu, true);
            new WhatsBubbleFragmenttgdsf();
            return true;
            //Setting menu
        } else if (id == R.id.nav_settings) {
            navigationView.getMenu().getItem(3).setChecked(true);
            navBar.setItemSelected(R.id.settingmenu, true);
            new WhatsFavouriteFragmentgds();
            return true;
            //Share APP
        } else if (id == R.id.nav_share) {
            share();
            return true;
            //Send Email
        } else if (id == R.id.nav_send) {
            ShareCompat.IntentBuilder.from(this)
                    .setType("message/rfc822")
                    .addEmailTo(getString(R.string.email))
                    .setSubject("Any Queries")
                    .setText("Type Here")
                    .startChooser();
            return true;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.whatshome_menu, menu);
        final MenuItem pro = menu.findItem(R.id.action_pro);
        return super.onCreateOptionsMenu(menu);
    }

    //Share APP Link
    // TODO: Change this fun implementation according to app
    private void share() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.app_name));
            String sAux = "\n" + getResources().getString(R.string.Let_me_recommend_you_this_application) + "\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=" + getApplication().getPackageName();
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            MobileAd.loadInterAd(this, null);
            finishAfterTransition();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.action_pro) {
            if (prf.getString("SUBSCRIBED").equals("TRUE")) {
                Toast.makeText(context, "You Are Already Pro User", Toast.LENGTH_SHORT).show();
            } else {
//                showDialog();
            }
            return true;
        }
        return true;
    }

}