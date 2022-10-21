/**
 * Spencer Lefever
 * COSC330 Project1
 * 
 * Subject interface for BattleshipView
 */

 public interface Subject {
    
    public void addObserver(Observer o);

    public void deleteObserver(Observer o);

    public void notifyObserver(String message);
 }