package com.example.ultimatetictactoe;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.ultimatetictactoe.SubGame;

import java.io.Serializable;

public class MainGame implements Parcelable, Serializable {
    public static int BOARD_SIZE = 3;
    private SubGame[][] arr;
    private char winner;
    public boolean isPlayerOne;
    private int lastX = -1, lastY = -1;


    public MainGame() {
        arr = new SubGame[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                arr[i][j] = new SubGame();
            }
        }
        winner = ' ';
        isPlayerOne = true;
    }

    public boolean checkWinner() {
        //Check if there is already a winner
        if (winner != ' ') {
            return true;
        }

        // Horizontal Checks
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (arr[i][0].getWinner() == arr[i][1].getWinner() &&
                    arr[i][0].getWinner() == arr[i][2].getWinner() &&
                    arr[i][0].getWinner() != ' ') {
                winner = arr[i][0].getWinner();
                return true;
            }
        }
        // Vertical Checks
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (arr[0][i].getWinner() == arr[1][i].getWinner() &&
                    arr[0][i].getWinner() == arr[2][i].getWinner() &&
                    arr[0][i].getWinner() != ' ') {
                winner = arr[0][i].getWinner();
                return true;
            }
        }
        // Diagonal Checks
        if (arr[0][0].getWinner() == arr[1][1].getWinner() &&
                arr[0][0].getWinner() == arr[2][2].getWinner()  &&
                arr[0][0].getWinner() != ' ') {
            winner = arr[0][0].getWinner();
            return true;
        }
        if (arr[0][2].getWinner() == arr[1][1].getWinner() &&
                arr[0][2].getWinner() == arr[2][0].getWinner()  &&
                arr[0][2].getWinner() != ' ') {
            winner = arr[0][2].getWinner();
            return true;
        }
        return false;
    }

    boolean playTurn(int x, int y, int boardX, int boardY, char c) {
        // If winner already exits, return false
        if (checkWinner()) {
            return false;
        }

        if (lastX > -1 && lastY > -1) {
            if ((boardX != lastX || boardY != lastY) && arr[lastX][lastY].getWinner() == ' ') {
                return false;
            }
        }

        SubGame board = arr[boardX][boardY];
        if (!board.playTurn(x, y, c)) {
            return false;
        }
        lastX = x;
        lastY = y;
        isPlayerOne = !isPlayerOne;
        return true;
    }

    public SubGame[][] getArr() {
        return arr;
    }

    public void setArr(SubGame[][] arr) {
        this.arr = arr;
    }

    public char getWinner() {
        return winner;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }

    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    public void setPlayerOne(boolean playerOne) {
        isPlayerOne = playerOne;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt((int) winner);
        dest.writeInt(lastX);
        dest.writeInt(lastY);
        dest.writeByte((byte) (isPlayerOne ? 1 : 0));
        Bundle bundle = new Bundle();
        bundle.putSerializable("main board", arr);
        dest.writeBundle(bundle);
    }

    protected MainGame(Parcel in) {
        winner = (char) in.readInt();
        lastX = in.readInt();
        lastY = in.readInt();
        isPlayerOne = in.readByte() != 0;
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        assert bundle != null;
        arr = (SubGame [][]) bundle.getSerializable("main board");
    }

    public static final Creator<MainGame> CREATOR = new Creator<MainGame>() {
        @Override
        public MainGame createFromParcel(Parcel in) {
            return new MainGame(in);
        }

        @Override
        public MainGame[] newArray(int size) {
            return new MainGame[size];
        }
    };
}
