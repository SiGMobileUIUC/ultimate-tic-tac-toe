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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SubGameActivity extends AppCompatActivity implements View.OnClickListener {
    private SubGame subGame = new SubGame();
    private MainGame mainGame = new MainGame();
    private int boardx, boardy;
    private ImageButton[][] buttons = new ImageButton[SubGame.BOARD_SIZE][SubGame.BOARD_SIZE];
    private char currPlayer = 'x';
//    private TextView gameStatus = (TextView)findViewById(R.id.gameStatus);


    private TextView title; // Text for screen title
    private TextView gameStatus; // Text for errors and other pop up information.


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_game);

        // get view components
        title = findViewById(R.id.title);
        gameStatus = findViewById(R.id.gameStatus);

        // populate variables with intents
        boardx = getIntent().getIntExtra("x", -1);
        boardy = getIntent().getIntExtra("y", -1);
        subGame = getIntent().getParcelableExtra("subGame");
        mainGame = getIntent().getParcelableExtra("mainGame");
        assert subGame != null;
        assert mainGame != null;
        currPlayer = mainGame.isPlayerOne() ? 'x' : 'o';

        title.setText("Game at (" + boardx +", " + boardy +")"); // Set Title

        // Give buttons IDs and update values from subGame
        for (int i = 0; i < SubGame.BOARD_SIZE; i++) {
            for (int j = 0; j < SubGame.BOARD_SIZE; j++) {
                String ButtonID = "btn" + i + j;
                int resID = getResources().getIdentifier(ButtonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
                switch (subGame.getBoard()[i][j]) {
                    case 'x':
                        buttons[i][j].setImageResource(R.drawable.x);
                        break;

                    case 'o':
                        buttons[i][j].setImageResource(R.drawable.o);
                        break;
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        String id = getResources().getResourceEntryName(v.getId());
        int x = Integer.parseInt(id.substring(3, 4));
        int y = Integer.parseInt(id.substring(4, 5));
        Log.d("click", "spot at: (" + x + ", " + y +")");

        if(mainGame.getWinner() == ' ') {

            if (subGame.playTurn(x, y, currPlayer)) {
                switch (currPlayer) {
                    case 'x':
                        buttons[x][y].setImageResource(R.drawable.x);
                        break;
                    case 'o':
                        buttons[x][y].setImageResource(R.drawable.o);
                        break;
                }
                subGame.getBoard()[x][y] = currPlayer;
                mainGame.getArr()[boardx][boardy] = subGame;
                mainGame.setPlayerOne(!mainGame.isPlayerOne());

                Intent intent = new Intent(SubGameActivity.this, MainGameActivity.class);
                intent.putExtra("main game", (Parcelable) mainGame);
                startActivity(intent);
            } else {
                gameStatus.setText("Invalid Move!");
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    String.valueOf(mainGame.getWinner()) + " has already won!",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            // do something on back.
            Intent intent = new Intent(SubGameActivity.this, MainGameActivity.class);
            intent.putExtra("main game", (Parcelable) mainGame);
            startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
