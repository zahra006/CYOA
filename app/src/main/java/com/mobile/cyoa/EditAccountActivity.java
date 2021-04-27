package com.mobile.cyoa;

import androidx.appcompat.app.AppCompatActivity;

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
        etFirst = findViewById(R.id.etFirstNameProf);
        etLast = findViewById(R.id.etLastNameProf);
        etEmail = findViewById(R.id.etEmailProf);
        btnSaveEdit = findViewById(R.id.btnSave);
        imgEdProf = findViewById(R.id.imgUserDetail);
        getUser();
        editUser();
    }

    private void getUser() {
        StringRequest request = new StringRequest(Request.Method.GET,Constant.USER, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")){
                    JSONObject user = object.getJSONObject("user");
                    etFirst.setText(user.getString("name"));
                    etLast.setText(user.getString("lastname"));
                    etEmail.setText(user.getString("email"));
                    Picasso.get().load(Constant.URL+"storage/profiles/"+user.getString("photo")).into(imgEdProf);
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
        RequestQueue queue = Volley.newRequestQueue(EditAccountActivity.this);
        queue.add(request);
    }

    private void editUser() {
        btnSaveEdit.setOnClickListener(v -> {
            //POST JSON
            //Update JSON


            //back to profile
            startActivity(new Intent(EditAccountActivity.this, AccountFragment.class));
        });
    }
}