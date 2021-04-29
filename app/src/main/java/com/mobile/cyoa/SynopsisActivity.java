package com.mobile.cyoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SynopsisActivity extends AppCompatActivity {
    TextView txtTitle, txtSynopsis;
    Button btnRead, btnComment;
    ImageView imgSyn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synopsis);
        init();
    }

    private void init() {
        txtTitle = findViewById(R.id.txtSynopsisTitle);
        txtSynopsis = findViewById(R.id.txtSynopsis);
        btnRead = findViewById(R.id.btnRead);
        btnComment = findViewById(R.id.btnComment);
        imgSyn = findViewById(R.id.imgSynopsisCover);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
        String synopsis = intent.getExtras().getString("Synopsis");
        String cover = intent.getExtras().getString("Cover");
        int book_id = intent.getExtras().getInt("book_Id",0);
        int book_position = intent.getExtras().getInt("book_position",-1);

        txtTitle.setText(title);
        txtSynopsis.setText(synopsis);
        Picasso.get().load(Constant.URL+"storage/covers/"+cover).into(imgSyn);

        btnRead.setOnClickListener(v -> {

        });

        btnComment.setOnClickListener(v -> {
            Intent i = new Intent(SynopsisActivity.this,CommentActivity.class);
            i.putExtra("bookId",book_id);
            i.putExtra("bookPosition",book_position);
            startActivity(i);
        });

    }
    public void goBack(View view) {
        super.onBackPressed();
    }
}