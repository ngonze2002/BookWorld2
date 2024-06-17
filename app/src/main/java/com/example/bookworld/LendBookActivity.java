package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class LendBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend_book);

        findViewById(R.id.btnViewBorrowedBooks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start View Borrowed Books Activity (to be implemented)
                // Intent intent = new Intent(LendBookActivity.this, ViewBorrowedBooksActivity.class);
                // startActivity(intent);
            }
        });

        findViewById(R.id.btnLendBooks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Lend Books Activity (to be implemented)
                // Intent intent = new Intent(LendBookActivity.this, LendBooksActivity.class);
                // startActivity(intent);
            }
        });
    }
}