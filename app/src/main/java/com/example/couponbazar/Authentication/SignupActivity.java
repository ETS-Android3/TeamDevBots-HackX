package com.example.couponbazar.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.couponbazar.Model.User;
import com.example.couponbazar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editName,editPhone,editMail,editPass;
    private Button register,login;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        login=findViewById(R.id.login2);
        register=findViewById(R.id.signup2);
        progressBar=findViewById(R.id.progressBar);
        editName=findViewById(R.id.name);
        editPhone=findViewById(R.id.age);
        editMail=findViewById(R.id.email);
        editPass=findViewById(R.id.pass2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        progressBar.setVisibility(View.GONE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        String email=editMail.getText().toString().trim();
        String fullName=editName.getText().toString().trim();
        String phoneNumber=editPhone.getText().toString().trim();
        String password=editPass.getText().toString().trim();
        if(fullName.isEmpty()){
            editName.setError("Name is Required!");
            editName.requestFocus();
            return;
        }
        if(phoneNumber.isEmpty()){
            editPhone.setError("Phone number is required!");
            editPhone.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editMail.setError("Email ID is required!");
            editMail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editPass.setError("Password is required!");
            editPass.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editMail.setError("Please provide valid email!");
            editMail.requestFocus();
            return;
        }
        if(password.length()<6){
            editPass.setError("Password should be of min 6 characters!");
            editPass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User (fullName,phoneNumber,email);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignupActivity.this,"User has been registered successfully!",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(SignupActivity.this,LoginActivity.class);
                                i.putExtra("keyEmail",email);
                                i.putExtra("keyPass",password);
                                startActivity(i);
                                progressBar.setVisibility(View.GONE);



                            }else{
                                Toast.makeText(SignupActivity.this, "Failed to register! Please Try Again", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

                }
            }
        });


    }
}