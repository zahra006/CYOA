package com.mobile.cyoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.mobile.cyoa.Fragments.HomeFragment;
import com.mobile.cyoa.Fragments.SynopsisFragment;

public class SynopsisActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameSynopsisContainer, new SynopsisFragment()).commit();
    }
}