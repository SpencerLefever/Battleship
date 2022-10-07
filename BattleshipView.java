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
    JButton[][] oceanGrid;
    JRadioButton[][] targetGrid;


    //Ship Image and dropdown Menu
    JComboBox shipBox;
    ImageIcon shipIcon;

    //String arrays for the top and side bars
    //holding the coordinate values
    String[] gridTopStrings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] gridSideStrings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    JLabel[] gridNumLabels = new JLabel[GRID_LENGTH];
    JLabel[] gridLetterLabels = new JLabel[GRID_WIDTH];
    

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

      JButton[][] oceanGrid = new JButton[GRID_LENGTH][GRID_WIDTH];
      JRadioButton[][] targetGrid = new JRadioButton[GRID_LENGTH][GRID_WIDTH];

      //Initialize array of JButtons
      for(int i=0; i<GRID_LENGTH; i++) {
        for(int j=0; j<GRID_WIDTH; j++) {
          oceanGrid[i][j] = new JButton();
          targetGrid[i][j] = new JRadioButton();
        }
      }

      //Initialize array of JLabels
      for(int i=0; i<GRID_LENGTH; i++) {
        gridNumLabels[i] = new JLabel(gridTopStrings[i]);
        gridLetterLabels[i] = new JLabel(gridSideStrings[i]);
      }


      String[] shipList = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
      shipBox = new JComboBox<String>(shipList);
      shipBox.setVisible(true);
      shipBox.addActionListener(shipBox);

      createLayout(oceanGrid, targetGrid, shipBox, gridNumLabels, gridLetterLabels);
    }

    private void createLayout(JButton[][] oceanGrid, JRadioButton[][] targetGrid, JComboBox shipBox, JLabel[] gridNumLabels, JLabel[] gridLetterLabels ) {
      Container pane = getContentPane();
      GroupLayout gl = new GroupLayout(pane);
      pane.setLayout(gl);

      gl.setAutoCreateGaps(true);
      gl.setAutoCreateContainerGaps(true);


      gl.setHorizontalGroup(
        gl.createSequentialGroup()
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING) //This group holds the Letters for the grid
          .addGap(30)
          .addComponent(gridLetterLabels[0])
          .addGap(30)
          .addComponent(gridLetterLabels[1])
          .addGap(30)
          .addComponent(gridLetterLabels[2])
          .addGap(30)
          .addComponent(gridLetterLabels[3])
          .addGap(30)
          .addComponent(gridLetterLabels[4])
          .addGap(30)
          .addComponent(gridLetterLabels[5])
          .addGap(30)
          .addComponent(gridLetterLabels[6])
          .addGap(30)
          .addComponent(gridLetterLabels[7])
          .addGap(30)
          .addComponent(gridLetterLabels[8])
          .addGap(30)
          .addComponent(gridLetterLabels[9])
          .addGap(30))
          .addGap(30)
        .addGroup(gl.createParallelGroup() //This group hold the first column
          .addComponent(gridNumLabels[0])
          .addGap(30)
          .addComponent(targetGrid[0][0])
          .addGap(30)
          .addComponent(targetGrid[1][0])
          .addGap(30)
          .addComponent(targetGrid[2][0])
          .addGap(30)
          .addComponent(targetGrid[3][0])
          .addGap(30)
          .addComponent(targetGrid[4][0])
          .addGap(30)
          .addComponent(targetGrid[5][0])
          .addGap(30)
          .addComponent(targetGrid[6][0])
          .addGap(30)
          .addComponent(targetGrid[7][0])
          .addGap(30)
          .addComponent(targetGrid[8][0])
          .addGap(30)
          .addComponent(targetGrid[9][0])
          .addGap(30)
        )
      );

      gl.setVerticalGroup(
        gl.createParallelGroup()
        .addGroup(gl.createSequentialGroup() //This group holds the Letters for the grid
          .addGap(30)
          .addComponent(gridLetterLabels[0])
          .addGap(30)
          .addComponent(gridLetterLabels[1])
          .addGap(30)
          .addComponent(gridLetterLabels[2])
          .addGap(30)
          .addComponent(gridLetterLabels[3])
          .addGap(30)
          .addComponent(gridLetterLabels[4])
          .addGap(30)
          .addComponent(gridLetterLabels[5])
          .addGap(30)
          .addComponent(gridLetterLabels[6])
          .addGap(30)
          .addComponent(gridLetterLabels[7])
          .addGap(30)
          .addComponent(gridLetterLabels[8])
          .addGap(30)
          .addComponent(gridLetterLabels[9])
          .addGap(30))
        .addGroup(gl.createSequentialGroup() //This group holds the first column of the grid
        .addComponent(gridNumLabels[0])
        .addGap(30)
        .addComponent(targetGrid[0][0])
        .addGap(30)
        .addComponent(targetGrid[1][0])
        .addGap(30)
        .addComponent(targetGrid[2][0])
        .addGap(30)
        .addComponent(targetGrid[3][0])
        .addGap(30)
        .addComponent(targetGrid[4][0])
        .addGap(30)
        .addComponent(targetGrid[5][0])
        .addGap(30)
        .addComponent(targetGrid[6][0])
        .addGap(30)
        .addComponent(targetGrid[7][0])
        .addGap(30)
        .addComponent(targetGrid[8][0])
        .addGap(30)
        .addComponent(targetGrid[9][0])
        .addGap(30)
        )
      );


      /**
      gl.setHorizontalGroup(
        gl.createSequentialGroup()
          .addGroup(gl.createSequentialGroup()
            .addComponent(gridNumLabels[0])
            .addGap(30)
            .addComponent(gridNumLabels[1])
            .addGap(30)
            .addComponent(gridNumLabels[2])
            .addGap(30)
            .addComponent(gridNumLabels[3])
            .addGap(30)
            .addComponent(gridNumLabels[4])
            .addGap(30)
            .addComponent(gridNumLabels[5])
            .addGap(30)
            .addComponent(gridNumLabels[6])
            .addGap(30)
            .addComponent(gridNumLabels[7])
            .addGap(30)
            .addComponent(gridNumLabels[8])
            .addGap(30)
            .addComponent(gridNumLabels[9])
            
            )
          .addGroup(gl.createParallelGroup(null)
            .addComponent()
          )
      );

      gl.setVerticalGroup(
        gl.createSequentialGroup()
        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(gridNumLabels[0])
        .addComponent(gridNumLabels[1])
        .addComponent(gridNumLabels[2])
        .addComponent(gridNumLabels[3])
        .addComponent(gridNumLabels[4])
        .addComponent(gridNumLabels[5])
        .addComponent(gridNumLabels[6])
        .addComponent(gridNumLabels[7])
        .addComponent(gridNumLabels[8])
        .addComponent(gridNumLabels[9])
          )
      );*/
    
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