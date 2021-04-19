package com.mobile.cyoa.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobile.cyoa.R;

public class SynopsisFragment extends Fragment {
    private View view;
    TextView txtTitle, txtSynopsis;

    public SynopsisFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_synopsis,container,false);
        init();
        return view;
    }

    private void init() {

    }
}
