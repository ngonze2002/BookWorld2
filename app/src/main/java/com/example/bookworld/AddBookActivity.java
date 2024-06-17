package com.example.bookworld;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class AddBookActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_PDF_REQUEST = 2;

    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    private Uri thumbnailUri;
    private Uri pdfUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("books"); // Reference to Firebase Storage path "books"

        Button selectThumbnailButton = findViewById(R.id.thumbnail_button);
        selectThumbnailButton.setOnClickListener(v -> openImagePicker());

        Button selectPdfButton = findViewById(R.id.book_file_button);
        selectPdfButton.setOnClickListener(v -> openPdfPicker());

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> uploadData());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Thumbnail Image"), PICK_IMAGE_REQUEST);
    }

    private void openPdfPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent, "Select PDF Book File"), PICK_PDF_REQUEST);
    }

    private void uploadData() {
        String title = ((EditText) findViewById(R.id.book_title)).getText().toString().trim();
        String description = ((EditText) findViewById(R.id.book_desc)).getText().toString().trim();

        if (title.isEmpty() || description.isEmpty() || thumbnailUri == null || pdfUri == null) {
            Toast.makeText(this, "Please fill in all fields and select both thumbnail image and PDF file", Toast.LENGTH_SHORT).show();
            return;
        }

        // Upload thumbnail image and book PDF to Firebase Storage
        uploadThumbnailToFirebase(thumbnailUri, title);
        uploadPdfToFirebase(pdfUri, title);
    }

    private void uploadThumbnailToFirebase(Uri thumbnailUri, String title) {
        StorageReference thumbnailRef = storageRef.child(title + "_thumbnail.jpg");
        UploadTask uploadTask = thumbnailRef.putFile(thumbnailUri);

        uploadTask.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                thumbnailRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String thumbnailUrl = uri.toString();
                    saveBookDetails(title, thumbnailUrl); // Save book details to Firestore
                });
            } else {
                Toast.makeText(AddBookActivity.this, "Failed to upload thumbnail image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadPdfToFirebase(Uri pdfUri, String title) {
        StorageReference pdfRef = storageRef.child(title + ".pdf");
        UploadTask uploadTask = pdfRef.putFile(pdfUri);

        uploadTask.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                pdfRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String pdfUrl = uri.toString();
                    // Optionally save PDF URL to Firestore or handle as needed
                });
            } else {
                Toast.makeText(AddBookActivity.this, "Failed to upload PDF", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveBookDetails(String title, String thumbnailUrl) {
        String description = ((EditText) findViewById(R.id.book_desc)).getText().toString().trim();

        Map<String, Object> book = new HashMap<>();
        book.put("title", title);
        book.put("description", description);
        book.put("thumbnailUrl", thumbnailUrl); // URL of uploaded thumbnail image

        db.collection("books")
                .document(title) // Use the book title as the document ID
                .set(book)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(AddBookActivity.this, "Book added successfully", Toast.LENGTH_SHORT).show();
                        finish(); // Close activity after successful upload
                    } else {
                        Toast.makeText(AddBookActivity.this, "Failed to add book", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                thumbnailUri = data.getData();
                // Example: Display the selected image in an ImageView
                // ImageView imageView = findViewById(R.id.imageView);
                // imageView.setImageURI(thumbnailUri);
            } else if (requestCode == PICK_PDF_REQUEST) {
                pdfUri = data.getData();
                // Example: Display the selected PDF file name
                // TextView pdfTextView = findViewById(R.id.pdfTextView);
                // pdfTextView.setText(pdfUri.getLastPathSegment());
            }
        }
    }
}

