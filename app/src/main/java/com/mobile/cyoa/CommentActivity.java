package com.mobile.cyoa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.cyoa.Adapters.CommentsAdapter;
import com.mobile.cyoa.Models.Comment;
import com.mobile.cyoa.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Comment> list;
    private CommentsAdapter adapter;
    private int bookId = 0;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
    }

    private void init() {
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        recyclerView = findViewById(R.id.recyclerComment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookId = getIntent().getExtras().getInt("bookId",0);
        getComments();
    }

    private void getComments() {
        list = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.POST,Constant.COMMENTS,response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")){
                    JSONArray comments = new JSONArray(object.getString("comments"));
                    for (int i = 0; i < comments.length(); i++) {
                        JSONObject comment = comments.getJSONObject(i);
                        JSONObject user = comment.getJSONObject("user");

                        User mUser = new User();
                        mUser.setId(user.getInt("id"));
                        mUser.setPhoto(user.getString("photo"));
                        mUser.setUserName(user.getString("name")+" "+user.getString("lastname"));

                        Comment mComment = new Comment();
                        mComment.setId(comment.getInt("id"));
                        mComment.setUser(mUser);
                        mComment.setComment(comment.getString("comment"));
                        mComment.setDate(comment.getString("created_at"));
                        list.add(mComment);
                    }
                    adapter = new CommentsAdapter(this,list);
                    recyclerView.setAdapter(adapter);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        },error -> {
            error.printStackTrace();

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = sharedPreferences.getString("token", "");
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

        RequestQueue queue = Volley.newRequestQueue(CommentActivity.this);
        queue.add(request);
    }

    public void goBack(View view) {
        super.onBackPressed();
    }
}