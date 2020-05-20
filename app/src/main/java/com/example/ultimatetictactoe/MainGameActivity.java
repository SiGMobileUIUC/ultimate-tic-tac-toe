package com.example.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainGameActivity extends AppCompatActivity {

    private MainGame game;
    private TextView title;
    private Button[][] buttons = new Button[game.BOARD_SIZE][game.BOARD_SIZE];
    private char currPlayer = 'x';

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        title = (TextView)findViewById(R.id.title);
        title.setText(currPlayer + "'s Turn");
    }

    public void onClick(View v) {
        String id = getResources().getResourceEntryName(v.getId());
        int x = Integer.parseInt(id.substring("btnImg".length(), "btnImg".length() + 1));
        int y = Integer.parseInt(id.substring("btnImg".length() + 1, "btnImg".length() + 2));
        Log.i("click", " (" + x + ", " + y + ")");
    }
}
