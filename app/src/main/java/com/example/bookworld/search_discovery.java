package com.example.bookworld;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

public class search_discovery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_discovery);

        // Initialize layouts and button
        LinearLayout homeLayout = findViewById(R.id.homelayout);
        LinearLayout myBooksLayout = findViewById(R.id.mybookslayout);
        LinearLayout moreLayout = findViewById(R.id.morelayout);
        ImageView threeDotButton = findViewById(R.id.three_dotButton);
        ImageView backButton = findViewById(R.id.backButton);
        LinearLayout ArtLayout = findViewById(R.id.art);
        LinearLayout technologyLayout = findViewById(R.id.technologyLayout);
        LinearLayout businessLayout = findViewById(R.id.businessLayout);
        LinearLayout animationLayout = findViewById(R.id.animationLayout);
        LinearLayout healthLayout = findViewById(R.id.health_sciencesLayout);
        LinearLayout comicsLayout = findViewById(R.id.comicsLayout);
        LinearLayout fantasyLayout = findViewById(R.id.fantasyLayout);
        LinearLayout historyLayout = findViewById(R.id.historyLayout);
        LinearLayout nonfictionLayout = findViewById(R.id.non_fiction);
        LinearLayout fictionLayout = findViewById(R.id.fictionLayout);

        // Set onClick listeners
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, Home.class);
                startActivity(intent);
            }
        });

        myBooksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, MyBooks.class);
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

        threeDotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, three_dots.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to go back to the previous one
                finish();
            }
        });

        ArtLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, Art.class);
                startActivity(intent);
            }
        });

        technologyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, Technology.class);
                startActivity(intent);
            }
        });

        businessLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, Business.class);
                startActivity(intent);
            }
        });

        animationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, animation.class);
                startActivity(intent);
            }
        });

        healthLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, HealthSciences.class);
                startActivity(intent);
            }
        });

        comicsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, comics.class);
                startActivity(intent);
            }
        });

        fantasyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, fantasy.class);
                startActivity(intent);
            }
        });

        historyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, History.class);
                startActivity(intent);
            }
        });
        nonfictionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, Non_Fiction.class);
                startActivity(intent);
            }
        });
        fictionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_discovery.this, Fiction.class);
                startActivity(intent);
            }
        });
    }
}
