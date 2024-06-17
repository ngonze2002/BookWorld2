package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class sign_in extends AppCompatActivity {

    private EditText emailEditText, usernameEditText, passwordEditText, confirmPasswordEditText;
    private Button signUpButton;
    private TextView loginTextView;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("users");

        emailEditText = findViewById(R.id.Email);
        usernameEditText = findViewById(R.id.Username);
        passwordEditText = findViewById(R.id.Password);
        confirmPasswordEditText = findViewById(R.id.ConPassword);
        signUpButton = findViewById(R.id.buttonLogin);
        loginTextView = findViewById(R.id.CreateAccount);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    signUp();
                }
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_in.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validateFields() {
        boolean isValid = true;
        String email = emailEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email address is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid email format");
            isValid = false;
        }

        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError("Username is required");
            isValid = false;
        } else if (username.length() < 4 || username.length() > 10) {
            usernameEditText.setError("Username must be between 4 and 10 characters");
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordEditText.setError("Confirm password is required");
            isValid = false;
        } else if (!confirmPassword.equals(password)) {
            confirmPasswordEditText.setError("Passwords do not match");
            isValid = false;
        }

        return isValid;
    }

    private void signUp() {
        final String email = emailEditText.getText().toString().trim();
        final String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success, save user details to Firestore and Realtime Database
                            saveUserDetails(email, username);
                        } else {
                            // If sign up fails, display a message to the user.
                            Toast.makeText(sign_in.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserDetails(String email, String username) {
        String userId = mAuth.getCurrentUser().getUid();
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("username", username);

        // Save to Firestore
        db.collection("users").document(userId)
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(sign_in.this, "User details saved.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(sign_in.this, "Failed to save user details to Firestore.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // Save to Realtime Database
        dbRef.child(userId).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(sign_in.this, "User details saved to Realtime Database.", Toast.LENGTH_SHORT).show();
                            // Redirect to home activity after saving user details
                            Intent intent = new Intent(sign_in.this, login.class);
                            startActivity(intent);
                            finish(); // Optional, to close the sign_in activity
                        } else {
                            Toast.makeText(sign_in.this, "Failed to save user details to Realtime Database.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
