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
		return deck.dealCard();
	}
	
	public void shuffleDeck() {
		deck.shuffle();
	}
	public void showHand() {
		System.out.println();
	}
	
}
