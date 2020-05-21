package com.example.ultimatetictactoe;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * A standard 3x3 Tic-Tac-Toe board with check winner functionality
 */
public class SubGame implements Parcelable, Serializable {
    public static int BOARD_SIZE = 3;
    private char[][] arr;
    private char winner;
    public char currentPlayer;

    public SubGame() {
        arr = new char[BOARD_SIZE][BOARD_SIZE];
        winner = ' ';
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                arr[i][j] = ' ';
            }
        }
    }

    /**
     * Checks win conditions and saves the winner in state
     */
    private void checkWinner() {
        // Horizontal Checks
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (arr[i][0] == arr[i][1] && arr[i][0] == arr[i][2] && (arr[i][0] == 'x' || arr[i][0] == 'o')) {
                winner = arr[i][0];
                return;
            }
        }
        // Vertical Checks
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (arr[0][i] == arr[1][i] && arr[0][i] == arr[2][i] && (arr[0][i] == 'x' || arr[0][i] == 'o')) {
                winner = arr[0][i];
                return;
            }
        }
        // Diagonal Checks
        if (arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2]  && (arr[0][0] == 'x' || arr[0][0] == 'o')) {
            winner = arr[0][0];
            return;
        }
        if (arr[0][2] == arr[1][1] && arr[0][2] == arr[2][0]  && (arr[0][2] == 'x' || arr[0][2] == 'o')) {
            winner = arr[0][2];
            return;
        }
    }

    public char getWinner() {
        return winner;
    }
    public void setWinner(char winner) {
        this.winner = winner;
    }

    public char[][] getBoard() {
        return arr;
    }
    public void setBoard(char[][] arr) {
        this.arr = arr;
    }

    /**
     * Play turn - called by SubGameActivity
     * @param x The x location of the click in a 2-D representation of the 3x3 board
     * @param y The y location of the click in a 2-D representation of the 3x3 board
     * @param c The char of the player ("x" or "o")
     * @return False if invalid move (already won or already taken). True otherwise.
     */
    public boolean playTurn(int x, int y, char c) {
        checkWinner();
        if (winner == 'x' || winner == 'o') {
            // Already a winner
            return false;
        }
        if (arr[x][y] == 'x' || arr[x][y] == 'o') {
            // Spot already taken
            return false;
        }
        arr[x][y] = c;
        return true;
    }

    // Parceling
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt((int) winner);
        dest.writeInt((int) currentPlayer);
        Bundle bundle = new Bundle();
        bundle.putSerializable("board", arr);
        dest.writeBundle(bundle);
    }


    protected SubGame(Parcel in) {
        winner = (char) in.readInt();
        currentPlayer = (char) in.readInt();
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        assert bundle != null;
        arr = (char [][]) bundle.getSerializable("board");
    }

    public static final Creator<SubGame> CREATOR = new Creator<SubGame>() {
        @Override
        public SubGame createFromParcel(Parcel in) {
            return new SubGame(in);
        }

        @Override
        public SubGame[] newArray(int size) {
            return new SubGame[size];
        }
    };
}