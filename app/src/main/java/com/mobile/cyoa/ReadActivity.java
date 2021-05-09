package com.mobile.cyoa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.cyoa.Adapters.CommentsAdapter;
import com.mobile.cyoa.Models.Comment;
import com.mobile.cyoa.Models.Content;
import com.mobile.cyoa.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadActivity extends AppCompatActivity {

    private Button btnSaveState, btnSynopsis, btnOptionA, btnOptionB;
    private TextView text;
    private SharedPreferences preferences;
    private int bookId = 0;
    public static int bookPosition = 0;
    public static String read = "1";
    private ArrayList<Content> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        init();
    }

    private void init() {
        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        btnSaveState = findViewById(R.id.btnReadSavestate);
        btnSynopsis = findViewById(R.id.btnReadSynopsis);
        btnOptionA = findViewById(R.id.btnOptionA);
        btnOptionB = findViewById(R.id.btnOptionB);
        text = findViewById(R.id.txtStory);
        bookId = getIntent().getExtras().getInt("bookId",0);
        bookPosition = getIntent().getExtras().getInt("bookPosition",-1);
        Toast.makeText(this,"Book ID: "+bookId,Toast.LENGTH_SHORT).show();
        getContent();

        btnSaveState.setOnClickListener(v -> {

        });

        btnSynopsis.setOnClickListener(v -> {

        });

        btnOptionA.setOnClickListener(v -> {

        });

        btnOptionB.setOnClickListener(v -> {

        });
    }

    private void getContent() {
        StringRequest request = new StringRequest(Request.Method.GET,Constant.CONTENTS, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")){
                    JSONArray contents = new JSONArray(object.getString("contents"));
                    for (int i = 0; i < contents.length(); i++) {
                        JSONObject content = contents.getJSONObject(i);
                        if(bookId==content.getInt("book_id")){
                            if(i==0){
                                text.setText(content.getString("content"));
                            }

                        }else{
                            text.setText("Empty Story");
                        }
                    }
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

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("id",bookId+"");
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(ReadActivity.this);
        queue.add(request);
    }

}