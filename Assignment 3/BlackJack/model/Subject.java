package BlackJack.model;

public interface Subject {
	public void addObserver(Observer observer);
	public void notifyObserver();
}
