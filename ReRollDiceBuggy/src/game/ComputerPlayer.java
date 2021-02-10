package game;

import java.util.Random;

import dice.Dice;

public class ComputerPlayer extends PlayerImpl {

	public ComputerPlayer(Dice diceToUse) throws Exception {
		super(diceToUse, "Robot #" + new Random().nextInt(9999));
	}

	@Override
	public int getRoundAction() {
		int diceRoll = diceToUse.rollDie();
		double chance = new Random().nextDouble();
		switch (diceRoll) {
		case 1:
			if (getRerollsLeft() > 0) {
				rerollsLeft--;
				return getRoundAction();
			} else {
				System.out.print("The computer, " + getName() + ", rolled a " + diceRoll + ". ");
				return diceRoll;
			}
		case 2:
			if (getRerollsLeft() > 0 && chance < .8) {
				rerollsLeft--;
				return getRoundAction();
			} else {
				System.out.print("The computer, " + getName() + ", rolled a " + diceRoll + ". ");
				return diceRoll;
			}
		case 3:
			if (getRerollsLeft() > 0 && chance < .5) {
				rerollsLeft--;
				return getRoundAction();
			} else {
				System.out.print("The computer, " + getName() + ", rolled a " + diceRoll + ". ");
				return diceRoll;
			}
		case 4:
			if (getRerollsLeft() > 0 && chance < .2) {
				rerollsLeft--;
				return getRoundAction();
			} else {
				System.out.print("The computer, " + getName() + ", rolled a " + diceRoll + ". ");
				return diceRoll;
			}
		case 5:
			if (getRerollsLeft() > 0 && chance < .05) {
				rerollsLeft--;
				return getRoundAction();
			} else {
				System.out.print("The computer, " + getName() + ", rolled a " + diceRoll + ". ");
				return diceRoll;
			}
		case 6:
			System.out.print("The computer, " + getName() + ", rolled a " + diceRoll + ". ");
			return diceRoll;
		default:
			System.out.print("The computer, " + getName() + ", rolled a " + diceRoll + ". ");
			return diceRoll;
		}
	}

}
