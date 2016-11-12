package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

public class Soft17Strategy extends BasicHitStrategy {
	private final int g_hitLimit = 17;

	@Override
	public boolean DoHit(Player a_dealer) {
		if (a_dealer.CalcScore() == g_hitLimit) {
			boolean hasOneAce = false;
			int otherCardsScore = 0;
			int cardScores[] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 1};
			
			for (Card c : a_dealer.GetHand()) {
				if (c.GetValue() == Card.Value.Ace && !hasOneAce) {
					hasOneAce = true;
				}else{
					otherCardsScore += cardScores[c.GetValue().ordinal()];
				}
			}

			if (hasOneAce == true && otherCardsScore == 6) {
				return true;
			} else {
				return false;
			}
		} else {
			return super.DoHit(a_dealer);
		}

	}
}
