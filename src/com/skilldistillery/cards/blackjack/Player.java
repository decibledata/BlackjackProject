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

	public int getHandValue() {
		return hand.getHandValue();
	}

	public void clearHand() {
		hand.clear();

	}

	public void addCardInHand(Card card) {
		hand.addCard(card);
	}

	public boolean hitStay(Deck deck) {
		boolean choice = true;

		while (choice) {
			System.out.println("\nYou stand at " + hand);
			System.out.println("\n\tHit[1] or Stay[2]?");
			int hS = sc.nextInt();

			if (hS == 1) {
				hand.addCard(deck.dealCard());
				System.out.println("HIT ME!");
				if (hand.getHandValue() > 21) {
					System.out.println("BUST!");
				}
			} else if (hS == 2) {
				choice = false;
				System.out.println("STAY");
			} else {
				System.out.println("Invalid choice.. Try again.");
			}
		}
		return choice;
	}

	public boolean busted() {
		return hand.getHandValue() > 21;
	}

}
