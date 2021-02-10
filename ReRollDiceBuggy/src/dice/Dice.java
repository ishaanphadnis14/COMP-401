package dice;

import java.util.List;

public interface Dice {
	int getSides();     // return number of sides the dice has
	int getNumOfRolls(); // return number of times dice was rolled
	List<Integer> getRollHistory();  // return list of 
	int rollDie();
	int getDiceAtRollNumber(int rollNumber);
}
