package com.example.myhanoiver1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhanoiver1.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button btn_login, btn_Signup;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    TextView forgot_pass;
    EditText email, password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        btn_Signup = findViewById(R.id.btn_signup);
        email = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password);
        checkBox = findViewById(R.id.mCheckbox);
        forgot_pass = findViewById(R.id.txtForgotPassword);
        sharedPreferences = getApplication().getSharedPreferences("login",MODE_PRIVATE);

        email.setText(sharedPreferences.getString("taikhoan",""));
        password.setText(sharedPreferences.getString("matkhau",""));
        checkBox.setChecked(sharedPreferences.getBoolean("checked",false));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();
            }
        });
        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
        });

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onClickForgot();}
        });
    }

    private void onClickForgot() {
        String fmail = email.getText().toString().trim();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(fmail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Please check your email", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Email not registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onClickSignUp() {
        String mEmail = email.getText().toString().trim();
        String mPass = password.getText().toString().trim();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(mEmail, mPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    CheckBox(checkBox,mEmail,mPass);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{
                    CheckBox(checkBox,mEmail,mPass);
                    Toast.makeText(LoginActivity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onClickLogin() {

        String mEmail = email.getText().toString().trim();
        String mPass = password.getText().toString().trim();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    CheckBox(checkBox,mEmail,mPass);
                    Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    CheckBox(checkBox,mEmail,mPass);
                    Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void CheckBox(CheckBox checkBox, String username, String password){
        if (checkBox.isChecked()){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("taikhoan",username);
            editor.putString("matkhau", password);
            editor.putBoolean("checked",true);
            editor.commit();
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("taikhoan");
            editor.remove("matkhau");
            editor.remove("checked");
            editor.commit();
        }
    }




}