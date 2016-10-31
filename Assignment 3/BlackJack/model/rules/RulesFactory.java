package BlackJack.model.rules;

public class RulesFactory {

  public IHitStrategy GetHitRule() {
    return new BasicHitStrategy();
  }

  public INewGameStrategy GetNewGameRule() {
    return new AmericanNewGameStrategy();
  }
  public IHitStrategy GetSoft17Strategy(){
	  return new Soft17Strategy();
  }
  public WinRule getWinRule(){
	  return new PlayerWinsOnEqual();
  }
}