import java.util.ArrayList;
import java.util.*;;

public class Hand {

	private ArrayList<Card> hand;

	public Hand() {
		hand = new ArrayList<Card>();
	}

	public void addCard(Card c) {
		if (c != null)
			hand.add(c);
	}

	public int getCardCount() {
		return hand.size();
	}

	public Card getCard(int position) {
		if (position >= 0 && position < hand.size())
			return (Card) hand.get(position);
		else
			return null;
	}

	public int getBlackjackValue() {

		int val = 0;
		boolean ace = false;
		int cards = getCardCount();
		for (int i = 0; i < cards; i++) {
			Card card = getCard(i);
			int cardVal = card.getValue();
			if (cardVal > 10) {
				cardVal = 10; // For a Jack, Queen, or King.
			}
			if (cardVal == 1) {
				ace = true;
			}
			val = val + cardVal;
		}

		if (ace == true && val + 10 <= 21)
			val = val + 10;

		return val;

	}
}
