package com.example.medlo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

     private EditText Password,Email;
     private TextView register,forgotPassword,info;
     private Button loginButton;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private int counter=5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Password = (EditText) findViewById(R.id.etEmail);
        Email = (EditText) findViewById(R.id.etPassword);
        register = (TextView) findViewById(R.id.tvRegister);
        loginButton = (Button) findViewById(R.id.btButton);
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        info = (TextView) findViewById(R.id.tvInfo);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Email.getText().toString().equals("") || Password.getText().toString().equals("")) {
                    noInput();
                } else {
                    validate(Email.getText().toString(), Password.getText().toString());
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

    }

        private void noInput () {

            Toast.makeText(this, "Please Enter Credentials", Toast.LENGTH_LONG).show();
        }

        private void validate (String userEmail, String userPassword)
        {
            progressDialog.setMessage("Please Wait");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, SecondActivity.class));

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                        counter--;
                        info.setText("No. of attempts remaining: " + counter);
                        if (counter == 0) {
                            loginButton.setEnabled(false);
                        }
                    }

                }
            });
        }

        @Override
        public void onBackPressed () {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to Exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            moveTaskToBack(true);
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }