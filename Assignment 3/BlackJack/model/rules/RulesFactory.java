package BlackJack.model.rules;

public class RulesFactory {

  public IHitStrategy GetHitRule() {
	  
    return new Soft17Strategy();
  }

  public INewGameStrategy GetNewGameRule() {
    return new AmericanNewGameStrategy();
  }
 
  public WinRule getWinRule(){
	  return new PlayerWinsOnEqual();
	  //return new DealerWinsOnEqual();
  }
}