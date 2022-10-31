import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipView extends JFrame {
        //private battleshipModel gridModel;

        final int GRID_LENGTH = 10;
        final int GRID_WIDTH = 10;
        final int buttonSpacer = 5;

        private BattleshipModel battleshipModel;
        
        //Buttons to put along bottom of screen
        private JButton quitButton = new JButton("Quit");
        public JButton confirmShipButton = new JButton("Confirm Ship");
        public JButton fireButton = new JButton("Fire");
        public JButton randomButton = new JButton("Randomize");
        public JButton confirmButton = new JButton("Confirm Grid");
        private JPanel buttonPanel;
        public JButton rotateShipButton = new JButton("Rotate Ship");

        //JComponents related to the grids
        private JPanel oceanGrid = new JPanel();
        private JPanel targetGrid = new JPanel();
        private JPanel gridPanel;
        public JToggleButton [][] oceanGridButtonArr = new JToggleButton[GRID_LENGTH][GRID_WIDTH];
        public JToggleButton [][] targetGridButtonArr = new JToggleButton[GRID_LENGTH][GRID_WIDTH];
        public ButtonGroup targetGridButtonGroup;
        public ButtonGroup oceanGridButtonGroup;
        private JTextField [] gridTopStringsArr;
        private JTextField [] gridSideStringsArr;
        String[] gridTopStrings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] gridSideStrings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};


        //JComponents related to ship selection panel
        private JPanel shipContainer = new JPanel();
        public JPanel shipSelectionPanel = new JPanel();
        public JRadioButton [] shipSelectionButtonArr = new JRadioButton[5];
        public ButtonGroup shipSelectionButtonGroup;
        private ImageIcon shipIcon;
        public JPanel shipButtonArrPanel;

        //JComponents related to header
        private JPanel headerPanel;
        private JTextField gameHeader;
       

        //JComponents realted to game chat log
        private JTextField gameLogHeader;
        public JTextArea gameLog;
        private JPanel gameLogPanel;        


        public BattleshipView(BattleshipModel m, String networkRoleString){

                battleshipModel = m;
                //Set size of frame
                setSize(new Dimension(1500, 1500));
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

                initIconImage();
            

                // Finalize
                pack();
                setResizable(true);
                setTitle("Battleship " + networkRoleString);
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
            //Initalize panel and set border
            gameLogPanel = new JPanel();
            gameLogPanel.setVisible(true);
            gameLogPanel.setLayout(new GridLayout(2,1,0,0));

            //Initialize header
            gameLogHeader = new JTextField("Move History");
            gameLogHeader.setVisible(true);
            gameLogHeader.setEditable(false);

            //Initialize game log
            gameLog = new JTextArea();
            gameLog.setVisible(true);
            gameLog.setEditable(false);

            gameLogPanel.add(gameLogHeader);
            gameLogPanel.add(gameLog);
        }

        void initIconImage(){
            //Image ic = new Image("source/logo.png");
            setIconImage(new ImageIcon("source/logo.png").getImage());
        }

        //Method to initialize JComboBox and ship icons for border layout
        void initShipSelectionPanel() {
            //Initialize JComboBox and values
            String[] shipList = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
            //shipSelection = new JComboBox<String>(shipList);
            shipContainer.setLayout(new GridLayout(5, 1, 5, 5));
            shipButtonArrPanel = new JPanel();

            for(int i = 0; i < 5; i++){
                shipSelectionButtonArr[i] = new JRadioButton(shipList[i]);
                shipSelectionButtonArr[i].setVerticalTextPosition(SwingConstants.TOP);
                shipSelectionButtonArr[i].setVisible(true);
                shipContainer.add(shipSelectionButtonArr[i]);
            }

            initShipSelectionButtonGroup();

            //Set layout for shipSelectionPanel
            shipSelectionPanel.setLayout(new GridLayout(2,1,25,25));

            shipButtonArrPanel.setVisible(true);
            //Add ship combo box and icon to the Ship selection panel
            shipSelectionPanel.add(shipContainer);
            shipSelectionPanel.add(shipButtonArrPanel);

        }

        void initShipSelectionButtonGroup(){
            shipSelectionButtonGroup = new ButtonGroup();

            for(int i = 0; i < 5; i++){
                shipSelectionButtonGroup.add(shipSelectionButtonArr[i]);
            }
        }

        void initOceanGridButtonArr() {
            //Initialize oceanGridButtonArr
                for(int i = 0; i < GRID_LENGTH; i++) {
                        for(int j=0; j< GRID_WIDTH; j++) {
                                oceanGridButtonArr[i][j] = new JToggleButton();
                                oceanGridButtonArr[i][j].setPreferredSize(new Dimension(10,10));
                        }
                }  
        }

        void initTargetGridButtonArr() {
             //Initialize targetGridButtonArr
                for(int i = 0; i < GRID_LENGTH; i++) {
                        for(int j=0; j< GRID_WIDTH; j++) {
                               targetGridButtonArr[i][j] = new JToggleButton();
                               targetGridButtonArr[i][j].setPreferredSize(new Dimension(10,10));
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
            oceanGrid.setLayout(new GridLayout(GRID_LENGTH+1, GRID_WIDTH+1, buttonSpacer, buttonSpacer));

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
                        }
                }
                targetGrid.setVisible(true);
        }


        void initButtonPanel() {
            buttonPanel = new JPanel();
                
            fireButton.setEnabled(false);
            confirmButton.setEnabled(false);
            confirmShipButton.setEnabled(false);
            rotateShipButton.setEnabled(false);
                
            //Add components to button panel
            buttonPanel.setLayout(new GridLayout(1,5,25,25));
            buttonPanel.add(fireButton);
            buttonPanel.add(confirmButton);
            buttonPanel.add(confirmShipButton);
            buttonPanel.add(rotateShipButton);              
            // for(int i = 0; i < 10; i++) {
                      //  for(int j=0; j< 10; j++) {
                    //            targetGridButtonArr[i][j].addActionListener(t);
                  //      }
                //}
            buttonPanel.add(randomButton);        
        }

        void addFireListener(ActionListener f){
                fireButton.addActionListener(f);
         }

         void addConfirmListener(ActionListener c){
                confirmButton.addActionListener(c);
         }

         void addConfirmShipListener(ActionListener r){
                confirmShipButton.addActionListener(r);
         }

         void addRandomListener(ActionListener r){
                randomButton.addActionListener(r);
         }

         void addRotateShipButtonListener(ActionListener a) {
                rotateShipButton.addActionListener(a);
         }

        
         void addShipSelectionListener(ActionListener s){
                for(int i = 0; i < 5; i++){
                    shipSelectionButtonArr[i].addActionListener(s);
                }
         }
         


         //TODO Figure out how to update grid after player sends a shot back
         void addTargetGridListener(DocumentListener a) {
            //Add the action listener to the JText area holding history of shots
            gameLog.getDocument().addDocumentListener(a);
         }

         void addOceanGridListener(DocumentListener a) {
            //Add the action listener to the JText area holding history of shots
            gameLog.getDocument().addDocumentListener(a);
         }


         void addupdateGridListener(ActionListener u){
                    for(int i = 0; i < 10; i++) {
                        for(int j=0; j< 10; j++) {
                                oceanGridButtonArr[i][j].addActionListener(u);
                        }
                }
         }

         void addOceanGridButtonListener(ActionListener o){
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    oceanGridButtonArr[i][j].addActionListener(o);
                }
            }
         }

         

        //  void addTargetButtonListener(ActionListener t) {
        //         for(int i = 0; i < 10; i++) {
        //                 for(int j=0; j< 10; j++) {
        //                         targetGridButtonArr[i][j].addActionListener(t);
        //                 }
        //         }
        //  }

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