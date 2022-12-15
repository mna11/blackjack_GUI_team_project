
public class Dealer {
	
	CardDeck deck;
	
	public Dealer() {
		deck = new CardDeck();
	}
	
	public void dealTo(CardPlayerBehavior p) {
		while (p.wantsACard()) {
			Card c = deck.newCard();
			p.receiveCard(c);
		}
	}
	
	public void dealOneTo(CardPlayerBehavior p) {
		Card c = deck.newCard();
		p.receiveCard(c);
	}

}
