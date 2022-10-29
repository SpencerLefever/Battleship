/**
 * Spencer Lefever
 * Testing class for battleship view
 */

 public class BattleshipViewTest {
    public static void main(String[] args) {
        BattleshipModel m = new BattleshipModel();
        BattleshipView view = new BattleshipView(m);
        BattleshipController c = new BattleshipController(m, view);

        view.setVisible(true);
    }
 }