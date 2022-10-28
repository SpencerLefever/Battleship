import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipModel{
    public Player p = new Player(10, 10);

    public void fire(int x, int y){
        Shot s = new Shot(x, y);
        //p.fireShot(Grid targetGrid, Shot s);

        //Send shot data to opponent
    }
}