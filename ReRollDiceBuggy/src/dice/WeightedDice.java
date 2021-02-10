package dice;

import java.util.Random;

public class WeightedDice extends FairDice {

	private int preferredNumber;

	public WeightedDice(int sides, int preferredNumber) throws Exception {
		super(sides);
		this.preferredNumber = preferredNumber;
	}

	@Override
	public int rollDie() {
		Random r = new Random();
		if (r.nextDouble() <= .7) {
			super.rollHistory.add(preferredNumber);
			return preferredNumber;
		}
		super.rollDie();
		return 0;
	}
}
