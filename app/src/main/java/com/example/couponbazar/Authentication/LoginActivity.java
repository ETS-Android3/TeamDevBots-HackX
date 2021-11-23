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

import com.example.couponbazar.HomeActivity;
import com.example.couponbazar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button login,signup;
    private EditText editpassword,editemail;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login);
        editemail=findViewById(R.id.mailid);
        editpassword=findViewById(R.id.pass);
        progressBar=findViewById(R.id.progressBar2);
        mAuth=FirebaseAuth.getInstance();
        signup=findViewById(R.id.signup2);
        progressBar.setVisibility(View.GONE);
        Intent ii =getIntent();
        Bundle b=ii.getExtras();
        if(b!=null){
            String a1=(String)b.get("keyEmail");
            String a2=(String)b.get("keyPass");
            editemail.setText(a1);
            editpassword.setText(a2);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
    }

    private void userLogin() {
        String email=editemail.getText().toString();
        String password=editpassword.getText().toString();
        if(email.isEmpty()){
            editemail.setError("Email is required!");
            editemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editemail.setError("Please enter a valid email!");
            editemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editpassword.setError("Password is required!");
            editpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editpassword.setError("Password should be of min 6 characters!");
            editpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "User Verified!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Intent i=new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}