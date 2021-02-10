package a2;

public class CardImpl implements Card {
	
	private int rank;
	private Card.Suit suite;
	private boolean lol;
	
	
	
	public CardImpl(int rank, Card.Suit suite) {
		
		this.rank = rank;
		this.suite = suite;
		this.lol = lol;
		if (rank < 2 || rank > 14) {
			throw new RuntimeException("Out of Bounds Bro");
		}
	} 
	
	public boolean lol() {
		return lol;
		
	} 
	
	public int getRank() {
		return rank;
		
	} 
	
	public Card.Suit getSuit() {
		return suite; 
	}
	
	public String toString() {
	String newRank = "";
		if (rank == 2) {
			newRank = "2";
		} else if (rank == 3) {
			newRank = "3";
		} else if (rank == 4) {
			newRank = "4";
		} else if (rank == 5) {
			newRank = "5";
		} else if (rank == 6) {
			newRank = "6";
		} else if (rank == 7) {
			newRank = "7";
		} else if (rank == 8) {
			newRank = "8";
		} else if (rank == 9) {
			newRank = "9";
		} else if (rank == 10) {
			newRank = "10";
		} else if (rank == 11) {
			newRank = "JACK";
		} else if (rank == 12) {
			newRank = "QUEEN";
		} else if (rank == 13) {
			newRank = "KING";
		} else if (rank == 14) {
			newRank = "ACE";
		} 
		
		String newSuite = "";
		if (suite == Suit.SPADES) {
			newSuite = " of Spades";
		} else if (suite == Suit.HEARTS) {
			newSuite = " of Hearts";
		} else if (suite == Suit.CLUBS) {
			newSuite = " of Clubs";
		} else if (suite == Suit.DIAMONDS) {
			newSuite = " of Diamonds";
		} 
	
	return newRank + newSuite;
}
	
	
	public boolean equals (Card other) {       
		if (this.suite == other.getSuit() && this.rank == other.getRank())
		   	    return ( true ); 
		   	 else		  
		   	    return ( false );
		
		   }
	
	}


