package com.mobile.cyoa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.cyoa.Adapters.CommentsAdapter;
import com.mobile.cyoa.Fragments.HomeFragment;
import com.mobile.cyoa.Models.Book;
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
    public static int bookPosition = 0;
    private SharedPreferences preferences;
    private EditText txtAddComment;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
    }

    private void init() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        recyclerView = findViewById(R.id.recyclerComments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookId = getIntent().getExtras().getInt("bookId",0);
        bookPosition = getIntent().getExtras().getInt("bookPosition",-1);
        txtAddComment = findViewById(R.id.etComment);
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
                        mUser.setPhoto(Constant.URL+"storage/profiles/"+user.getString("photo"));
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

        RequestQueue queue = Volley.newRequestQueue(CommentActivity.this);
        queue.add(request);
    }

    public void goBack(View view) {
        super.onBackPressed();
    }

    public void addComment(View view) {
        dialog.setMessage("Menambahkan...");
        dialog.show();
        String commentText = txtAddComment.getText().toString();
        if (commentText.length()>0){
            StringRequest request = new StringRequest(Request.Method.POST,Constant.CREATE_COMMENTS,response -> {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")){
                        JSONObject comment = object.getJSONObject("comment");
                        JSONObject user = comment.getJSONObject("user");

                        Comment c = new Comment();

                        User u = new User();
                        u.setId(user.getInt("id"));
                        u.setPhoto(Constant.URL+"storage/profiles/"+user.getString("photo"));
                        u.setUserName(user.getString("name")+" "+user.getString("lastname"));

                        c.setUser(u);
                        c.setId(comment.getInt("id"));
                        c.setDate(comment.getString("created_at"));
                        c.setComment(comment.getString("comment"));




                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            },error -> {
                error.printStackTrace();
                dialog.dismiss();
            }){
                //add token to header
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
                    map.put("comment",commentText);
                    return map;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(CommentActivity.this);
            queue.add(request);
        }

    }
}