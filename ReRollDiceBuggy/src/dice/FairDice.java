package dice;

import java.util.*;

public class FairDice implements Dice {

	private int sides;
	protected List<Integer> rollHistory;
	
	public FairDice(int sides) throws Exception {
		rollHistory = new ArrayList<Integer>();
		this.sides = sides;
	}
	@Override
	public int getSides() {
		return sides;
	}

	@Override
	public int getNumOfRolls() {
		return rollHistory.size();
	}

	public List<Integer> getRollHistory() {
		return rollHistory;
	}

	@Override
	public int rollDie() {
		Random r = new Random();
		int roll = r.nextInt(getSides())+1;
		rollHistory.add(roll);
		Collections.sort(rollHistory);
		return roll;
	}
	@Override
	public int getDiceAtRollNumber(int rollNumber) {
		return rollHistory.get(rollNumber);
	}
}
