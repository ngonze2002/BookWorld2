package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Password_recovery extends AppCompatActivity {

    private EditText emailEditText;
    private Button recoverPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        emailEditText = findViewById(R.id.EmailText);
        recoverPasswordButton = findViewById(R.id.buttonpass);

        recoverPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(Password_recovery.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                } else {
                    sendOtpCode(email);
                }
            }
        });
    }

    private void sendOtpCode(String email) {
        // Generate OTP code using regex
        String otpCode = generateOtpCode();
        Log.d("PasswordRecoveryActivity", "OTP Code: " + otpCode); // Log the OTP for debugging purposes

        // Normally, you would send the OTP code to the user's email address using an email service

        // Navigate to RecoveryCodeActivity
        Intent intent = new Intent(Password_recovery.this, Recovery_code.class);
        intent.putExtra("email", email);
        intent.putExtra("otpCode", otpCode);
        startActivity(intent);
    }

    private String generateOtpCode() {
        // Generating a 6-digit OTP
        int otpLength = 6;
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            int digit = random.nextInt(10); // Generate a digit between 0 and 9
            otp.append(digit);
        }
        return otp.toString();
    }
}
