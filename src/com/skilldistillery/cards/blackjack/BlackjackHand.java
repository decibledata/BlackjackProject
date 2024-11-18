package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.AbstractHand;
import com.skilldistillery.cards.common.Card;

public class BlackjackHand extends AbstractHand {
	// NO FIELDS

	@Override
	public int getHandValue() {
		int handValue = 0;
		for(Card card: cardsInHand) {
			handValue = card.getValue();
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
}
