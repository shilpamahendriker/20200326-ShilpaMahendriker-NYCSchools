package com.example.a20200326_shilpamahendriker_nycschools.views;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.example.a20200326_shilpamahendriker_nycschools.R;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity  {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        //Attach the fragment which has the recycler view for loading school names
        Fragment fragment = new SchoolsListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, fragment,null).addToBackStack(null).commit();

        drawerLayout.addDrawerListener(drawerToggle);
        setupDrawerToggle();
        navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        menuItem.setChecked(true);
                        fragment = new SchoolsListFragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.fragment_placeholder, fragment).addToBackStack(null).commit();
                        break;
                    case R.id.about_us:
                        menuItem.setChecked(true);
                        fragment = new AboutUsFragment();
                        FragmentManager fragmentManager1 = getSupportFragmentManager();
                        fragmentManager1.beginTransaction().replace(R.id.fragment_placeholder, fragment).addToBackStack(null).commit();
                        break;
                    default:
                        fragment = new SchoolsListFragment();

                }
                setTitle(menuItem.getTitle());
                // Close the navigation drawer
                drawerLayout.closeDrawers();

                return false;
            }
        });


}


    void setupDrawerToggle(){
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        getBaseContext().startActivity(intent);
        }


}