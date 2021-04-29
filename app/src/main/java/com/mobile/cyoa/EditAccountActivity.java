package com.mobile.cyoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.cyoa.Fragments.AccountFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditAccountActivity extends AppCompatActivity {

    private EditText etFirst, etLast, etEmail;
    private Button btnSaveEdit;
    private SharedPreferences preferences;
    private CircleImageView imgEdProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        init();
    }

    private void init() {
        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        etFirst = findViewById(R.id.etFirstNameProf);
        etLast = findViewById(R.id.etLastNameProf);
        etEmail = findViewById(R.id.etEmailProf);
        btnSaveEdit = findViewById(R.id.btnSave);
        imgEdProf = findViewById(R.id.imgUserDetail);

        editUser();
        btnSaveEdit.setOnClickListener(v ->
            startActivity(new Intent(EditAccountActivity.this, HomeActivity.class))
        );
    }


    private void editUser() {

    }
}