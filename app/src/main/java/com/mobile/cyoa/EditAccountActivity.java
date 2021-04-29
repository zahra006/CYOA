package com.mobile.cyoa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditAccountActivity extends AppCompatActivity {

    private EditText etFirst, etLast, etEmail;
    private Button btnSaveEdit, btnUbahFoto;
    private SharedPreferences preferences;
    private CircleImageView imgEdProf;
    private static final int GALLERY_CHANGE_PROFILE =5;
    private Bitmap bitmap = null;
    private ProgressDialog dialog;

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
        btnUbahFoto = findViewById(R.id.btnUbahPhoto);

        Picasso.get().load(getIntent().getStringExtra("imgUrl")).into(imgEdProf);
        etFirst.setText(preferences.getString("name",""));
        etLast.setText(preferences.getString("lastname",""));
        etEmail.setText(preferences.getString("email",""));

        btnUbahFoto.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            startActivityForResult(i, GALLERY_CHANGE_PROFILE);
        });
        btnSaveEdit.setOnClickListener(v ->{
                editUser();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLERY_CHANGE_PROFILE && resultCode==RESULT_OK){
            Uri imgUri = data.getData();
            imgEdProf.setImageURI(imgUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void editUser() {
        dialog.setMessage("Memperbarui...");
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,Constant.UPDATE_USER_INFO,response -> {

        },error->{
            error.printStackTrace();
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = preferences.getString("token","");
                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization","Bearer "+token);
                return map;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("name",etFirst.getText().toString().trim());
                map.put("lastname",etLast.getText().toString().trim());
                map.put("email",etEmail.getText().toString().trim());

                //convert bitmap jadi string
                map.put("photo",bitmapToString(bitmap));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(EditAccountActivity.this);
        queue.add(request);
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
}