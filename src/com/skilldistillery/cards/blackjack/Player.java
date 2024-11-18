package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class Player {
	public BlackjackHand hand;
	private Scanner sc = new Scanner(System.in);

	public Player() {
		hand = new BlackjackHand();
	}
	public void hitMe(Card card) {
		hand.addCard(card);
	}
	
	public int getHandValue() {
		return hand.getHandValue();
	}
	
	
	
	public boolean hitStay(Deck deck) {
		boolean choice = true;
		while (choice) {
			System.out.println("You stand at " + hand );
			System.out.println("\n\tHit[1] or Stay[2]?");
			int hS = sc.nextInt();
			switch (hS) {
			case 1: 
				hitMe(deck.dealCard());
				break;
			case 2:
				return choice;
			default:
				System.out.println("Invalid Choice. Try again.");
			break;
			}
		}
		System.out.println("!BUST!");
		return choice;
	}
	//NO getHand()
}
