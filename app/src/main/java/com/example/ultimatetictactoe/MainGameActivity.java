package com.example.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainGameActivity extends AppCompatActivity {

    private MainGame game;
    private TextView title;
    private ImageButton[][] buttons = new ImageButton[MainGame.BOARD_SIZE][MainGame.BOARD_SIZE];
    private char currPlayer;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        //Connect buttons array to Image buttons on screen
        for (int i = 0; i < SubGame.BOARD_SIZE; i++) {
            for (int j = 0; j < SubGame.BOARD_SIZE; j++) {
                String buttonID = "imgBtn" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
            }
        }

        // If new game, create a new game. Other populate with given intent
        game = getIntent().getExtras() == null ?
                new MainGame() : (MainGame) getIntent().getParcelableExtra("main game");

        // Update label values
        title = (TextView)findViewById(R.id.title);
        currPlayer = game.isPlayerOne() ? 'x' : 'o';
        title.setText(currPlayer + "'s Turn");

        // Check if any of the boards are winners
        for (int i = 0; i < SubGame.BOARD_SIZE; i++) {
            for (int j = 0; j < SubGame.BOARD_SIZE; j++) {
                char subWinner = game.getArr()[i][j].getWinner();
                switch (subWinner) {
                    case 'x':
                        buttons[i][j].setImageResource(R.drawable.x);
                        break;

                    case 'o':
                        buttons[i][j].setImageResource(R.drawable.o);
                        break;
                }
            }
        }

        // Game has winner
        game.checkWinner();
        if (game.getWinner() != ' ') {
            Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(game.getWinner()) + " has won the game!", Toast.LENGTH_LONG);
            toast.show();
        }
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
