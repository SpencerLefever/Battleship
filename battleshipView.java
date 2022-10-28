import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class BattleshipView extends JFrame {
        //private battleshipModel gridModel;

        final int GRID_LENGTH = 10;
        final int GRID_WIDTH = 10;
        
        private JButton quitButton = new JButton("Quit");
        private JButton restartButton = new JButton("Restart");
        private JButton fireButton = new JButton("Fire");
        private JButton randomButton = new JButton("Randomize");
        private JButton confirmButton = new JButton("Confirm");
        private JPanel oceanGrid = new JPanel();
        private JPanel targetGrid = new JPanel();
        private JPanel shipContainer = new JPanel();
        private JPanel shipSelectionPanel = new JPanel();
        private JComboBox<String> shipSelection;
        private ImageIcon shipIcon;
        private JLabel shipLabel;
        private JRadioButton [][] oceanGridButtonArr = new JRadioButton[GRID_LENGTH][GRID_WIDTH];
        private JRadioButton [][] targetGridButtonArr = new JRadioButton[GRID_LENGTH][GRID_WIDTH];

        public BattleshipView(){

                //Set size of frame
                setSize(750,1000);
                //Set main frame to border layout
                setLayout(new BorderLayout(50, 50));

                //Initialize JComboBox and values
                String[] shipList = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
                shipSelection = new JComboBox<String>(shipList);
                shipSelection.setVisible(true);

                //Initialize ImageIcon to null value
                shipIcon = new ImageIcon("src/battleship.png");
                
                shipLabel = new JLabel(shipIcon, JLabel.CENTER);
                shipLabel.setVisible(true);

                //Set layout for shipSelectionPanel
                shipSelectionPanel.setLayout(new GridLayout(2,1,25,25));

                //Add ship combo box and icon to the Ship selection panel
                shipSelectionPanel.add(shipSelection);
                shipSelectionPanel.add(shipLabel);

                //Initialize oceanGridButtonArr
                for(int i = 0; i < GRID_LENGTH; i++) {
                        for(int j=0; j< GRID_WIDTH; j++) {
                                oceanGridButtonArr[i][j] = new JRadioButton();
                        }
                }

                //Initialize targetGridButtonArr
                for(int i = 0; i < GRID_LENGTH; i++) {
                        for(int j=0; j< GRID_WIDTH; j++) {
                               targetGridButtonArr[i][j] = new JRadioButton();
                        }
                }
                        
                // Layout Components
                JPanel gridPanel = new JPanel();
                JPanel buttonPanel = new JPanel();
                
                
                //Add components to button panel
                buttonPanel.setLayout(new GridLayout(3,1,25,25));
                buttonPanel.add(quitButton);
                buttonPanel.add(restartButton);
                buttonPanel.add(confirmButton);
                
                
                //Add components to grid panel
                gridPanel.setLayout(new GridLayout(2, 1, 50, 50));
                

                //Variable to hold the value for space between buttons in grid
                final int buttonSpacer = 5;

                //Set ocean and target grid panels to grid layout
                oceanGrid.setLayout(new GridLayout(GRID_LENGTH, GRID_WIDTH, buttonSpacer, buttonSpacer));
                targetGrid.setLayout(new GridLayout(GRID_LENGTH, GRID_WIDTH, buttonSpacer, buttonSpacer));

                //Add buttons to ocean grid panel
                for(int i = 0; i < GRID_LENGTH; i++) {
                        for(int j=0; j< GRID_WIDTH; j++) {
                                oceanGrid.add(oceanGridButtonArr[i][j]);
                                oceanGridButtonArr[i][j].setMaximumSize(new Dimension(5,5));
                        }
                }
                oceanGrid.setVisible(true);

                //Add buttons to target grid panel
                for(int i = 0; i < GRID_LENGTH; i++) {
                        for(int j=0; j< GRID_WIDTH; j++) {
                               targetGrid.add(targetGridButtonArr[i][j]);
                               targetGridButtonArr[i][j].setMaximumSize(new Dimension(5,5));
                        }
                }
                targetGrid.setVisible(true);

                //Add grid to the grid panel
                gridPanel.add(oceanGrid);
                gridPanel.add(targetGrid);                
                
                gridPanel.setVisible(true);
        

                
                
                
                //Add panels to main frame
                add(buttonPanel, BorderLayout.WEST);
                add(gridPanel, BorderLayout.CENTER);
                add(shipSelectionPanel, BorderLayout.EAST);
                
        

                // Finalize
                pack();
                setResizable(true);
                setTitle("BattleShip");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        }
        
        /*
        // Add listeners
        void addQuitListener(ActionListener q){
                quitButton.addActionListener(q);
        }

        void addRestartListener(ActionListener r){
                restartButton.addActionListener(r);
        }

        void addFireListener(ActionListener f){
                fireButton.addActionListener(f);
        }

        void addConfirmListener(ActionListener c){
                confirmButton.addActionListener(c);
        }

        void addRandomListener(ActionListener r){
                randomButton.addActionListener(r);
        }
        */

}