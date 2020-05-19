package com.example.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGameOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, SubGameActivity.class);
//        intent.putExtra("currPlayer", 'x');
        startActivity(intent);
    }

    public void loadGameOnClick(View view) {

    }
}
