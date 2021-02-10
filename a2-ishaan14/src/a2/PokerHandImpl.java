package a2;


public class PokerHandImpl implements PokerHand {
	
	private Card[] _cards;
	private int getHandType = 0;
	private int getRank = 0;
	private int compareHand = 0; 
	private int rank = 0;
	private int rank2 = 0;

	public PokerHandImpl (Card[] cards) {
		if (cards == null) {
			throw new RuntimeException("Null");
		}
		if (cards.length >  5) {
			throw new RuntimeException("Out of Bounds");
		}
		this._cards = cards.clone();
		for (int i = 0; i < _cards.length; i++) {
			for (int j = i; j < _cards.length; j++) {
				if (_cards[i].getRank() > _cards[j].getRank()) {
					Card tmp = _cards[i];
					_cards[i] = _cards[j];
					_cards[j] = tmp;
				}
				
			}
		}
		
	}
	
	
	public Card[] getCards() {
		return _cards.clone();
	}
	
	
	public boolean contains(Card c) {
		for (int i = 0; i < _cards.length; i++) {
			if (_cards[i].getRank() == c.getRank() && _cards[i].getSuit() == c.getSuit()) {
				return true;
			}
		}
		return false;
	}

	public boolean isOnePair() {
		int count = 0;
		for (int i = 0; i < _cards.length-1; i++) {
			
			if (_cards[i].getRank() == _cards[i+1].getRank()) {
				rank = _cards[i].getRank();
				count++;
			} 
		}
			if (count == 1) {
				return true;
			} else {
				return false;
			}
		}
	
	
	public boolean isTwoPair() { // delete between second else if 
	int count = 0;
	for (int i = 0; i < _cards.length-1; i++) {
		
		if (_cards[i].getRank() == _cards[i+1].getRank()) {
			count++;
		}
	}
	
		if (count == 2 && !isFullHouse() && !isFourOfAKind() && !isThreeOfAKind()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isThreeOfAKind() {
			if (_cards[0].getRank() == _cards[1].getRank() && _cards[0].getRank() == _cards[2].getRank()) {
				if (_cards[0].getRank() != _cards[3].getRank()){
					if (_cards[3].getRank() != _cards[4].getRank()){
						return true;
					
					}
				}
			}
			if (_cards[1].getRank() == _cards[2].getRank() && _cards[1].getRank() == _cards[3].getRank()) {
				if (_cards[1].getRank() != _cards[0].getRank() && _cards[1].getRank() != _cards[4].getRank() ){
						return true;
				}
			}
			if (_cards[2].getRank() == _cards[3].getRank() && _cards[2].getRank() == _cards[4].getRank()) {
				if (_cards[2].getRank() != _cards[1].getRank()){
					if (_cards[0].getRank() != _cards[1].getRank()){
						return true;
					}
				}
			}
			return false;
	}
	
	public boolean isWheel() {
		if (_cards[4].getRank() == 14) {
			if (_cards[3].getRank() == 5) {
				if (_cards[2].getRank() == 4) {
					if (_cards[1].getRank() == 3) {
						if (_cards[0].getRank() == 2) {
							rank2 = _cards[3].getRank();
							return true;
							
							
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean isStraight() {
		
	    if((_cards[0].getRank() == _cards[1].getRank() - 1) && (_cards[1].getRank() == _cards[2].getRank() - 1)  && 
	    	(_cards[2].getRank() == _cards[3].getRank() - 1) && (_cards[3].getRank() == _cards[4].getRank() - 1)) {
	    	return true;
	    } else if (isWheel()) {
	    	return true;
	    } else {
	    	return false;
	    }
	}


	public boolean isFlush() {
        	if ((_cards[0].getSuit() == _cards[1].getSuit()) && (_cards[0].getSuit() == _cards[2].getSuit()) && (_cards[0].getSuit() == _cards[3].getSuit()) && (_cards[0].getSuit() == _cards[4].getSuit())) {
				return true;
        }
    
        return false;
	}
	public boolean isFullHouse() {
		if (_cards[0].getRank() == _cards[1].getRank() && _cards[0].getRank() == _cards[2].getRank() && _cards[0].getRank() != _cards[3].getRank() && _cards[3].getRank() == _cards[4].getRank()) {
					return true;
				}
		if (_cards[2].getRank() == _cards[3].getRank() && _cards[2].getRank() == _cards[4].getRank() && _cards[2].getRank() != _cards[1].getRank() && _cards[0].getRank() == _cards[1].getRank()) {
					return true;
				}
		return false;
	}
	public boolean isFourOfAKind() {
		if (_cards[0].getRank() == _cards[1].getRank() && _cards[0].getRank() == _cards[2].getRank() && _cards[0].getRank() == _cards[3].getRank()) {
			if (_cards[0].getRank() != _cards[4].getRank()) {
					return true;
				}
			}
		if (_cards[1].getRank() == _cards[2].getRank() && _cards[1].getRank() == _cards[3].getRank() && _cards[1].getRank() == _cards[4].getRank()) {
			if (_cards[1].getRank() != _cards[0].getRank()) {
					return true;
			}
		}
		return false;
	}
	

	public boolean isStraightFlush() {
		if (isStraight() && isFlush()) {
			return true;	
		} else {
			return false;
	}
	}
		
	public int getHandTypeValue() {
	
		if (isStraightFlush() == true) {
			getHandType = 9;
		} else if (isFourOfAKind()== true) {
			getHandType = 8;
		} else if (isFullHouse()== true) {
			getHandType = 7;
		} else if (isFlush()== true) {
			getHandType = 6;
		} else if (isStraight()== true) {
			getHandType = 5;
		} else if (isThreeOfAKind()== true) {
			getHandType = 4;
		} else if (isTwoPair()== true) {
			getHandType = 3;
		} else if (isOnePair()== true) {
			getHandType = 2;
		} else {
			getHandType = 1;
		}
		return getHandType;
	} 
	
	public int getHandRank() {			
		if (isOnePair() == true) {
			getRank = rank;
		} else if (isTwoPair() == true) {
			getRank = _cards[3].getRank();
		} else if (isThreeOfAKind() == true) {
			getRank = _cards[2].getRank();
		} else if (isStraight() && !isWheel() == true) {
			getRank = _cards[4].getRank();
		} else if (isWheel() == true) {
			getRank = 5;
		} else if (isFlush() == true) {
			getRank = _cards[4].getRank();
		} else if (isFullHouse() == true) {
			getRank = _cards[2].getRank();
		} else if (isFourOfAKind() == true) {
			getRank = _cards[2].getRank();
		} else if (isStraightFlush() == true) {
			getRank =  _cards[4].getRank();
		} else {
			getRank = _cards[4].getRank();
		}
		return getRank;
	} 

	public int compareTo(PokerHand other) {
			if (other.getHandTypeValue() > getHandTypeValue()) {
				compareHand = -1; 
			} else if (other.getHandTypeValue() < getHandTypeValue()) {
				compareHand = 1;
			} else if (other.getHandTypeValue() == getHandTypeValue()) {
				if (other.getHandRank() > getHandRank()) {
					compareHand = -1;
				} else if (other.getHandRank() < getHandRank()) {
					compareHand = 1;
				} else if (other.getHandRank() == getHandRank()) {
					compareHand = 0;
			}
		}
			return compareHand;
	}
}
