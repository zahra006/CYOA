package com.mobile.cyoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.cyoa.Fragments.AccountFragment;

public class EditAccountActivity extends AppCompatActivity {

    private EditText etFirst, etLast, etEmail;
    private Button btnSaveEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        init();
    }

    private void init() {
        etFirst = findViewById(R.id.etFirstNameProf);
        etLast = findViewById(R.id.etLastNameProf);
        etEmail = findViewById(R.id.etEmailProf);
        btnSaveEdit = findViewById(R.id.btnSave);
        editUser();
    }

    private void editUser() {
        btnSaveEdit.setOnClickListener(v -> {
            //POST JSON
            //Update JSON


            //back to profile
            startActivity(new Intent(EditAccountActivity.this, AccountFragment.class));
        });
    }
}