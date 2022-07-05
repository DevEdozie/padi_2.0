package com.creeds_code.padi_20;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.creeds_code.padi_20.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    ActivityRegisterBinding binding;
    FirebaseAuth mAuth;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        count = 0;
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(count < 1){
                   registerUser();
                   count++;
               }else if(count > 0){
                   Toast.makeText(RegisterActivity.this, "Please wait.......", Toast.LENGTH_LONG).show();
               }

            }
        });

        binding.loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

    }

    private void registerUser(){
        String email = binding.regEmail.getText().toString();
        String password = binding.regPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            binding.regEmail.setError("Email cannot be empty!");
            binding.regEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            binding.regPassword.setError("Password cannot be empty!");
            binding.regPassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    count = 0;
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this,"Registration Error" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

//    private void setVisibity() {
//        if (binding.registerLayout.getVisibility() == View.VISIBLE) {
//            binding.registerLayout.setVisibility(View.GONE);
//        } else {
//            binding.registerLayout.setVisibility(View.VISIBLE);
//        }
//    }
//
//    private void makeVisible(){
//        binding.registerLayout.setVisibility(View.VISIBLE);
//    }
//
//    private void makeInvisible(){
//        binding.registerLayout.setVisibility(View.GONE);
//    }

}