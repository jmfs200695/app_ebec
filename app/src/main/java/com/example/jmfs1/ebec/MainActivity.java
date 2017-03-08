package com.example.jmfs1.ebec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jmfs1.ebec.Fotos.Fotografias;
import com.example.jmfs1.ebec.schedulefragment.ScheduleFragment;
import com.example.jmfs1.ebec.scoresfragment.ScoresFragment;
import com.example.jmfs1.ebec.shop.ShopCategoriesFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        View include = findViewById(R.id.include);
//        View appBarLayout = include.findViewById(R.id.appBarLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        Toolbar toolbar = (Toolbar) appBarLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ImageView iv = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.ebec).asBitmap().into(iv);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.log_out) {
            // Remove data in users phone
            SharedPreferences.Editor prefs = getApplicationContext().getSharedPreferences("LOGIN_PREFS", 0).edit();
            prefs.clear();
            prefs.commit();

            // After logout redirect user to Login Activity
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);

            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            getApplicationContext().startActivity(i);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fm = getSupportFragmentManager();

        switch(id)
        {
            case R.id.nav_clock:
                fm.beginTransaction().replace(R.id.frame, new ScheduleFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_loja:
                fm.beginTransaction().replace(R.id.frame, new ShopCategoriesFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_pontuacoes:
                fm.beginTransaction().replace(R.id.frame, new ScoresFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_fotos:
                fm.beginTransaction().replace(R.id.frame, new Fotografias()).addToBackStack(null).commit();
                break;
            case R.id.nav_parceiros:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
