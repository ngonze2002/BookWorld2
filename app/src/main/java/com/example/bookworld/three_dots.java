package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class three_dots extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_dots);

        LinearLayout accountLayout = findViewById(R.id.accountLayout);
        LinearLayout notificationsLayout = findViewById(R.id.NotificationsLayout);
        LinearLayout logoutLayout = findViewById(R.id.LogoutLayout);
        LinearLayout deleteLayout = findViewById(R.id.DeleteLayout);
        ImageView backButton = findViewById(R.id.backButton);

        accountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(three_dots.this, Account_Settings.class);
                startActivity(intent);
            }
        });

        notificationsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(three_dots.this, Notifications.class);
                startActivity(intent);
            }
        });

        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout logic here
                // For now, just navigating to the login screen
                Intent intent = new Intent(three_dots.this, login.class);
                startActivity(intent);
            }
        });

        deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(three_dots.this, sign_in.class);
                startActivity(intent);
            }
        });

        // Add onClick listener for back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(three_dots.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
