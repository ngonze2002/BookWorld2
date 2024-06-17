package com.example.bookworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BorrowBook extends AppCompatActivity {

    private EditText etSearchBook;
    private TextView tvBookList;
    private Button btnBorrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);

        etSearchBook = findViewById(R.id.etSearchBook);
        tvBookList = findViewById(R.id.tvBookList);
        btnBorrow = findViewById(R.id.btnBorrow);

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = etSearchBook.getText().toString().trim();
                searchBook(searchQuery);
            }
        });

        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrowBook();
            }
        });
    }

    private void searchBook(String query) {
        // This is a placeholder for actual search functionality
        if (query.equalsIgnoreCase("example book")) {
            tvBookList.setText("Book found! You can borrow 'Example Book'.");
            btnBorrow.setEnabled(true);
        } else {
            tvBookList.setText("Book not available.");
            btnBorrow.setEnabled(false);
        }
    }

    private void borrowBook() {
        // This is a placeholder for the actual borrow functionality
        tvBookList.setText("You have borrowed 'Example Book'.");
    }
}