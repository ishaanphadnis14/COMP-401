package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dice.Dice;
import dice.FairDice;

public class GameTests {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	public void testDiceSideNegative () throws Exception{
		try {
		Dice dice = new FairDice (-1);
		fail("Exception not thrown");
		} catch (Exception e) {
		
		}
	}
	
	public void testDiceSidesZero() throws Exception {
		try {
		Dice dice = new FairDice(0);
		fail ("Exception not thrown");
		} catch (Exception e) {
		}
		}
	
	public void testDiceSidesPositive() throws Exception {
		try {
			Dice dice = new FairDice (1);
			fail("Exception not thrown");
			} catch (Exception e) {
			
			}
		}
	
}
