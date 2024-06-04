package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Recovery_code extends AppCompatActivity {

    private EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4;
    private Button verifyButton;
    private String receivedOtpCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_code);

        otpDigit1 = findViewById(R.id.otpDigit1);
        otpDigit2 = findViewById(R.id.otpDigit2);
        otpDigit3 = findViewById(R.id.otpDigit3);
        otpDigit4 = findViewById(R.id.otpDigit4);
        verifyButton = findViewById(R.id.buttonVerify);

        // Get the OTP code from the Intent
        receivedOtpCode = getIntent().getStringExtra("otpCode");

        // Add a TextWatcher to each EditText
        otpDigit1.addTextChangedListener(new GenericTextWatcher(otpDigit1, otpDigit2));
        otpDigit2.addTextChangedListener(new GenericTextWatcher(otpDigit2, otpDigit3));
        otpDigit3.addTextChangedListener(new GenericTextWatcher(otpDigit3, otpDigit4));

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOtp = otpDigit1.getText().toString() + otpDigit2.getText().toString() +
                        otpDigit3.getText().toString() + otpDigit4.getText().toString();

                if (enteredOtp.equals(receivedOtpCode)) {
                    // OTP verified, move to ResetPasswordActivity
                    Intent intent = new Intent(Recovery_code.this, reset_password.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Recovery_code.this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class GenericTextWatcher implements TextWatcher {
        private View currentView, nextView;

        private GenericTextWatcher(View currentView, View nextView) {
            this.currentView = currentView;
            this.nextView = nextView;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Move focus to the next EditText when a digit is entered
            if (s.length() == 1) {
                nextView.requestFocus();
            }
        }
    }
}
