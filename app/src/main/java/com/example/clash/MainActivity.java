package com.example.clash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    EditText editTextNumber3;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);    //Hide Status Bar
        setContentView(R.layout.activity_main);


        editTextNumber3 = findViewById(R.id.editTextNumber3);
        button6 = findViewById(R.id.button6);

        ProgressBar progressBar = findViewById(R.id.progressBar);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editTextNumber3.getText().toString().trim().isEmpty()) {
                    if ((editTextNumber3.getText().toString().trim()).length() == 10) {

                        progressBar.setVisibility(view.VISIBLE);
                        button6.setVisibility(view.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + editTextNumber3.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                MainActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        button6.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        button6.setVisibility(View.VISIBLE);
                                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                       public void onCodeSent(@NonNull String fillotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        button6.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                        intent.putExtra("mobile", editTextNumber3.getText().toString());
                                        intent.putExtra("fillotp",fillotp);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);

                                    }
                                }
                        );

                    } else {
                        Toast.makeText(MainActivity.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}