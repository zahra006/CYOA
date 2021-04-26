package com.mobile.cyoa.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.cyoa.AuthActivity;
import com.mobile.cyoa.Constant;
import com.mobile.cyoa.HomeActivity;
import com.mobile.cyoa.Models.Book;
import com.mobile.cyoa.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {
    private View view;
    private TextView txtFirst, txtLast, txtEmail;
    private ImageButton btnEdit;
    private CircleImageView imgProf;
    private SharedPreferences preferences;
    private Button btnLogout;
    private ArrayList<Book> arrayList;


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
        preferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        txtFirst = view.findViewById(R.id.txtFirstNameProf);
        txtLast = view.findViewById(R.id.txtLastNameProf);
        txtEmail = view.findViewById(R.id.txtEmailProf);
        btnEdit = view.findViewById(R.id.imgEdit);
        imgProf = view.findViewById(R.id.imgUserDetail);
        btnLogout = view.findViewById(R.id.btnLogout);

        getUser();

        btnLogout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Apakah anda ingin Keluar dari Aplikasi?");
            builder.setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    logout();
                }
            });
            builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        });


    }

    private void getUser() {
        arrayList = arrayList = new ArrayList<>();
        //StringRequest request = new StringRequest(Request.Method.GET,Constant.)
        //JSONObject user =
    }

    private void logout() {
        StringRequest request = new StringRequest(Request.Method.GET, Constant.LOGOUT,response -> {

            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(((HomeActivity)getContext()), AuthActivity.class));
                    ((HomeActivity)getContext()).finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        },error -> {
            error.printStackTrace();
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = preferences.getString("token", "");
                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer "+token);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
}
