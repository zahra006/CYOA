package com.mobile.cyoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.mobile.cyoa.Fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragmentManager.beginTransaction().replace(R.id.frameHomeContainer, new HomeFragment()).commit();

    }
}