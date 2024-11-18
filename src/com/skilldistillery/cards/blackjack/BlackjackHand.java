package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.AbstractHand;
import com.skilldistillery.cards.common.Card;

public class BlackjackHand extends AbstractHand {
	// NO FIELDS

	@Override
	public int getHandValue() {
		int handValue = 0;
		int aceCard = 0;
		
		for(Card card: cardsInHand) {
			handValue += card.getValue();
			if (card.getValue() == 11 ) {
				aceCard++;
			}
		}
		return handValue;
	}
	
	public boolean blackJack() {
		if(getHandValue() == 21) {
			System.out.println("!BLACKJACK!");
			return true;
		}
		return false;
	}
	
	public boolean bust() {
		return (getHandValue() > 21);
	}

	public void clear() {
		cardsInHand.clear();		
	}

	public Object getCardsInHand() {
		return cardsInHand;
	}
}