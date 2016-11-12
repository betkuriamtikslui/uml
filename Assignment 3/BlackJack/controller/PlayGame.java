package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;
import BlackJack.model.Observer;

public class PlayGame implements Observer {
	Game a_game;
	IView a_view;

	public PlayGame(Game a_game, IView a_view) {
		this.a_game = a_game;
		this.a_view = a_view;
		a_game.registerObserver(this);
	}

	public boolean Play() {
		a_view.DisplayWelcomeMessage();

		displayHands();
		if (a_game.IsGameOver()) {
			a_view.DisplayGameOver(a_game.IsDealerWinner());
		}

		int input = a_view.GetInput();

		if (a_view.selectNewGame(input)) {
			a_game.NewGame();
		} else if (a_view.selectHit(input)) {
			a_game.Hit();
		} else if (a_view.selectStand(input)) {
			a_game.Stand();
		}
		return !a_view.selectQuit(input);
	}
	private void displayHands(){
		a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
		a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		
		}
	}
	@Override
	public void updateScreen() {
		displayHands();
	}
}