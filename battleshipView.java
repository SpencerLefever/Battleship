public class battleshipView extends JFrame {
        //private battleshipModel gridModel;
        
        private JButton quitButton = new JButton("Quit");
        private JButton restartButton = new JButton("Restart");
        private JButton fireButton = new JButton("Fire");
        private JButton randomButton = new JButton("Randomize");
        private JButton confirmButton = new JButton("Confirm");
        private JPanel oceanGrid = new JPanel();
        private JPanel targetGrid = new JPanel();
        private JPanel shipContainer = new JPanel();
        private JTextField test = new JTextField("Test");
        private JTextField test2 = new JTextField("Test2");
        private JButton [] j = new JButton[5];

        public battleshipView(){
        for(int i = 0; i < 5; i++) {
        	j[i] = new JButton();
        }
        	
        // Layout Components
        //JPanel mainFrame = new JPanel();
        JPanel gridPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        
        
        //Add components to button panel
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(quitButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(confirmButton);
        
        setLayout(new BorderLayout());
//        mainFrame.add(quitButton);
//        mainFrame.add(fireButton);
//        mainFrame.add(randomButton);
//        mainFrame.add(confirmButton);
//        mainFrame.add(restartButton);
//        mainFrame.add(oceanGrid);
//        //mainFrame.add(targetGrid);
//        mainFrame.add(shipContainer);
        
       //Add components to grid panel
        gridPanel.setLayout(new GridLayout(2, 1));
        oceanGrid.setLayout(new GridLayout(5, 1));
        for(int i = 0; i < 5; i++) {
        oceanGrid.add(j[i]);
        }
        targetGrid.add(test2);
        gridPanel.add(oceanGrid);
        gridPanel.add(targetGrid);
        
        gridPanel.setSize(100, 200);
        oceanGrid.setVisible(true);
        targetGrid.setVisible(true);
        gridPanel.setVisible(true);
       

        
        
        
        //Add panel to main frame
        add(buttonPanel, BorderLayout.LINE_START);
        add(gridPanel, BorderLayout.CENTER);
   

        // Finalize
        //this.add(mainFrame);
        this.pack();
        this.setTitle("BattleShip");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        }
        
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

}
