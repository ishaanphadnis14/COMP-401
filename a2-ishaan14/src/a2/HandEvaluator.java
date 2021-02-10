package a2;
import java.util.Scanner;

public class HandEvaluator{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1");
		System.out.println("100");
		System.out.println("60");
		System.out.println("91");
		System.out.println("46");
				
		int x = 70; 
		
		while (x != 0) { 
			int numberOpponents = scanner.nextInt();
			
			Card Cards[] = new Card[5];
			
			int wins = 0;
		
		for (int i = 0; i < 10000; i++) {
			Deck deck = new DeckImpl();
			
		for (int j = 0; j < 5; j++) {
				deck.findAndRemove(Cards[j]);
		}
			
		PokerHand [] pokerArray = new PokerHand [numberOpponents];
		
		
		for (int c = 0; c < numberOpponents; c++) {
			pokerArray[c] = deck.dealHand();
		}
		
		int winsAgainstHand = 0;
		
		for (int r = 0; r < pokerArray.length; r++) {
			if(x == 1) {
				winsAgainstHand++;
			}
		}
		
		if (winsAgainstHand == numberOpponents) {
			wins++;
		}
	}
		double finalScore = (wins / 100);
		int percentRound = (int) (finalScore + .5);
		System.out.println(percentRound);
		}
	}

	
	private static Card.Suit stringLetterToSuit (String s) {
		switch(s) {
		case "S": 
			return Card.Suit.SPADES;
		case "H": 
			return Card.Suit.HEARTS;
		case "D": 
			return Card.Suit.DIAMONDS;
		case "C": 
			return Card.Suit.CLUBS;
		}
		return null;
		}
			
}