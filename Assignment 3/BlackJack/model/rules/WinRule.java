package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public interface WinRule {

	public boolean isDealerWinner(Dealer a_dealer, Player a_player);
}
