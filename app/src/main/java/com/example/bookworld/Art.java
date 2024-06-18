package com.example.bookworld;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

public class Art extends AppCompatActivity {

    private LinearLayout remainingRows1;
    private LinearLayout remainingRows01;
    private LinearLayout animationRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);

        // Initialize LinearLayouts
        LinearLayout homeLayout = findViewById(R.id.homeart);
        LinearLayout myBooksLayout = findViewById(R.id.mybooksart);
        LinearLayout searchLayout = findViewById(R.id.searchart);
        LinearLayout moreLayout = findViewById(R.id.moreart);
        ImageView backButton = findViewById(R.id.backButton);

        // Initialize View All Buttons
        Button viewAllButton1 = findViewById(R.id.view_all_button1);
        Button viewAllButton2 = findViewById(R.id.view_all_button2);
        Button viewAllButton = findViewById(R.id.view_all_button);

        // Initialize Hidden Rows
        remainingRows1 = findViewById(R.id.remaining_rows_1);
        remainingRows01 = findViewById(R.id.remaining_rows_01);
        animationRows = findViewById(R.id.row);


        // Set onClickListeners for View All Buttons
        viewAllButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(remainingRows1);
            }
        });

        viewAllButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(remainingRows01);
            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(animationRows);
            }
        });

        // Set onClick listeners for bottom navigation buttons
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Art.this, Home.class);
                startActivity(intent);
            }
        });

        myBooksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Art.this, MyBooks.class);
                startActivity(intent);
            }
        });

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Art.this, search_discovery.class);
                startActivity(intent);
            }
        });

        // Set onClick listener for the "More" button to show a PopupWindow
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
                ColorDrawable background = new ColorDrawable(Color.BLACK);
                background.setAlpha(150); // Set alpha (transparency) level (0-255)
                popupWindow.setBackgroundDrawable(background);

                // Set focusable and outside touchable to true to allow dismissal of the pop-up window when touched outside
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);

                // Show the pop-up window at the center of the screen
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        });

        // Set onClick listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the current activity
            }
        });
    }

    private void toggleVisibility(LinearLayout hiddenRows) {
        if (hiddenRows.getVisibility() == View.GONE) {
            hiddenRows.setVisibility(View.VISIBLE);
        } else {
            hiddenRows.setVisibility(View.GONE);
        }
    }
}
