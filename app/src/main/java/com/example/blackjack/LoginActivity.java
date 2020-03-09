package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        Bundle bundle = getIntent().getExtras();
        try {
            String user ="" + bundle.getString("username");
            Log.i("testeLogin",user);
            String password = "" + bundle.getString("password");
            String email = "" + bundle.getString("email");
            String name = "" + bundle.getString("name");
            if(user.equals(usernameText.getText().toString())){
                if(password.equals(passwordText.getText().toString())){
                    Log.i("teste1","PQ");
                    Intent intent = new Intent(this, BlackjackActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else
                    Toast.makeText(this,"Password Incorrect",Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(this,"Username Incorrect",Toast.LENGTH_LONG).show();

        }catch (NullPointerException e){
            Toast.makeText(this,"Authentication Failed",Toast.LENGTH_LONG).show();
             bundle = getIntent().getExtras();

        }
    }

}
