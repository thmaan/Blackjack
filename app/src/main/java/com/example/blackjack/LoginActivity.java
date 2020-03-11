package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ImageView backButton;
    EditText usernameText;
    EditText passwordText;
    Button loginBtn;
    TextView helpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backButton = findViewById(R.id.backBtn);
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        helpText = findViewById(R.id.help);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void onShowPassword(View v){
        boolean checked = ((CheckBox) v).isChecked();

        if (v.getId() == R.id.checkBox) {
            if (checked)
                passwordText.setTransformationMethod(null);
            else
                passwordText.setTransformationMethod(new PasswordTransformationMethod());
        }
    }
    public void onClickLogin(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("users", MODE_APPEND);
        try {
            String user ="" + sh.getString("username","");
            String password = "" + sh.getString("password","");
            String email = "" + sh.getString("email","");
            String name = "" + sh.getString("name","");
            SharedPreferences.Editor myEditor = sharedPreferences.edit();
            myEditor.putString("username",user);
            myEditor.putString("password",password);
            myEditor.putString("email",email);
            myEditor.putString("name",name);
            if(user.equals(usernameText.getText().toString())){
                if(password.equals(passwordText.getText().toString())){
                    myEditor.commit();
                    Intent intent = new Intent(this, BlackjackActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(this,"Password Incorrect",Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(this,"Username Incorrect",Toast.LENGTH_LONG).show();

        }catch (NullPointerException e){
            Toast.makeText(this,"Authentication Failed",Toast.LENGTH_LONG).show();
        }
    }

}
