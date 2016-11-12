package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public class DealerWinsOnEqual implements WinRule{

	private final int max = 21;
	@Override
	public boolean isDealerWinner(Dealer a_dealer, Player a_player) {
		// TODO Auto-generated method stub
		if(a_player.CalcScore() > max ){
			return true;
		}else if(a_dealer.CalcScore() > max){
			return false;
		}
		return a_player.CalcScore() <= a_dealer.CalcScore();
	}

}
