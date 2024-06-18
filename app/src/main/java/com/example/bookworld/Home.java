package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookworld.bookdata.Book;
import com.example.bookworld.bookdata.BookAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerTrendingBooks;
    private RecyclerView recyclerNewReleases;
    private FirebaseFirestore db;
    private BookAdapter trendingAdapter;
    private BookAdapter newReleasesAdapter;
    private List<Book> trendingBooksList;
    private List<Book> newReleasesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize layouts and button
        LinearLayout myBooksLayout = findViewById(R.id.mybookslayout);
        LinearLayout searchLayout = findViewById(R.id.searchbutton);
        LinearLayout moreLayout = findViewById(R.id.morelayout);
        ImageView threeDotButton = findViewById(R.id.three_dotButton); // Ensure this ID matches your XML
        recyclerTrendingBooks = findViewById(R.id.recyclerTrendingBooks);
        recyclerNewReleases = findViewById(R.id.recyclerNewReleases);
        db = FirebaseFirestore.getInstance();

        recyclerTrendingBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerNewReleases.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        trendingBooksList = new ArrayList<>();
        newReleasesList = new ArrayList<>();

        trendingAdapter = new BookAdapter(trendingBooksList);
        newReleasesAdapter = new BookAdapter(newReleasesList);

        recyclerTrendingBooks.setAdapter(trendingAdapter);
        recyclerNewReleases.setAdapter(newReleasesAdapter);

        // Retrieve trending books from Firestore
        retrieveTrendingBooks();

        // Retrieve new releases from Firestore
        retrieveNewReleases();

        // Set onClick listeners
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

    private void retrieveTrendingBooks() {
        db.collection("books")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String thumbnailUrl = document.getString("thumbnailUrl");
                                String title = document.getString("title");

                                // Create a Book object and add it to the trending books list
                                Book book = new Book(thumbnailUrl, title);
                                trendingBooksList.add(book);
                            }
                            // Notify the adapter that the data set has changed
                            trendingAdapter.notifyDataSetChanged();
                        } else {
                            // Handle errors
                        }
                    }
                });
    }

    private void retrieveNewReleases() {
        db.collection("books")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String thumbnailUrl = document.getString("thumbnailUrl");
                                String title = document.getString("title");

                                // Create a Book object and add it to the new releases list
                                Book book = new Book(thumbnailUrl, title);
                                newReleasesList.add(book);
                            }
                            // Notify the adapter that the data set has changed
                            newReleasesAdapter.notifyDataSetChanged();
                        } else {
                            // Handle errors
                        }
                    }
                });
    }
}
