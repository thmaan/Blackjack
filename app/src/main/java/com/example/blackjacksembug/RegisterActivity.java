package com.example.blackjacksembug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
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
        getSupportActionBar().hide();

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
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String email = emailText.getText().toString();
                String name = nameText.getText().toString();
                Bundle bundle = new Bundle();

                boolean flag = true;
                if (username.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Username Needed", Toast.LENGTH_SHORT).show();
                    usernameText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.redborder, null));
                    flag = false;
                } else
                    bundle.putString("username", usernameText.getText().toString());

                if (password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Password Needed", Toast.LENGTH_SHORT).show();
                    passwordText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.redborder, null));
                    flag = false;
                } else
                    bundle.putString("password", passwordText.getText().toString());
                if (email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Email Needed", Toast.LENGTH_SHORT).show();
                    emailText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.redborder, null));
                    flag = false;
                } else
                    bundle.putString("email", emailText.getText().toString());
                if (name.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Name Needed", Toast.LENGTH_SHORT).show();
                    nameText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.redborder, null));
                    flag = false;
                } else
                    bundle.putString("name", usernameText.getText().toString());
                if (flag) {
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}



