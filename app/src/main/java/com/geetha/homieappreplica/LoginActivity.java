package com.geetha.homieappreplica;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "Login Activity :";

    private EditText userNameET;
    private EditText passwordET;
    private Button userSignInBT;
    private TextView createAccountTV;
    private FirebaseAuth mAuth;
    private TextView forgotPasswordTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.loginscreen);
        userNameET = findViewById(R.id.userNameET);
        passwordET = findViewById(R.id.passwordET);
        userSignInBT = findViewById(R.id.userSignInBT);
        createAccountTV = findViewById(R.id.createAccountTV);
        forgotPasswordTV = findViewById(R.id.forgotPasswordTV);
        userSignInBT.setOnClickListener(this);
        createAccountTV.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressBar();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            setWelcomeScreen();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        hideProgressBar();
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        if (mAuth.getCurrentUser() != null) {
            setWelcomeScreen();
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = userNameET.getText().toString();
        if (TextUtils.isEmpty(email)) {
            userNameET.setError("Required.");
            valid = false;
        } else {
            userNameET.setError(null);
        }

        String password = passwordET.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordET.setError("Required.");
            valid = false;
        } else {
            passwordET.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        hideProgressBar();
        if (user == null) {
            findViewById(R.id.userSignInBT).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.userSignInBT).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userSignInBT:
                String email = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                signIn(email, password);
                break;
            case R.id.createAccountTV:
                Intent createAccount = new Intent(this, CreateAccountActivity.class);
                startActivity(createAccount);
                break;
            case R.id.forgotPasswordTV:
                Toast.makeText(this, "Create a new password", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public void setWelcomeScreen() {
        Intent welcomeScreen = new Intent(this, WelcomeScreen.class);
        welcomeScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(welcomeScreen);
    }
}
