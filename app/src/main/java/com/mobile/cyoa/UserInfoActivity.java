package com.mobile.cyoa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.graphics.ImageFormat.JPEG;

public class UserInfoActivity extends AppCompatActivity {


    private TextInputLayout layoutName, layoutLastName;
    private TextInputEditText txtName, txtLastName;
    private TextView txtPilihFoto;
    private Button btnBerikut;
    private CircleImageView circleImageView;
    private static final int GALLERY_ADD_PROFILE=1;
    private Bitmap bitmap = null;
    private SharedPreferences userPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        init();
    }

    private void init() {
        userPref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        layoutName = findViewById(R.id.txtLayoutNameUserInfo);
        layoutLastName = findViewById(R.id.txtLayoutLastNameUserInfo);
        txtName = findViewById(R.id.etNameUserInfo);
        txtLastName = findViewById(R.id.etLastNameUserInfo);
        txtPilihFoto = findViewById(R.id.txtPilihFoto);
        btnBerikut = findViewById(R.id.btnBerikut);
        circleImageView = findViewById(R.id.imgUserInfo);

        //pilih foto dari gallery hp
        txtPilihFoto.setOnClickListener(v -> {
            Intent p = new Intent(Intent.ACTION_PICK);
            p.setType("image/*");
            startActivityForResult(p,GALLERY_ADD_PROFILE);
        });

        btnBerikut.setOnClickListener(v -> {
            //validasi field input
            if (validate()){
                saveUserInfo();

            }
        });


    }

    private void saveUserInfo() {
        String name = txtName.getText().toString().trim();
        String lastname = txtLastName.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST,Constant.SAVE_USER_INFO,response -> {

        }, error -> {
            error.printStackTrace();
        }){
            //masukkan token ke header

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = userPref.getString("token","");
                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization","Bearer "+token);
                return map;
            }

            //tambah parameter

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("name",name);
                map.put("lastname",lastname);

                //convert bitmap jadi string
                map.put("photo",bitmapToString(bitmap));
                return map;
            }
        };
    }

    private String bitmapToString(Bitmap bitmap) {
        if (bitmap != null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,byteArrayOutputStream);
            byte [] array = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(array,Base64.DEFAULT);
        }
        return "";
    }

    private boolean validate() {
        if(txtName.getText().toString().isEmpty()){
            layoutName.setErrorEnabled(true);
            layoutName.setError("Nama depan harus diisi");
            return false;
        }if(txtLastName.getText().toString().isEmpty()){
            layoutLastName.setErrorEnabled(true);
            layoutLastName.setError("Nama belakang harus diisi");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLERY_ADD_PROFILE && requestCode == RESULT_OK){
            Uri imgUri = data.getData();
            circleImageView.setImageURI(imgUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}