package com.mobile.cyoa.Fragments;

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

import com.android.volley.toolbox.StringRequest;
import com.mobile.cyoa.Adapters.BooksAdapter;
import com.mobile.cyoa.HomeActivity;
import com.mobile.cyoa.Models.Book;
import com.mobile.cyoa.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ArrayList<Book> arrayList;
    private SwipeRefreshLayout refreshLayout;
    private BooksAdapter booksAdapter;
    private Toolbar toolbar;

    public HomeFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_home,container,false);
        init();
        return view;
    }

    private void init() {
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

        //StringRequest.
    }
}
