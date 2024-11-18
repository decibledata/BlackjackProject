package com.skilldistillery.cards.app;

import java.util.Scanner;
import com.skilldistillery.cards.blackjack.Dealer;
import com.skilldistillery.cards.blackjack.Player;
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

			getBank();

			while (playerBank > 0) {
				playRound();
				playAgain();
			}
		}
	}

	private void playRound() {
		int betAmount = getBetAmount();
		deck.shuffle();

		player.hitMe(deck.dealCard());
		player.hitMe(deck.dealCard());
		dealer.hitMe(deck.dealCard());
		dealer.hitMe(deck.dealCard());

		System.out.println("Your hand: " + player.hand);
		System.out.println("Dealer's hand: [facedown card], " + dealer.hand.getCardsInHand());

		moneyWonOrLost(betAmount);
		//Fix so that winner or loser is displayed..

		player.clearHand();
		dealer.clearHand();
	}

	private boolean playAgain() {
		if (playerBank > 0) {
			System.out.println("\nFeeling Lucky? Wanna play again?\n[1] YES | [2] NO\n");
			int playAgain = sc.nextInt();
			
			if (playAgain == 1) {
				return true;
			}
			else if (playAgain == 2) {
				System.out.println("\nThanks for stopping by! Your Cashout is $" + playerBank);
				System.exit(0);
			}
			else {
				System.out.println("INVALID CHOICE. TRY AGAIN");
				return playAgain();
			}
		}
		return false;
	}

	private int getBetAmount() {
		int betAmount;
		while (true) {
			System.out.print("ENTER BET AMOUNT: $");
			betAmount = sc.nextInt();
			if (betAmount > 0 && betAmount <= playerBank) {
				playerBank -= betAmount;
				break;
			} else {
				System.out.println("Invalid Bet. Enter valid amount.");
			}
		}
		return betAmount;
	}

	private int getBank() {
		while (true) {
			System.out.print("ENTER STARTING BANK: $");
			playerBank = sc.nextInt();

			if (playerBank >= 500) {
				System.out.println("\nAlright. You ready? Great! Lets Play!\n");
				break;
			} else {
				System.out.println("Are you joking? " + "This place is a $500 Minimum. "
						+ "Come back when you got some real money..");
			}
		}
		return playerBank;
	}

	public void playerBet() {
		getBetAmount();
	}

	private void moneyWonOrLost(int betAmount) {
		int playerHandValue = player.hand.getHandValue();
		int dealerHandValue = dealer.hand.getHandValue();

		System.out.println("Dealer's Hand: " + dealer.hand);

		if (playerHandValue > 21) {
			System.out.println("\nBUST! You lose $" + betAmount);
		} else if (dealerHandValue > 21) {
			playerBank += betAmount;
			System.out.println("\nDEALER BUST! You gain $" + betAmount);
		} else if (playerHandValue > dealerHandValue) {
			playerBank += betAmount;
			System.out.println("\nYOU WIN! You gain $" + betAmount);
		} else if (playerHandValue == dealerHandValue) {
			playerBank += betAmount;
			System.out.println("\nTIE!");
		}
	}

}

// NO HAND NO DECK NO CARDS