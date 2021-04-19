package com.mobile.cyoa.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobile.cyoa.R;

public class SynopsisFragment extends Fragment {
    private View view;
    TextView txtTitle, txtSynopsis;
    Button btnRead;
    ImageView imgSyn;

    public SynopsisFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_synopsis,container,false);
        init();
        return view;
    }

    private void init() {
        txtTitle = view.findViewById(R.id.txtSynopsisTitle);
        txtSynopsis = view.findViewById(R.id.txtSynopsis);
        btnRead = view.findViewById(R.id.btnRead);
        imgSyn = view.findViewById(R.id.imgSynopsisCover);


    }
}
