package com.mobile.cyoa.Adapters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.cyoa.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter {

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
