/**
 * Represents the larger 3x3 Tic-Tac-Toe board where each square is its own Tic-Tac-Toe game
 */
public class MainGame {
	private SubGame[][] subGames; // 3x3 Array of SubGames. Parallex index with trackWins
	private char[][] trackWins; // Stores the winner of each subGame ("x" or "o") - parallel index

	public MainGame() {
		subGames = new SubGame[3][3];
		trackWins = new char[3][3];
	}

	/**
	 * Plays a turn and checks for winner of a subGame
	 * @param xSubGame The x location of the subGame
	 * @param ySubGame The y location of the subGame
	 * @param xClick The x location of the click in the subGame
	 * @param yClick The y location of the click in the subGame
	 * @param c char of the player ("x" or "o")
	 */
	public void playSubGameTurn(int xSubGame, int ySubGame, int xClick, int yClick, char c) {
		SubGame thisSubGame = subGames[xSubGame][ySubGame];
		if (!thisSubGame.playTurn(xClick, yClick, c)) {
			// Invalid move
			System.out.println("This was an invalid move!");
		} else {
			// Valid move; Check winner
			if (thisSubGame.getWinner() != ' ') {
				trackWins[xSubGame][ySubGame] = thisSubGame.getWinner();
			}
		}
	}


	public SubGame[][] getSubGames() {
		return subGames;
	}
	public char[][] getTrackWins() {
		return trackWins;
	}
}
