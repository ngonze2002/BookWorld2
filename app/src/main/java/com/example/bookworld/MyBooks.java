package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.bookworld.bookdata.Book;
import com.example.bookworld.bookdata.BookAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyBooks extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> bookList;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.recyclerViewBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bookList = new ArrayList<>();
        adapter = new BookAdapter(bookList);
        recyclerView.setAdapter(adapter);

        // Retrieve books from Firestore
        retrieveBooks();

        // Button to move to AddBookActivity
        Button addBookButton = findViewById(R.id.addBookbutton);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyBooks.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }

    private void retrieveBooks() {
        db.collection("books")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String thumbnailUrl = document.getString("bookthumbnail");
                                String title = document.getString("booktitle");

                                // Create a Book object and add it to the list
                                Book book = new Book(thumbnailUrl, title);
                                bookList.add(book);
                            }
                            // Notify the adapter that the data set has changed
                            adapter.notifyDataSetChanged();
                        } else {
                            // Handle errors
                        }
                    }
                });
    }
}
