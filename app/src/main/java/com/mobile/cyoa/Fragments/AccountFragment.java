package com.mobile.cyoa.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobile.cyoa.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {
    private View view;
    public AccountFragment (){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_account,container,false);
        init();
        return view;
    }

    private void init() {
        TextView txtFirst, txtLast, txtEmail;
        ImageButton btnEdit;
        CircleImageView imgProf;

        txtFirst = view.findViewById(R.id.txtFirstNameProf);
        txtLast = view.findViewById(R.id.txtLastNameProf);
        txtEmail = view.findViewById(R.id.txtEmailProf);
        btnEdit = view.findViewById(R.id.imgEdit);
        imgProf = view.findViewById(R.id.imgUserDetail);


    }
}
