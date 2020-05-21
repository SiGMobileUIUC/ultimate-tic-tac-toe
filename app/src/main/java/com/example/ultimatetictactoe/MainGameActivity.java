package com.example.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
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

        // Update label values
        title = (TextView)findViewById(R.id.title);
        title.setText(currPlayer + "'s Turn");

        // If new game, create a new game. Other populate with given intent
        game = getIntent().getExtras() == null ?
                new MainGame() : (MainGame) getIntent().getParcelableExtra("main game");

        // Check if any of the boards are winners

    }

    public void onClick(View v) {
        String id = getResources().getResourceEntryName(v.getId());
        int x = Integer.parseInt(id.substring("btnImg".length(), "btnImg".length() + 1));
        int y = Integer.parseInt(id.substring("btnImg".length() + 1, "btnImg".length() + 2));
        Log.i("click", " (" + x + ", " + y + ")");

        Intent intent = new Intent(this, SubGameActivity.class);
        intent.putExtra("x", x);
        intent.putExtra("y", y);
        intent.putExtra("subGame", (Parcelable) game.getArr()[x][y]);
        intent.putExtra("mainGame", (Parcelable) game);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            // do something on back.
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
