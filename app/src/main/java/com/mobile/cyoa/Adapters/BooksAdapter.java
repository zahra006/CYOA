package com.mobile.cyoa.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.cyoa.Constant;
import com.mobile.cyoa.Models.Book;
import com.mobile.cyoa.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksHolder>{

    private Context context;
    private ArrayList<Book> list;

    public BooksAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_book,parent,false);
        return new BooksHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksHolder holder, int position) {
        Book book = list.get(position);
        Picasso.get().load(Constant.URL+"storage/covers/"+book.getCover()).into(holder.imgBookCover);
        holder.txtBookTitle.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BooksHolder extends RecyclerView.ViewHolder{

        private TextView txtBookTitle;
        private ImageView imgBookCover;

        public BooksHolder(@NonNull View itemView) {
            super(itemView);

            txtBookTitle = itemView.findViewById(R.id.txtBookTitle);
            imgBookCover = itemView.findViewById(R.id.imgBookCover);

        }
    }
}
