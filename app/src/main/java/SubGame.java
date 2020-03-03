

public class SubGame {
    private final int BOARD_SIZE = 3;
    private char[][] arr;
    private char winner;

    private void checkWinner() {
        //horizontals
        for(int i = 0; i < BOARD_SIZE; i++) {
//            char toCompare = arr[i][0];
//            boolean isWin = true;
//            for(int j = 0; j < BOARD_SIZE; j++) {
//                if (arr[i][j] == toCompare && arr)
//            }
            if(arr[i][0] == arr[i][1] && arr[i][0] == arr[i][2] && (arr[i][0] == 'x' || arr[i][0] == 'o')) {
                winner = arr[i][0];
                return;
            }

        }
        //verticals
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(arr[0][i] == arr[1][i] && arr[0][i] == arr[2][i] && (arr[0][i] == 'x' || arr[0][i] == 'o')) {
                winner = arr[0][i];
                return;
            }
        }
        //diagonals
        if(arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2]  && (arr[0][0] == 'x' || arr[0][0] == 'o')) {
            winner = arr[0][0];
            return;
        }

        if(arr[0][2] == arr[1][1] && arr[0][2] == arr[2][0]  && (arr[0][2] == 'x' || arr[0][2] == 'o')) {
            winner = arr[0][2];
            return;
        }
    }

    public SubGame() {
        arr = new char[3][3];
        winner = ' ';
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

    public boolean playTurn(int x, int y, char c) {
        if (winner == 'x' || winner == 'o') {
            return false;
        }
        if (arr[x][y] == 'x' || arr[x][y] == 'o') {
            return false;
        }
        arr[x][y] = c;
        return true;
    }
}
