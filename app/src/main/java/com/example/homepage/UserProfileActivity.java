package com.example.homepage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfileActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textView = findViewById(R.id.textview);
        textView.setText("Welcome to the User Profile!");
    }
}
