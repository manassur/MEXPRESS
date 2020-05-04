package com.app.mexpress;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.app.mexpress.fragment.HomeFragment;
import com.app.mexpress.fragment.MoreFragment;
import com.app.mexpress.fragment.SavedFragment;
import com.app.mexpress.fragment.SourceFragment;
import com.cloudinary.android.MediaManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout viewlayout;
    NavigationView navigationView;
    ImageView  harmburger;

    // drawer layout
    private ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout drawer;
    Toolbar mToolbar;
    View headerView;

    // Declare fragments here
     FragmentManager fragmentManager ;
     HomeFragment homeFragment;
     MoreFragment moreFragment;
     SourceFragment sourceFragment;
     SavedFragment savedFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize MediaManager
        MediaManager.init(this);
        setUpAllBindings();
        setUpToolBar();
        setUpNavigation();
        setUpFragments();
        setUpBottomNavigation();
        setUpHarmburger();

    }




    public void setUpToolBar(){
//         mToolbar = findViewById(R.id.toolbar);
//        mToolbar.setTitle(getString(R.string.app_name));
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        mToolbar.setNavigationIcon(null);// to hide Navigation icon
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void setUpAllBindings(){
        navigationView = findViewById(R.id.nav_view);
        viewlayout = findViewById(R.id.viewlayout);
        bottomNavigationView =  findViewById(R.id.bottom_navigation);
        drawer = findViewById(R.id.drawer_layout);
        headerView = navigationView.getHeaderView(0);
    }

    public  void setUpNavigation(){
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer,mToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        drawer.addDrawerListener(mDrawerToggle);
        // drawerListener must be set before syncState is called
        drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });



        navigationView.bringToFront();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.business:
                        drawer.closeDrawer(navigationView,true); //Edit Gravity.START need API
                        break;
                    default:
                        return true;
                }


                return true;

            }
        });


    }

    private void setUpFragments() {
        fragmentManager  = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        moreFragment = new MoreFragment();
        sourceFragment= new SourceFragment();
        savedFragment = new SavedFragment();
    }

    private void setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = homeFragment;
                        switch (item.getItemId()) {

                            case R.id.navigation_home:
                                fragment = homeFragment;
                                setTitle(item.getTitle());
                                break;
                            case R.id.navigation_source:
                                fragment = sourceFragment;
                                setTitle(item.getTitle());
                                break;
                            case R.id.navigation_saved:
                                fragment = savedFragment;
                                setTitle(item.getTitle());
                                break;
                            case R.id.navigation_more:
                                fragment = moreFragment;
                                setTitle(item.getTitle());
                                break;
                            default:
                                fragment = homeFragment;
                                setTitle(item.getTitle());
                                break;

                        }
                        fragmentManager.beginTransaction().replace(R.id.viewlayout, fragment).commit();
                        return true;
                    }
                });

        // Set default selection
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    private void setUpHarmburger(){
        harmburger = findViewById(R.id.harmburger);
        harmburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }

}
