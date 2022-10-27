/**
 * Spencer Lefever
 * COSC330 Project1 Battleship
 * 
 * Testing class for the battleship game
 * with the MVC design pattern
 */


public class BattleshipMVCTest {
    public static void main(String[] args) {

        // if(args.length != 2) {
        //     System.out.println("Error: Role and host name must be passed\nEnter null if role is server");
        //     System.exit(-1);
        // }

        BattleshipModel model = new BattleshipModel();
        BattleshipView view = new BattleshipView(model);
        BattleshipController controller = new BattleshipController(model, view);

        //controller.playGame();
    }
 }