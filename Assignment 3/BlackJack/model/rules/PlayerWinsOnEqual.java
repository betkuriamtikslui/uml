package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public class PlayerWinsOnEqual implements WinRule {
	private static int max = 21;
	@Override
	public boolean playerWon(Dealer a_dealer, Player a_player) {
		if(a_player.CalcScore() <= max && a_player.CalcScore() <=a_dealer.CalcScore()){
			return true;
		}else{
			return false;
		}
	}

}
