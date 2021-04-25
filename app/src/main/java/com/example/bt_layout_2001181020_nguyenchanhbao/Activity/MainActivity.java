package com.example.bt_layout_2001181020_nguyenchanhbao.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bt_layout_2001181020_nguyenchanhbao.Fragment.AccountFragment;
import com.example.bt_layout_2001181020_nguyenchanhbao.Fragment.DashboardFragment;
import com.example.bt_layout_2001181020_nguyenchanhbao.Fragment.HomeFragment;
import com.example.bt_layout_2001181020_nguyenchanhbao.Fragment.NotificationFragment;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Customer;
import com.example.bt_layout_2001181020_nguyenchanhbao.Module.Utils;
import com.example.bt_layout_2001181020_nguyenchanhbao.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navView;
    EditText searchView;
    Customer customer;
    boolean isLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        Utils.context = this;

        SharedPreferences sharedpreferences = getSharedPreferences("isDBLoaded", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        isLoaded = sharedpreferences.getBoolean("isLoad", false);

        if (!isLoaded) {
            editor.putBoolean("isLoad", true);
            editor.commit();
        }

        navView = findViewById(R.id.nav_view);
        loadFragment(new HomeFragment());

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_dashboard);

        searchView = findViewById(R.id.search_view);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    findViewById(R.id.appBarLayout).setVisibility(View.VISIBLE);
//                    getSupportActionBar().setTitle("Dashboard");
                    fragment = new DashboardFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_home:
                    findViewById(R.id.appBarLayout).setVisibility(View.VISIBLE);
//                    getSupportActionBar().setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_notifications:
                    findViewById(R.id.appBarLayout).setVisibility(View.VISIBLE);
//                    getSupportActionBar().setTitle("Notification");
                    fragment = new NotificationFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_account:
                    findViewById(R.id.appBarLayout).setVisibility(View.GONE);

                    Intent loginIntent = getIntent();
                    customer = (Customer) loginIntent.getSerializableExtra("info");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info", customer);

                    fragment = new AccountFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        Utils.WriteToFileInternal();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Utils.WriteToFileInternal();
    }

}