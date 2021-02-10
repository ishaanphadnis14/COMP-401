package game;

import java.util.Scanner;

import dice.Dice;

public class PlayerImpl implements Player {

	protected int rerollsLeft;
	private int wins;
	protected Dice diceToUse;
	private String playerName;

	public PlayerImpl(Dice diceToUse, String playerName) throws Exception {
		if (diceToUse == null) {
			throw new Exception("Dice used by this player can't be null");
		}
		if (playerName == null || playerName.equals("")) {
			throw new Exception("Players name cannot be null or whitespace");
		}
		this.diceToUse = diceToUse;
		this.playerName = playerName;
		wins = 0;
	}

	@Override
	public int getRerollsLeft() {
		return rerollsLeft;
	}

	@Override
	public Dice getDice() {
		return diceToUse;
	}

	@Override
	public int getRoundsWon() {
		return wins;
	}

	@Override
	public int rollDie(boolean isReroll) {
		return diceToUse.rollDie();
	}

	@Override
	public int addWin() {
		return wins++;
	}

	@Override
	public String getName() {
		return playerName;
	}

	@Override
	public int getRoundAction() {
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println("Type 'r' to roll your die!");
			int diceRoll = 0;
			String action = s.next();

			if (action.equals("r")) {
				diceRoll = diceToUse.rollDie();
			} else {
				continue;
			}
			System.out.println(
					"You rolled a " + diceRoll + ". Is that good? Type 's' to submit your roll or 'r' to reroll");
			String option = s.next();

			if (option.equals("s")) {
				return diceRoll;
			} else if (option.equals("r")) {
				if (getRerollsLeft() > 0) {
					rerollsLeft--;
					continue;
				} else {
					System.out.println("You don't have any rerolls left, we gotta stick with that roll");
					return diceRoll;
				}
			} else {
				continue;
			}
		}
	}

	@Override
	public int setRerolls(int rerolls) throws Exception {
		if (rerolls < 3) {
			throw new Exception("You can't start with less than 3 rerolls");
		}
		return getRerollsLeft();
	}

}
