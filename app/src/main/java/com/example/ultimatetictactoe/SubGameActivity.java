package com.example.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubGameActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private SubGame subGame = new SubGame();
    private char currPlayer = 'x';
//    private TextView gameStatus = (TextView)findViewById(R.id.gameStatus);


    private TextView title; // Text for screen title
    private TextView gameStatus; // Text for errors and other pop up information.


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_game);
        title = (TextView)findViewById(R.id.title);
        gameStatus = (TextView)findViewById(R.id.gameStatus);

        // Set value of title to "Game at (x, y)
        int x = getIntent().getIntExtra("x", 0);
        int y = getIntent().getIntExtra("y", 0);
        title.setText("Game at (" + String.valueOf(x)+", " + String.valueOf(y)+")");

        // Give board values from
        for (int i = 0; i < SubGame.BOARD_SIZE; i++) {
            for (int j = 0; j < SubGame.BOARD_SIZE; j++) {
                String ButtonID = "btn" + String.valueOf(i) + String.valueOf(j);
                int resID = getResources().getIdentifier(ButtonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener((View.OnClickListener) this);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        String id = getResources().getResourceEntryName(v.getId());
        int x = Integer.parseInt(id.substring(3, 4));
        int y = Integer.parseInt(id.substring(4, 5));
        Log.d("click", "" + x + ", " + y);
        if(subGame.playTurn(x, y, currPlayer)) {
            buttons[x][y].setText(String.valueOf(currPlayer));
//            Intent intent = new Intent(SubGameActivity.this, MainActivity.class);
//            startActivity(intent);
        } else {
            gameStatus.setText("Invalid Move!");
        }
    }
}
