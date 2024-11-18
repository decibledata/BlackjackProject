package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class Dealer extends Player {

	private Deck deck;

	public Dealer() {
		deck = new Deck();
	}
	//NO getDeck()
	public Card dealCard() {
		if (deck.checkDeckSize() > 0) {
			return deck.dealCard();			
		}
		else {
			System.out.println("NO CARDS LEFT");
		}
		return null;
	}
	
	public void shuffleDeck() {
		deck.shuffle();
	}
	public void showHand() {
		System.out.println();
	}
	public void playTurn(Deck deck) {
		while (getHandValue() < 17 ) {
			addCardInHand(deck.dealCard());
		}
	
	}
	
}
