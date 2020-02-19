import java.util.Scanner;
import java.util.*;

public class BlackJack {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int money, bet;
		boolean winner;
		System.out.println("Welcome to BlackJack");

		money = 100;
		while (true) {
			System.out.println("You have " + money + " dollars.");
			do {
				System.out.println("How many dollars do you want to bet?  (Enter 0 to end.)");
				System.out.println("? ");
				bet = sc.nextInt();
				if (bet < 0 || bet > money)
					System.out.println("Your answer must be between 0 and " + money + '.');
			} while (bet < 0 || bet > money);
			if (bet == 0)
				break;
			winner = playBlackjack();
			if (winner)
				money = money + bet;
			else
				money = money - bet;
			System.out.println();
			if (money == 0) {
				System.out.println("Looks like you've are out of money!");
				break;
			}
		}

		System.out.println();
		System.out.println("You leave with $" + money + '.');
	}

	static boolean playBlackjack() {
		Deck deck = new Deck();
		Hand dealerHand = new Hand();
		Hand userHand = new Hand();

		deck.shuffle();
		dealerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());
		userHand.addCard(deck.dealCard());
		userHand.addCard(deck.dealCard());

		if (dealerHand.getBlackjackValue() == 21) {
			System.out.println("Dealer has the " + dealerHand.getCard(0) + " and the " + dealerHand.getCard(1) + ".");
			System.out.println("User has the " + userHand.getCard(0) + " and the " + userHand.getCard(1) + ".");
			System.out.println();
			System.out.println("Dealer has Blackjack.  Dealer wins.");
			return false;
		}

		if (userHand.getBlackjackValue() == 21) {
			System.out.println("Dealer has the " + dealerHand.getCard(0) + " and the " + dealerHand.getCard(1) + ".");
			System.out.println("User has the " + userHand.getCard(0) + " and the " + userHand.getCard(1) + ".");
			System.out.println();
			System.out.println("You have Blackjack.  You win.");
			return true;
		}

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("Your cards are:");
			for (int i = 0; i < userHand.getCardCount(); i++)
				System.out.println("    " + userHand.getCard(i));
			System.out.println("Your total is " + userHand.getBlackjackValue());
			System.out.println();
			System.out.println("Dealer is showing the " + dealerHand.getCard(0));
			System.out.println();
			System.out.print("Hit (H) or Stand (S)? ");
			char userAction; // User's response, 'H' or 'S'.
			do {
				userAction = Character.toUpperCase(sc.next().charAt(0));
				if (userAction != 'H' && userAction != 'S')
					System.out.print("Please respond H or S:  ");
			} while (userAction != 'H' && userAction != 'S');
			if (userAction == 'S') {
				break;
			} else {
				Card newCard = deck.dealCard();
				userHand.addCard(newCard);
				System.out.println();
				System.out.println("User hits.");
				System.out.println("Your card is the " + newCard);
				System.out.println("Your total is now " + userHand.getBlackjackValue());
				if (userHand.getBlackjackValue() > 21) {
					System.out.println();
					System.out.println("You busted by going over 21.  You lose.");
					System.out.println("Dealer's other card was the " + dealerHand.getCard(1));
					return false;
				}
			}

		}
		System.out.println();
		System.out.println("User stands.");
		System.out.println("Dealer's cards are");
		System.out.println("    " + dealerHand.getCard(0));
		System.out.println("    " + dealerHand.getCard(1));
		while (dealerHand.getBlackjackValue() <= 16) {
			Card newCard = deck.dealCard();
			System.out.println("Dealer hits and gets the " + newCard);
			dealerHand.addCard(newCard);
			if (dealerHand.getBlackjackValue() > 21) {
				System.out.println();
				System.out.println("Dealer busted by going over 21.  You win.");
				return true;
			}
		}
		System.out.println("Dealer's total is " + dealerHand.getBlackjackValue());

		System.out.println();
		if (dealerHand.getBlackjackValue() == userHand.getBlackjackValue()) {
			System.out.println("Dealer wins on a tie.  You lose.");
			return false;
		} else if (dealerHand.getBlackjackValue() > userHand.getBlackjackValue()) {
			System.out.println("Dealer wins, " + dealerHand.getBlackjackValue() + " points to "
					+ userHand.getBlackjackValue() + ".");
			return false;
		} else {
			System.out.println(
					"You win, " + userHand.getBlackjackValue() + " points to " + dealerHand.getBlackjackValue() + ".");
			return true;
		}
	}

}
