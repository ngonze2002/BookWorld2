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

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize layouts and button
        LinearLayout homeLayout = findViewById(R.id.homelayout);
        LinearLayout myBooksLayout = findViewById(R.id.mybookslayout);
        LinearLayout searchLayout = findViewById(R.id.searchbutton);
        LinearLayout moreLayout = findViewById(R.id.morelayout);
        ImageView threeDotButton = findViewById(R.id.three_dotButton); // Ensure this ID matches your XML

        // Set onClick listeners
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Home.class);
                startActivity(intent);
            }
        });

        myBooksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MyBooks.class);
                startActivity(intent);
            }
        });

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, search_discovery.class);
                startActivity(intent);
            }
        });

        // Set onClick listener for the "More" button to show pop-up window
        // Set onClick listener for the "More" button to show pop-up window
        moreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, More.class);
                startActivity(intent);
            }
        });

        threeDotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, three_dots.class);
                startActivity(intent);
            }
        });
    }
}
