package com.example.bookworld.bookdata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookworld.R;
import com.example.bookworld.bookdata.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.titleTextView.setText(book.getTitle());

        // Load thumbnail image using Picasso from Firebase Storage URL
        Picasso.get()
                .load(book.getThumbnailUrl()) // Assuming getThumbnailUrl() returns Firebase Storage URL
                .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
                .error(R.drawable.error_image) // Error image if loading fails
                .into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImageView;
        TextView titleTextView;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.bookThumbnail);
            titleTextView = itemView.findViewById(R.id.bookTitle);
        }
    }
}
