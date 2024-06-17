package com.example.bookworld;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;

public class fantasy extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy);

        // Initialize LinearLayouts
        LinearLayout homeLayout = findViewById(R.id.homefantasy);
        LinearLayout myBooksLayout = findViewById(R.id.mybooksfantasy);
        LinearLayout searchLayout = findViewById(R.id.searchfantasy);
        LinearLayout moreLayout = findViewById(R.id.morefantasy);
        ImageView backButton = findViewById(R.id.backButton);

        // Set onClick listeners
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fantasy.this, Home.class);
                startActivity(intent);
            }
        });

        myBooksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fantasy.this, MyBooks.class);
                startActivity(intent);
            }
        });

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fantasy.this, search_discovery.class);
                startActivity(intent);
            }
        });

        moreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the layout for the pop-up window
                View popupView = getLayoutInflater().inflate(R.layout.activity_more, null);

                // Create a new pop-up window
                PopupWindow popupWindow = new PopupWindow(popupView,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                // Set background drawable with semi-transparent color to create overlay effect
                Drawable background = new ColorDrawable(Color.BLACK);
                background.setAlpha(150); // Set alpha (transparency) level (0-255)
                popupWindow.setBackgroundDrawable(background);

                // Set focusable and outside touchable to true to allow dismissal of the pop-up window when touched outside
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);

                // Show the pop-up window at the center of the screen
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fantasy.this, search_discovery.class);
                startActivity(intent);
            }
        });
    }
}
