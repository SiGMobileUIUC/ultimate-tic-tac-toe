package com.example.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGameOnClick(View view) {
        Log.i("click", "Starting New Game");
        Intent intent = new Intent(MainActivity.this, MainGameActivity.class);
        startActivity(intent);
    }

    public void loadGameOnClick(View view) {
        Log.i("click", "Loading Previous Game");
    }
}
