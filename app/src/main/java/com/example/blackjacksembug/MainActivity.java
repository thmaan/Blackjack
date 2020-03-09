package com.example.blackjacksembug;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
    public void onClickLogin(View v){
        Bundle bundle = getIntent().getExtras();
        Intent intent = new Intent(this, LoginActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        Log.i("Aula02","Main Activity onStart");
        super.onStart();
    }
    public void onClickRegister(View v){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPostResume() {
        Log.i("Aula02","Main Activity onResume");
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        Log.i("Aula02","Main Activity onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Aula02","Main Activity onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Aula02","Main Activity onDestroy");
        super.onDestroy();
    }
}
