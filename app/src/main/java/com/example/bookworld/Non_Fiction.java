package com.example.bookworld;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;

public class Non_Fiction extends AppCompatActivity {

    private LinearLayout remainingRows1, remainingRows2, fantasyRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_fiction);

        // Initialize LinearLayouts
        LinearLayout homeLayout = findViewById(R.id.homenon);
        LinearLayout myBooksLayout = findViewById(R.id.mybooksnon);
        LinearLayout searchLayout = findViewById(R.id.searchnon);
        LinearLayout moreLayout = findViewById(R.id.morenon);
        ImageView backButton = findViewById(R.id.backButton);

        // Initialize View All Buttons
        Button viewAllButton = findViewById(R.id.view_all_button);
        Button viewAllButton2 = findViewById(R.id.view_all_button2);
        Button viewAllButton1 = findViewById(R.id.view_all_button1);

        // Initialize Hidden Rows
        remainingRows1 = findViewById(R.id.remaining_rows_1);
        remainingRows2 = findViewById(R.id.remaining_rows_2);
        fantasyRemaining = findViewById(R.id.fantasy_remaining);

        // Set onClickListeners for View All Buttons
        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(fantasyRemaining);
            }
        });

        viewAllButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(remainingRows2);
            }
        });

        viewAllButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(remainingRows1);
            }
        });

        // Set onClick listeners
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Non_Fiction.this, Home.class);
                startActivity(intent);
            }
        });

        myBooksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Non_Fiction.this, MyBooks.class);
                startActivity(intent);
            }
        });

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Non_Fiction.this, search_discovery.class);
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
                Intent intent = new Intent(Non_Fiction.this, search_discovery.class);
                startActivity(intent);
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
