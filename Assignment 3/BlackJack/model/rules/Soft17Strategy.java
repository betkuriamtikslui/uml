package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

public class Soft17Strategy implements IHitStrategy {
	private final int g_hitLimit = 17;

	public boolean DoHit(Player a_dealer) {
		if (a_dealer.CalcScore() == g_hitLimit) {
			boolean hasOneAce = false;
			for (Card c : a_dealer.GetHand()) {
				c.Show(true);
				if (c.GetValue() == Card.Value.Ace) {
					hasOneAce = true;
				}
			}

			if (hasOneAce == true) {
				return true;
			} else {
				return false;
			}
		} else {
			return a_dealer.CalcScore() < g_hitLimit;
		}

	}
}
