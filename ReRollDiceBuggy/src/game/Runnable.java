package game;

import java.util.Scanner;

import dice.Dice;
import dice.FairDice;

public class Runnable {

	public static void main(String[] args) throws Exception {
		Dice fairDie1 = new FairDice(6);
		Dice fairDie2 = new FairDice(6);

		Scanner s = new Scanner(System.in);
		System.out.println("Enter your name");
		Player humanPlayer = new PlayerImpl(fairDie1,s.next());
		Player computerPlayer = new ComputerPlayer(fairDie2);
		
		System.out.println("Enter how many rounds you want to play: ");
		
		GameController game = new GameControllerImpl(s.nextInt(), humanPlayer, computerPlayer);
	}

}
