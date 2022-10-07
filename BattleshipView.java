/**
 * Spencer Lefever 
 * COSC330 Project 1 Battleship View
 * 
 * View class for a player's gui
 */

 import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicDesktopIconUI.MouseInputHandler;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

 public class BattleshipView extends JFrame{

    final int GRID_LENGTH = 10, GRID_WIDTH = 10;
    //Declare gui components
    JFrame mainFrame;

    //Player Grids
    /*
    JInternalFrame oceanGrid;
    JInternalFrame targetGrid;
    */
    JButton [] oceanGrid;
    JButton [] targetGrid;


    //Ship Image and dropdown Menu
    JComboBox shipBox;
    ImageIcon shipIcon;

    //GUI buttons
    JButton confirmShips;
    JButton randomizeShips;
    JButton restartButton;

    public BattleshipView() {
      initGUI();
    }

    private void initGUI() {
      mainFrame = new JFrame();

      /*
      oceanGrid = new JInternalFrame();
      oceanGrid.setTitle("Ocean Grid");
      oceanGrid.setVisible(true);
      targetGrid = new JInternalFrame();
      targetGrid.setTitle("Target Grid");
      targetGrid.setVisible(true);
      */

      JButton [] oceanGrid = new JButton[GRID_LENGTH * GRID_WIDTH];
      JButton [] targetGrid = new JButton[GRID_LENGTH * GRID_WIDTH];

      //TODO INITIALIZE THE JBUTTONS


      String[] shipList = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
      shipBox = new JComboBox<String>(shipList);
      shipBox.setVisible(true);
      shipBox.addActionListener(shipBox);

      createLayout(oceanGrid, targetGrid, shipBox);
    }

    private void createLayout(JButton[] oceanGrid, JButton[] targetGrid, JComboBox shipBox ) {
      Container pane = getContentPane();
      GroupLayout gl = new GroupLayout(pane);
      pane.setLayout(gl);

      gl.setAutoCreateGaps(true);
      gl.setAutoCreateContainerGaps(true);

      gl.setHorizontalGroup(
        gl.createSequentialGroup()
          .addGroup(gl.createSequentialGroup()
            .addComponent(oceanGrid[0])
            .addComponent(oceanGrid[1])
            .addComponent(oceanGrid[2])
            .addComponent(oceanGrid[3])
            .addComponent(oceanGrid[4])
            )
      );

      gl.setVerticalGroup(
        gl.createSequentialGroup()
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addComponent(oceanGrid[0])
          .addComponent(oceanGrid[1])
          .addComponent(oceanGrid[2])
          .addComponent(oceanGrid[3])
          .addComponent(oceanGrid[4])

          )
      );
    
      pack();
    }

    public void actionPerformed(ActionEvent e) {
      JComboBox cb = (JComboBox)e.getSource();
      String shipName = (String)cb.getSelectedItem();
      updateLabel(shipName);
    }

    public void updateLabel(String shipName) {
      
    }
 }