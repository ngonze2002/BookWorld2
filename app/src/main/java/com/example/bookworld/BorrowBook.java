package com.example.bookworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button btnBorrow, btnSearch;
    private RecyclerView recyclerSearchedBooks;
    private FirebaseFirestore db;
    private BookAdapter adapter;
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);

        etSearchBook = findViewById(R.id.etSearchBook);
        btnBorrow = findViewById(R.id.btnBorrow);
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