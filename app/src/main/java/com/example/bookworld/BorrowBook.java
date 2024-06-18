package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookworld.bookdata.Book;
import com.example.bookworld.bookdata.BookAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BorrowBook extends AppCompatActivity {

    private EditText etSearchBook;
    private TextView tvBookList;
    private Button btnSearch;
    private RecyclerView recyclerSearchedBooks;
    private FirebaseFirestore db;
    private BookAdapter adapter;
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);


        // Initialize layouts and button
        LinearLayout homeLayout = findViewById(R.id.homelayout);
        LinearLayout myBooksLayout = findViewById(R.id.mybookslayout);
        LinearLayout searchLayout = findViewById(R.id.searchbutton);
        LinearLayout moreLayout = findViewById(R.id.morelayout);
        ImageView threeDotButton = findViewById(R.id.threeDotButton);
        ImageView backButton = findViewById(R.id.backButton);
        // Ensure this ID matches your XML

        // Set onClick listeners
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BorrowBook.this, BorrowBook.class);
                startActivity(intent);
            }
        });

        myBooksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BorrowBook.this, MyBooks.class);
                startActivity(intent);
            }
        });

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BorrowBook.this, search_discovery.class);
                startActivity(intent);
            }
        });

        // Set onClick listener for the "More" button to show pop-up window
        // Set onClick listener for the "More" button to show pop-up window
        moreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BorrowBook.this, More.class);
                startActivity(intent);
            }
        });

        threeDotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BorrowBook.this, three_dots.class);
                startActivity(intent);
            }
        });


        // Set onClick listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the current activity
            }
        });



        etSearchBook = findViewById(R.id.etSearchBook);
        Button btnBorrow = findViewById(R.id.btnBorrow);
        btnSearch = findViewById(R.id.btnSearch);
        recyclerSearchedBooks = findViewById(R.id.recyclerSearchedBooks);
        db = FirebaseFirestore.getInstance();

        // Initialize RecyclerView and adapter
        bookList = new ArrayList<>();
        adapter = new BookAdapter(bookList);
        recyclerSearchedBooks.setLayoutManager(new LinearLayoutManager(this));
        recyclerSearchedBooks.setAdapter(adapter);

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
        db.collection("books")
                .whereEqualTo("title", query)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        bookList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String thumbnailUrl = document.getString("thumbnailUrl");
                            String title = document.getString("title");

                            // Create a Book object and add it to the list
                            Book book = new Book(thumbnailUrl, title);
                            bookList.add(book);
                        }
                        // Notify the adapter that the data set has changed
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), "Book not found Haha :(", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void borrowBook() {
        // This is a placeholder for the actual borrow functionality
        tvBookList.setText("You have borrowed 'Example Book'.");
    }
}