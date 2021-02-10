package game;

import java.util.Random;

public class GameControllerImpl implements GameController {

	private Player player1;
	private Player player2;
	private String[] winners;
	private int numOfRounds;
	private int currentRound;

	public GameControllerImpl(int numOfRounds, Player player1, Player player2) throws Exception {
		if (numOfRounds < 3 || numOfRounds % 2 == 0) {
			throw new Exception("The game requires the number of rounds to be 3 or greater and an odd number");
		}
		if (player1 == null || player2 == null) {
			throw new Exception("One or both player objects are null");
		}
		if (player1.getName().equals(player2.getName())) {
			throw new Exception("Both players cannot have the same name");
		}
		
		this.player1 = player1;
		this.player2 = player2;
		
		this.numOfRounds = numOfRounds;
		
		this.player1.setRerolls(getTotalRounds());
		this.player2.setRerolls(getTotalRounds());
		
		winners = new String[numOfRounds + 1];
		currentRound = 2;
		
		playRound();
	}

	@Override
	public Player getPlayer1() {
		return player1;
	}

	@Override
	public Player getPlayer2() {
		return player2;
	}

	@Override
	public Player getCurrentWinner() {
		if (getPlayer1().getRoundsWon() > getPlayer2().getRoundsWon()) {
			return getPlayer1();
		} else if (getPlayer1().getRoundsWon() < getPlayer2().getRoundsWon()) {
			return getPlayer2();
		} else {
			return null;
		}
	}

	@Override
	public String getLastRoundWinnerName() {
		if (getCurrentRound() == 1) {
			return "nobody";
		} else {
			return winners[getCurrentRound() - 1];
		}
	}

	@Override
	public int getCurrentRound() {
		return currentRound;
	}

	@Override
	public int getRoundsLeft() {
		return getTotalRounds() - getCurrentRound();
	}

	@Override
	public int getTotalRounds() {
		return numOfRounds;
	}

	@Override
	public boolean isGameOver() {
		if (getPlayer1().getRoundsWon() == (getTotalRounds() + 1) / 2
				|| getPlayer2().getRoundsWon() == (getTotalRounds() + 1 ) / 2) {
			return true;
		} 
		return false;
	}
	
	private void playRound() {
		if (!isGameOver()) {
			int p1 = player1.getRoundAction();
			int p2 = player2.getRoundAction();
			if (p1 > p2) {
				addWin(1);
			} else if(p1 < p2) {
				System.out.println("Looks like " + getPlayer2().getName() + " won that one!");
				addWin(2);
			} else {
				System.out.println("That one was a draw, lets settle it with a coin toss! ");
				if (new Random().nextDouble() > .5) {
					addWin(1);
				} else {
					addWin(2);
				}
			}
			playRound();
		}
		System.out.println("That's the game folks! " + getCurrentWinner().getName() + " won the game!");
	}
	
	private void addWin(int playerNumber) {
		if (playerNumber == 1) {
			player1.addWin();
			winners[getCurrentRound()] = player1.getName();
			System.out.println("Looks like " + getPlayer1().getName() + " won round " + getCurrentRound() + "!");
			
		} else {
			player2.addWin();
			winners[getCurrentRound()] = player2.getName();
			System.out.println("Looks like " + getPlayer2().getName() + " won round " + getCurrentRound() + "!");
		}
		currentRound++;
	}
}
