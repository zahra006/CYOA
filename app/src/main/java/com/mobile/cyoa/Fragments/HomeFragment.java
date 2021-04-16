package com.mobile.cyoa.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.cyoa.Adapters.BooksAdapter;
import com.mobile.cyoa.Constant;
import com.mobile.cyoa.HomeActivity;
import com.mobile.cyoa.Models.Book;
import com.mobile.cyoa.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ArrayList<Book> arrayList;
    private SwipeRefreshLayout refreshLayout;
    private BooksAdapter booksAdapter;
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;

    public HomeFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_home,container,false);
        init();
        return view;
    }

    private void init() {
        sharedPreferences = getContext().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        recyclerView = view.findViewById(R.id.recyclerHome);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout = view.findViewById(R.id.swipeHome);
        toolbar = view.findViewById(R.id.toolbarHome);
        ((HomeActivity)getContext()).setSupportActionBar(toolbar);

        getBooks();
    }

    private void getBooks() {
        arrayList = new ArrayList<>();
        refreshLayout.setRefreshing(true);

        StringRequest request = new StringRequest(Request.Method.GET, Constant.BOOKS, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")){
                    JSONArray array = new JSONArray(object.getString("books"));
                    for (int i = 0; i < array.length(); i++){
                        JSONObject bookObject = array.getJSONObject(i);

                        Book book = new Book();
                        book.setId(bookObject.getInt("id"));
                        book.setTitle(bookObject.getString("title"));
                        book.setSynopsis(bookObject.getString("synopsis"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },error -> {
            error.printStackTrace();

        }){
            //provide token in header

            @Override
            public Map<String, String> getHeaders() {
                String token = sharedPreferences.getString("token", "");
                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer "+token);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
}
