package game;

public interface GameController {
	Player getPlayer1();
	Player getPlayer2();
	Player getCurrentWinner();
	String getLastRoundWinnerName();
	
	int getCurrentRound();
	int getRoundsLeft();
	int getTotalRounds();
	
	boolean isGameOver();
}
