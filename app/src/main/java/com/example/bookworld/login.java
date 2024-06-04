package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.firebase.auth.AuthResult;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import android.widget.CompoundButton;
import android.text.method.PasswordTransformationMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView signUpTextView, googleSignInTextView, forgotPasswordTextView;
    private CheckBox showPasswordCheckBox;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.TextEmail);
        passwordEditText = findViewById(R.id.TextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        signUpTextView = findViewById(R.id.CreateAccount);
        googleSignInTextView = findViewById(R.id.google_text);
        showPasswordCheckBox = findViewById(R.id.checkBoxShowPassword);
        forgotPasswordTextView = findViewById(R.id.ForgotPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    signInWithEmailPassword();
                }
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, sign_in.class);
                startActivity(intent);
                finish();
            }
        });

        googleSignInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });

        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show password
                    passwordEditText.setTransformationMethod(null);
                } else {
                    // Hide password
                    passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
                }
                // Move cursor to the end of the text
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to password recovery screen
                Intent intent = new Intent(login.this, Password_recovery.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateFields() {
        boolean isValid = true;
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email address is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid email format");
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            isValid = false;
        }

        return isValid;
    }

    private void signInWithEmailPassword() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(login.this, "Login successful.",
                                    Toast.LENGTH_SHORT).show();
                            // Proceed to next activity
                        } else {
                            // If sign in fails, display a message to the user.
                            if (task.getException() != null) {
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                String errorCode = e.getErrorCode();
                                if (errorCode.equals("ERROR_USER_NOT_FOUND")) {
                                    // User does not exist
                                    Toast.makeText(login.this, "User does not exist. Please sign up.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(login.this, sign_in.class);
                                    startActivity(intent);
                                    finish();
                                } else if (errorCode.equals("ERROR_WRONG_PASSWORD")) {
                                    // Wrong password
                                    passwordEditText.setError("Wrong password");
                                } else {
                                    // Other errors
                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
    }

    private void signInWithGoogle() {
        // Implement Google Sign-In
    }
}
