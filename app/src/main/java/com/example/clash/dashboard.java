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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class dashboard extends AppCompatActivity {

    private static final int RC_SIGN_IN = 101;
    TextView btn;
    EditText email002,password003;
    Button registerbutton005;
    private FirebaseAuth mAuth;
    ProgressDialog mloadingbar;
    Button googlebutton07;
    GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);


        btn=findViewById(R.id.signup06);
        email002=findViewById(R.id.email002);
        password003=findViewById(R.id.password003);
        registerbutton005=findViewById(R.id.registerbutton005);
        mAuth=FirebaseAuth.getInstance();
        mloadingbar=new ProgressDialog(dashboard.this);
        googlebutton07=findViewById(R.id.googlebutton07);

        registerbutton005.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrededentials();

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,newlogin.class));
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();



        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googlebutton07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(dashboard.this, user.getEmail()+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(dashboard.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Intent intent=new Intent(dashboard.this,mainpage.class);
        startActivity(intent);
    }


    private void checkCrededentials() {

        String email=email002.getText().toString();
        String password=password003.getText().toString();

        if (email.isEmpty()  ||  !email.contains("@") )
        {
            showError(email002,"Email format is not valid");
        }
        else if (password.isEmpty() || password.length()<7)
        {
            showError(password003,"Password must be 7 characters");
        }

        else
        {
            mloadingbar.setTitle("login");
            mloadingbar.setMessage("Please wait,while check your credentials");
            mloadingbar.setCanceledOnTouchOutside(false);


            mloadingbar.show();


            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(dashboard.this, "Successfully Registration", Toast.LENGTH_SHORT).show();

                        mloadingbar.dismiss();
                        Intent intent = new Intent(dashboard.this, mainpage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                       // Toast.makeText(dashboard.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                        Toast.makeText(dashboard.this, "Password or Email Wrong !", Toast.LENGTH_SHORT).show();
                        mloadingbar.dismiss();
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