package com.mobile.cyoa.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.cyoa.Models.Comment;
import com.mobile.cyoa.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsHolder>{

    private Context context;
    private ArrayList<Comment> list;

    public CommentsAdapter(Context context, ArrayList<Comment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_comment,parent,false);
        return new CommentsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder holder, int position) {
        Comment comment = list.get(position);
        Picasso.get().load(comment.getUser().getPhoto()).into(holder.imgUserP);
        holder.txtUsernameComment.setText(comment.getUser().getUserName());
        holder.txtDateComment.setText(comment.getDate());
        holder.txtComment.setText(comment.getComment());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CommentsHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgUserP;
        private TextView txtComment, txtDateComment, txtUsernameComment;
        private ImageButton imgOption;


        public CommentsHolder(@NonNull View itemView) {
            super(itemView);

            imgUserP = itemView.findViewById(R.id.imgUserComment);
            txtComment = itemView.findViewById(R.id.txtComment);
            txtDateComment = itemView.findViewById(R.id.txtDateComment);
            txtUsernameComment = itemView.findViewById(R.id.txtUsernameComment);
            imgOption = itemView.findViewById(R.id.imgOptionComment);
        }
    }
}
