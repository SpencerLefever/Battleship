import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipView extends JFrame {
        private BattleshipModel gridModel;

        final int GRID_LENGTH = 10;
        final int GRID_WIDTH = 10;
        final int buttonSpacer = 5;

        private BattleshipModel battleshipModel;
        
        //Buttons to put along bottom of screen
        private JButton quitButton = new JButton("Quit");
        private JButton restartButton = new JButton("Restart");
        private JButton fireButton = new JButton("Fire");
        private JButton randomButton = new JButton("Randomize");
        private JButton confirmButton = new JButton("Confirm");
        private JPanel buttonPanel;

        //JComponents related to the grids
        private JPanel oceanGrid = new JPanel();
        private JPanel targetGrid = new JPanel();
        private JPanel gridPanel;
        public JButton [][] oceanGridButtonArr = new JButton[GRID_LENGTH][GRID_WIDTH];
        public JToggleButton [][] targetGridButtonArr = new JToggleButton[GRID_LENGTH][GRID_WIDTH];
        public ButtonGroup targetGridButtonGroup;
        public ButtonGroup oceanGridButtonGroup;
        private JTextField [] gridTopStringsArr;
        private JTextField [] gridSideStringsArr;
        String[] gridTopStrings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] gridSideStrings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};


        //JComponents related to ship selection panel
        private JPanel shipContainer = new JPanel();
        private JPanel shipSelectionPanel = new JPanel();
        private JComboBox<String> shipSelection;
        private ImageIcon shipIcon;
        private JLabel shipLabel;

        //JComponents related to header
        private JPanel headerPanel;
        private JTextField gameHeader;
       

        //JComponents realted to game chat log
        private JTextArea gameLog;
        private JPanel gameLogPanel;        


        public BattleshipView(BattleshipModel m){

                battleshipModel = m;
                //Set size of frame
                setSize(750,1000);
                //Set main frame to border layout
                setLayout(new BorderLayout(25, 25));

                initOceanGrid();

                initTargetGrid();

                initGridPanel();

                initButtonPanel();

                initShipSelectionPanel();

                initGameHeaderPanel();

                initGameLogPanel();

                addPanelsToMainFrame();
            

                // Finalize
                pack();
                setResizable(false);
                setTitle("Battleship");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        }

        void initGridStringArr() {
            initGridSideStringArr();
            initGridTopStringArr();
        }

        void initOceanGrid() {
            initOceanGridButtonArr();
            initOceanGridButtonGroup();
            initOceanGridPanel();
        }

        void initTargetGrid() {
            initTargetGridButtonArr();
            initTargetGridButtonGroup();
            initTargetGridPanel();
        }

        void initGameHeaderPanel() {
            headerPanel = new JPanel();
            headerPanel.setVisible(true);

            gameHeader = new JTextField("Welcome to Battleship");
            gameHeader.setEditable(false);
            gameHeader.setVisible(true);

            headerPanel.add(gameHeader);
        }

        void initGameLogPanel() {
            gameLogPanel = new JPanel();
            gameLogPanel.setVisible(true);

            gameLog = new JTextArea("Move History");
            gameLog.setVisible(true);
            gameLog.setEditable(false);

            gameLogPanel.add(gameLog);
        }


        //Method to initialize JComboBox and ship icons for border layout
        void initShipSelectionPanel() {
            //Initialize JComboBox and values
            String[] shipList = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
            shipSelection = new JComboBox<String>(shipList);
            shipSelection.setVisible(true);

            // //Initialize ImageIcon to null value
            // shipIcon = new ImageIcon("src/battleship.png");
            
            // shipLabel = new JLabel(shipIcon, JLabel.CENTER);
            //shipLabel.setVisible(true);

            //Set layout for shipSelectionPanel
            shipSelectionPanel.setLayout(new GridLayout(2,1,25,25));

            //Add ship combo box and icon to the Ship selection panel
            shipSelectionPanel.add(shipSelection);
            //shipSelectionPanel.add(shipLabel);

        }

        void initOceanGridButtonArr() {
            //Initialize oceanGridButtonArr
                for(int i = 0; i < GRID_LENGTH; i++) {
                        for(int j=0; j< GRID_WIDTH; j++) {
                                oceanGridButtonArr[i][j] = new JButton();
                        }
                }  
        }

        void initTargetGridButtonArr() {
             //Initialize targetGridButtonArr
                for(int i = 0; i < GRID_LENGTH; i++) {
                        for(int j=0; j< GRID_WIDTH; j++) {
                               targetGridButtonArr[i][j] = new JToggleButton();
                        }

                }           
        }

        void initTargetGridButtonGroup() {
                targetGridButtonGroup = new ButtonGroup();
                for(int i = 0; i < GRID_LENGTH; i++) {
                        for(int j=0; j< GRID_WIDTH; j++) {
                               targetGridButtonGroup.add(targetGridButtonArr[i][j]);
                        }

                }       
        }

        void initOceanGridButtonGroup() {
                oceanGridButtonGroup = new ButtonGroup();
                for(int i = 0; i < GRID_LENGTH; i++) {
                        for(int j=0; j< GRID_WIDTH; j++) {
                              oceanGridButtonGroup.add(oceanGridButtonArr[i][j]);
                        }

                }       
        }

        void initGridPanel() {
            // Layout Components
            gridPanel = new JPanel();

            //Add components to grid panel
            gridPanel.setLayout(new GridLayout(2, 1, 50, 50));
                
            //Add grid to the grid panel
            gridPanel.add(targetGrid);
            gridPanel.add(oceanGrid);                
            
            gridPanel.setVisible(true);
        }

        void initGridTopStringArr() {
            gridTopStringsArr = new JTextField[GRID_LENGTH];
            for(int i=0; i<GRID_LENGTH; i++) {
                gridTopStringsArr[i] = new JTextField(gridTopStrings[i]);
                gridTopStringsArr[i].setVisible(true);
                gridTopStringsArr[i].setEditable(false);

            }
        }

        void initGridSideStringArr() {
            gridSideStringsArr = new JTextField[GRID_LENGTH];
            for(int i=0; i<GRID_LENGTH; i++) {
                gridSideStringsArr[i] = new JTextField(gridSideStrings[i]);
                gridSideStringsArr[i].setVisible(true);
                gridSideStringsArr[i].setEditable(false);
            }
        }

        void initOceanGridPanel() {
            initGridStringArr();

            //Add blank text field in corner
            JTextField blank = new JTextField("");
            blank.setEditable(false);
            blank.setVisible(true);
            //Set ocean and target grid panels to grid layout
            oceanGrid.setLayout(new GridLayout(GRID_LENGTH + 1, GRID_WIDTH + 1, buttonSpacer, buttonSpacer));

            //Add blank space in corner of grid
            oceanGrid.add(blank);

             //Add buttons to ocean grid panel
             for(int i = 0; i < GRID_LENGTH; i++){
                oceanGrid.add(gridTopStringsArr[i]);
             }

                for(int i = 0; i < GRID_LENGTH; i++) {
                    oceanGrid.add(gridSideStringsArr[i]);
                        for(int j=0; j< GRID_WIDTH; j++) {
                                oceanGrid.add(oceanGridButtonArr[i][j]);
                                oceanGridButtonArr[i][j].setMaximumSize(new Dimension(5,5));
                        }
                }
                oceanGrid.setVisible(true);
        }

        void initTargetGridPanel() {
            initGridStringArr();

             //Add blank text field in corner
            JTextField blank = new JTextField("");
            blank.setEditable(false);
            blank.setVisible(true);

            targetGrid.setLayout(new GridLayout(GRID_LENGTH+1, GRID_WIDTH+1, buttonSpacer, buttonSpacer));

            //Add blank space in corner of the grid
            targetGrid.add(blank);


             //Add buttons to ocean grid panel
             for(int i = 0; i < GRID_LENGTH; i++){
                targetGrid.add(gridTopStringsArr[i]);
             }

             //Add buttons to target grid panel
                for(int i = 0; i < GRID_LENGTH; i++) {
                    targetGrid.add(gridSideStringsArr[i]);
                        for(int j=0; j< GRID_WIDTH; j++) {
                               targetGrid.add(targetGridButtonArr[i][j]);
                               targetGridButtonArr[i][j].setMaximumSize(new Dimension(5,5));
                        }
                }
                targetGrid.setVisible(true);
        }


        void initButtonPanel() {
            buttonPanel = new JPanel();
                
                
            //Add components to button panel
            buttonPanel.setLayout(new GridLayout(1,3,25,25));
            buttonPanel.add(fireButton);
            buttonPanel.add(confirmButton);
            buttonPanel.add(restartButton);
        
        }

        void addFireListener(ActionListener f){
                fireButton.addActionListener(f);
         }

         void addConfirmListener(ActionListener c){
                confirmButton.addActionListener(c);
         }

         void addRestartListener(ActionListener r){
                restartButton.addActionListener(r);
         }

         void addTargetButtonListener(ActionListener t) {
                for(int i = 0; i < 10; i++) {
                        for(int j=0; j< 10; j++) {
                                targetGridButtonArr[i][j].addActionListener(t);
                        }
                }
         }

        void addPanelsToMainFrame() {
                //Add grid to the grid panel
                gridPanel.add(targetGrid);
                gridPanel.add(oceanGrid);                
                
                gridPanel.setVisible(true);
                        
                //Add panels to main frame
                add(buttonPanel, BorderLayout.PAGE_END);
                add(gridPanel, BorderLayout.CENTER);
                add(shipSelectionPanel, BorderLayout.EAST);
                add(gameLogPanel, BorderLayout.WEST);
                add(headerPanel, BorderLayout.NORTH);
                
        }

}