package com.example.clash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {
    EditText inputnumber1,inputnumber2,inputnumber3,inputnumber4,inputnumber5,inputnumber6;
    Button button6;
    String getotpback;
    public String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);


         button6 = findViewById(R.id.button6);

        inputnumber1 = findViewById(R.id.inputnumber1);
        inputnumber2 = findViewById(R.id.inputnumber2);
        inputnumber3 = findViewById(R.id.inputnumber3);
        inputnumber4 = findViewById(R.id.inputnumber4);
        inputnumber5 = findViewById(R.id.inputnumber5);
        inputnumber6 = findViewById(R.id.inputnumber6);


        TextView textView9 = findViewById(R.id.textView9);
        number = getIntent().getStringExtra("mobile");
        textView9.setText(

                "+91 " + number

        );

        getotpback = getIntent().getStringExtra("fillotp");

       final ProgressBar progressBar2 = findViewById(R.id.progressBar2);



        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!inputnumber1.getText().toString().trim().isEmpty() && !inputnumber2.getText().toString().trim().isEmpty()  && !inputnumber3.getText().toString().trim().isEmpty()  && !inputnumber4.getText().toString().trim().isEmpty()  && !inputnumber5.getText().toString().trim().isEmpty()  && !inputnumber6.getText().toString().trim().isEmpty()) {
                    String entercodeotp = inputnumber1.getText().toString() +
                            inputnumber2.getText().toString()+
                            inputnumber3.getText().toString()+
                            inputnumber4.getText().toString()+
                            inputnumber5.getText().toString()+
                            inputnumber6.getText().toString();

                    if ( getotpback!=null) {
                        progressBar2.setVisibility(View.VISIBLE);
                        button6.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpback,entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar2.setVisibility(View.VISIBLE);
                                        button6.setVisibility(View.INVISIBLE);
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);

                                        } else {
                                            Toast.makeText(MainActivity2.this, "Enter the correct otp", Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                });

                    }else {
                        Toast.makeText(MainActivity2.this, "Please check internet connection", Toast.LENGTH_SHORT).show();

                    }


//                     Toast.makeText(MainActivity2.this, "otp verify", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity2.this, "please enter all number", Toast.LENGTH_SHORT).show();

                }

            }
        });

        otpmove();

        TextView textView;
        textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        MainActivity2.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newfillotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                               getotpback = newfillotp;
                                Toast.makeText(MainActivity2.this, "Otp send succussfully", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });

    }

    private void otpmove() {
        inputnumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputnumber2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputnumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputnumber3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputnumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputnumber4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputnumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputnumber5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputnumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputnumber6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }
}