package com.mobile.cyoa.Adapters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.cyoa.CommentActivity;
import com.mobile.cyoa.Constant;
import com.mobile.cyoa.Fragments.HomeFragment;
import com.mobile.cyoa.Models.Comment;
import com.mobile.cyoa.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsHolder>{

    private Context context;
    private ArrayList<Comment> list;
    private SharedPreferences preferences;
    private ProgressDialog dialog;

    public CommentsAdapter(Context context, ArrayList<Comment> list) {
        this.context = context;
        this.list = list;
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_comment,parent,false);
        return new CommentsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder holder, int position) {
        Comment comment = list.get(position);
        Picasso.get().load(comment.getUser().getPhoto()).into(holder.imgProfile);
        holder.txtName.setText(comment.getUser().getUserName());
        holder.txtDate.setText(comment.getDate());
        holder.txtComment.setText(comment.getComment());

        if (preferences.getInt("id",0)!=(comment.getUser().getId())){
            holder.imgDelete.setVisibility(View.GONE);
        }
        else {
            holder.imgDelete.setVisibility(View.VISIBLE);
            holder.imgDelete.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Apakah kamu yakin akan menghapus ini?");
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteComment(comment.getId(),position);

                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            });
        }

    }

    private void deleteComment(int commentId, int position) {
        dialog.setMessage("Menghapus...");
        dialog.show();
        StringRequest request=new StringRequest(Request.Method.POST, Constant.DELETE_COMMENTS, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")){
                    list.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context,"Berhasil dihapus",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        },error -> {
            error.printStackTrace();
            dialog.dismiss();
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
                map.put("id",commentId+"");
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CommentsHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgProfile;
        private TextView txtComment, txtDate, txtName;
        private ImageButton imgDelete;


        public CommentsHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imgUserComment);
            txtComment = itemView.findViewById(R.id.txtComment);
            txtDate = itemView.findViewById(R.id.txtDateComment);
            txtName = itemView.findViewById(R.id.txtUsernameComment);
            imgDelete = itemView.findViewById(R.id.imgDeleteComment);
        }
    }
}
