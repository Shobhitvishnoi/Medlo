package com.example.medlo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName , userGender ,userPhone ,userFamily_Phone_Number, userEmail, userPassword, userAddress1, userAddress2 ;
    private Button regButton;
    private TextView userLogin;

    private FirebaseAuth firebaseAuth;
    String email,name,gender,phone,phone2,password,address1,address2;

    private FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        setupUIViews();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(RegistrationActivity.this, "Successfully Registered, uploaded", Toast.LENGTH_SHORT).show();

                                finish();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }
    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etName);
        userGender = (EditText)findViewById(R.id.etGender);
        userPhone = (EditText)findViewById(R.id.etPhone_Number);
        userFamily_Phone_Number = (EditText)findViewById(R.id.etFamily_Phone_Number);
        userEmail = (EditText)findViewById(R.id.etEnterEmail);
        userPassword = (EditText)findViewById(R.id.etEnterPassword);
        userAddress1 = (EditText)findViewById(R.id.etAddress1);
        userAddress2 = (EditText)findViewById(R.id.etAddress2);
        regButton = (Button)findViewById(R.id.etRegister);
        userLogin = (TextView)findViewById(R.id.ettext);

    }
    private boolean validate(){
        Boolean result = false;

        name = userName.getText().toString();
        gender= userGender.getText().toString();
        phone= userPhone.getText().toString();
        phone2= userFamily_Phone_Number.getText().toString();
        email= userEmail.getText().toString();
        password= userPassword.getText().toString();
        address1= userAddress1.getText().toString();
        address2= userAddress2.getText().toString();
        if(name.isEmpty() || gender.isEmpty() || phone.isEmpty() || phone2.isEmpty() || email.isEmpty() || password.isEmpty() || address1.isEmpty() || address2.isEmpty() ){
            Toast.makeText(this, "Please Fill all Fields", Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }

        return result;


    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile=new UserProfile(address1, address2, email, phone2, gender, name, password,  phone);
        myRef.setValue(userProfile);
    }
}
