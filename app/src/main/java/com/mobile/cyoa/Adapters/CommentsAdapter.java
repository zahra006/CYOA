package com.mobile.cyoa.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter {

    class CommentsHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgUserP;
        private TextView txtComment, txtDateComment, txtUsernameComment;


        public CommentsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
