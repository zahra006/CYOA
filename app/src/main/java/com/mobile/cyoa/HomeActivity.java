package com.mobile.cyoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mobile.cyoa.Fragments.AccountFragment;
import com.mobile.cyoa.Fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameHomeContainer, new HomeFragment(),HomeFragment.class.getSimpleName()).commit();
        init();
    }

    private void init() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_book:{
                        Fragment account = fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName());
                        if (account!=null){
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                        }

                        break;
                    }
                    case R.id.item_account:{
                        Fragment account = fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName());
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                        if (account!=null){
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName())).commit();
                        }else {
                            fragmentManager.beginTransaction().add(R.id.frameHomeContainer,new AccountFragment(),AccountFragment.class.getSimpleName()).commit();
                        }

                        break;
                    }
                }
                return true;
            }
        });
    }
}