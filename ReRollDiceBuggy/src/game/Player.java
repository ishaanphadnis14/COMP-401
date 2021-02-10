package game;

import dice.Dice;

public interface Player {
	int getRerollsLeft();
	Dice getDice();
	int getRoundsWon();
	int rollDie(boolean isReroll);
	int addWin();
	String getName();
	int getRoundAction();
	int setRerolls(int numRerolls) throws Exception;
}
