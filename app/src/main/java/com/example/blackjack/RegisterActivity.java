package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton;
    EditText usernameText;
    EditText passwordText;
    EditText emailText;
    EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        registerButton = findViewById(R.id.registerBtn);
        usernameText = findViewById(R.id.username);
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        nameText = findViewById(R.id.name);

        usernameText.setText("th");
        emailText.setText("th@gmail.com");
        passwordText.setText("th");
        nameText.setText("Thomas");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
                SharedPreferences.Editor myEditor = sharedPreferences.edit();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();
            String email = emailText.getText().toString();
            String name = nameText.getText().toString();
            boolean flag = true;
            if (username.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Username Needed", Toast.LENGTH_SHORT).show();
                usernameText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.redborder, null));
                flag = false;
            } else
                myEditor.putString("username",usernameText.getText().toString());
            if (password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Password Needed", Toast.LENGTH_SHORT).show();
                passwordText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.redborder, null));
                flag = false;
            } else
                myEditor.putString("password",passwordText.getText().toString());
            if (email.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Email Needed", Toast.LENGTH_SHORT).show();
                emailText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.redborder, null));
                flag = false;
            } else
                myEditor.putString("email", emailText.getText().toString());
            if (name.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Name Needed", Toast.LENGTH_SHORT).show();
                nameText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.redborder, null));
                flag = false;
            } else
                myEditor.putString("name",nameText.getText().toString());
            if (flag) {
                myEditor.putString("name",name);
                myEditor.putString("password",password);
                myEditor.putString("email",email);
                myEditor.putString("username",username);
                myEditor.putInt("win",0);
                myEditor.putInt("lose",0);
                myEditor.commit();
                startActivity(intent);
            }
            }
        });

    }
}



