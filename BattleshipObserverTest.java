/**
 * Spencer Lefever
 * COSC330 Project1 Battleship
 * 
 * Testing class to make sure the observer
 * pattern is functional for the view
 */


 public class BattleshipObserverTest {
    public static void main(String[] args) {
        BattleshipView view = new BattleshipView();

        for(int i=0; i<5; i++) {
            view.notifyObserver("Message number " + i + "\n");
        }
    }
 }