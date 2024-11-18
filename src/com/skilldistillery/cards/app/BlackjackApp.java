package com.skilldistillery.cards.app;

import java.util.Random;
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

			boolean continueGame = true;
			while (continueGame && playerBank > 0) {
				playRound();
				
				if (playerBank > 0) {
					continueGame = playAgain();
				}
					else {
						System.out.println("You've spent all you can spend. Time to leave..");
						break;
					}
					System.out.println("Thanks for stopping by. Your cashout is $" + playerBank);
					break;
				}
			}
		}

	private void playRound() {
		int betAmount = getBetAmount();
		deck.shuffle();

		player.addCardInHand(deck.dealCard());
		player.addCardInHand(deck.dealCard());
		dealer.addCardInHand(deck.dealCard());
		dealer.addCardInHand(deck.dealCard());

		Random rand = new Random();
		int dealerBet = rand.nextInt(playerBank / 2) + 1;

		System.out.println("DEALERS BET: $" + dealerBet);
		System.out.println("\nYour hand: " + player.hand);
		System.out.println("Dealer's hand: [facedown card], " + dealer.hand.getCardsInHand());
		
		if (player.getHandValue() == 21) {
			System.out.println("BLACKJACK!");
			System.out.println("You currently have $" + playerBank + dealerBet + " in your bank");
			player.clearHand();
			dealer.clearHand();
			return;
			
		}
		
		boolean playerBust = player.hitStay(deck);
			if (playerBust) {
				System.out.println("BUST");
				player.clearHand();
				dealer.clearHand();
				return;
			}
			
			System.out.println("Player STAYS. Dealer.. Reveal your cards!\n");
			System.out.println("Dealer's Hand:" + dealer.hand);

			while (dealer.getHandValue() < 21) {
				if(dealer.getHandValue() < 17) {
					System.out.println("\nDealer Hits.");
					dealer.addCardInHand(deck.dealCard());
					System.out.println("Dealer's Hand:" + dealer.hand);
				}
				else {
					break;
				}
			}
		if (dealer.busted()) {
			System.out.println("DEALER BUST");
			playerBank += betAmount * 2;
			System.out.println("You currently have $" + playerBank + " in your bank");
			player.clearHand();
			dealer.clearHand();
			return;
			}

		int playerHandValue = player.getHandValue();
		int dealerHandValue = dealer.getHandValue();
		

		String winner = showWinner(betAmount, playerHandValue, dealerHandValue, dealerBet);
		
		if (winner.equals("Player")) {
			playerBank += betAmount + dealerBet;
			System.out.println("\nYOU WIN! You Gained $" + (betAmount + dealerBet) + "\nYou currently have "
					+ playerBank + " in your bank.");

		} else if (winner.equals("Dealer")) {
			System.out.println(
					"\nYOU LOSE! You Lost $" + betAmount + "\nYou currently have $" + playerBank + " in your bank.");

		} else {
			System.out.println("TIE");
		}

		player.clearHand();
		dealer.clearHand();
	}

	private String showWinner(int betAmount, int playerHandValue, int dealerHandValue, int dealerBet) {
		if (player.busted()) {
			return "Dealer";
		} else if (dealerHandValue > 21) {
			return "Player";
		} else if (playerHandValue > dealerHandValue) {
			return "Player";
		} else if (dealerHandValue > playerHandValue) {
			return "Dealer";
		} else {
			return "Tie";
		}
	}

	private boolean playAgain() {
			System.out.println("\nFeeling Lucky? Wanna play again?\n[1] YES | [2] NO\n");
			int playAgain = sc.nextInt();

			while (playAgain != 1 && playAgain != 2) {
				System.out.println("Invalid choice. Try again.");
				playAgain = sc.nextInt();
			}
			return playAgain == 1;
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
}

// NO HAND NO DECK NO CARDS