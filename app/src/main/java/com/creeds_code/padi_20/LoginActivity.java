package com.creeds_code.padi_20;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.creeds_code.padi_20.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth mAuth;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        count = 0;
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setContentView(R.layout.blank_activity);

                if(count < 1) {
                    loginUser();
                    count++;
                }else if(count > 0){
                    Toast.makeText(LoginActivity.this, "Please wait.......", Toast.LENGTH_LONG).show();
                }
            }
        });
        binding.regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginUser() {
        String email = binding.loginEmail.getText().toString();
        String password = binding.loginPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            binding.loginEmail.setError("Email cannot be empty!");
            binding.loginEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            binding.loginPassword.setError("Password cannot be empty!");
            binding.loginPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //makeVisible();
                    count = 0;
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

//    private void setVisibity() {
//        if (binding.loginLayout.getVisibility() == View.VISIBLE) {
//            binding.loginLayout.setVisibility(View.GONE);
//        } else {
//            binding.loginLayout.setVisibility(View.VISIBLE);
//        }
//    }
//
//    private void makeVisible(){
//        binding.loginLayout.setVisibility(View.VISIBLE);
//    }
//
//    private void makeInvisible(){
//        binding.loginLayout.setVisibility(View.GONE);
//    }

}