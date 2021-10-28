package com.example.clash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class newlogin extends AppCompatActivity {

    TextView btn;
    EditText username001,email002,password003,confirmpassword004;
    Button registerbutton005;
    private FirebaseAuth mAuth;
    private ProgressDialog mloadingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_newlogin);


        btn=findViewById(R.id.alreadyac006);
        username001=findViewById(R.id.username001);
        email002=findViewById(R.id.email002);
        password003=findViewById(R.id.password003);
        confirmpassword004=findViewById(R.id.confirmpassword004);
        registerbutton005=findViewById(R.id.registerbutton005);
        mAuth=FirebaseAuth.getInstance();
        mloadingbar=new ProgressDialog(newlogin.this);

        registerbutton005.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrededentials();

            }
        });






        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(newlogin.this,dashboard.class));
            }
        });
    }



    private void checkCrededentials() {
        String username = username001.getText().toString();
        String email=email002.getText().toString();
        String password=password003.getText().toString();
        String confirmpassword=confirmpassword004.getText().toString();

        if (username.isEmpty()  || username.length()<7)
        {
            showError (username001,"Your username is not valid!");
        }
        else if (email.isEmpty()  ||  !email.contains("@") )
        {
            showError(email002,"Email is not valid");
        }
        else if (password.isEmpty() || password.length()<7)
        {
            showError(password003,"Password must be 7 characters");
        }
        else if (confirmpassword.isEmpty() || !confirmpassword.equals(password))
        {
            showError(confirmpassword004,"Please enter same Password");
        }
        else
        {
            mloadingbar.setTitle("Registration");
            mloadingbar.setMessage("Please wait,while check your credentials");
            mloadingbar.setCanceledOnTouchOutside(false);
            mloadingbar.show();


            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(newlogin.this, "Successfully Registration", Toast.LENGTH_SHORT).show();

                        mloadingbar.dismiss();
                        Intent intent=new Intent(newlogin.this,mainpage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(newlogin.this, task.getException().toString() , Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }
    }

    private void showError(EditText input,String s) {
        input.setError(s);
        input.requestFocus();


    }
}