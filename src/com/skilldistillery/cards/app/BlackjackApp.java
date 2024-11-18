package com.skilldistillery.cards.app;

import java.util.Scanner;
import com.skilldistillery.cards.blackjack.Dealer;
import com.skilldistillery.cards.blackjack.Player;
import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class BlackjackApp {

	private Dealer dealer;
	private Player player;
	private Scanner sc;
	private Deck deck;
	private int playerBank;

	private BlackjackApp() {
		dealer = new Dealer();
		player = new Player();
		deck = new Deck();
		sc = new Scanner(System.in);

	}

	public static void main(String[] args) {
		BlackjackApp app = new BlackjackApp();
		app.startGame();
	}

	public void startGame() {
		while (true) {
			System.out.println("WELCOME TO GEORGE'S BLACKJACK TABLE\n" + "                               _\n"
					+ "       ,'`.    _  _    /\\    _(_)_\n" + "      (_,._)  ( `' )  <  >  (_)+(_)\n"
					+ "        /\\     `.,'    \\/      |\n");

			playerBet();

			while (playerBank > 0) {
				playRound();
				playAgain();
			}
			System.out.println("Thanks for stopping by. Here is your cashout " + playerBank);
		}
	}

	private void playRound() {
		// TODO Auto-generated method stub
		deck.shuffle();

		player.hitMe(deck.dealCard());
		player.hitMe(deck.dealCard());
		dealer.hitMe(deck.dealCard());
		dealer.hitMe(deck.dealCard());
	}

	private void playAgain() {
		// TODO Auto-generated method stub
		if (playerBank > 0) {
			System.out.println("\nFeeling Lucky? Wanna play again?\n[1] YES | [2] NO\n");
			int playAgain = sc.nextInt();

			if (playAgain == 1) {
				playRound();
			} else if (playAgain == 2) {
				System.out.println("\nThanks for stopping by. Your Cashout is " + playerBank);
				System.exit(0);
			} else {
				System.out.println("Invalid. Try Again");
			}
		}
	}

	public void playerBet() {

		while (true) {
			System.out.print("ENTER STARTING AMOUNT: $");
			playerBank = sc.nextInt();

			if (playerBank >= 500) {
				System.out.println("Alright. You ready? Great! Lets Play!");
				break;
			} else {
				System.out.println(
						"Are you joking? This place is a $500 Minimum. Come back when you got some real money.\nTry Again:");
			}
		}
	}
}

// NO HAND NO DECK NO CARDS