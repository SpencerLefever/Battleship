import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipController{
    private BattleshipView view;
    private BattleshipModel model;

    BattleshipController(BattleshipModel m, BattleshipView v){
        view = v;
        model = m;

        view.addFireListener(new FireListener());
        view.addConfirmListener(new ConfirmListener());
        view.addRestartListener(new RestartListener());
        view.addTargetButtonListener(new TargetButtonListener());

    }

 class FireListener implements ActionListener{
     public void actionPerformed(ActionEvent e){
        //TODO Find coordinate of buttons pressed
        for(int i = 0; i < 10; i++) {
                        for(int j=0; j< 10; j++) {
                            if(view.targetGridButtonArr[i][j].isSelected())
                            {
                                System.out.println("Button coordinate " + i + " " + j);
                                model.fire(i,j);
                            }
                        }
                }
     }
 }


class ConfirmListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.out.println("Confirmed");
    }
}

class RestartListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.out.println("Restarted");
    }
}

class TargetButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        //get source of button
        JToggleButton btn = (JToggleButton) e.getSource();
        
        //
    }
}

// class TargetGridButtonListener implements ActionListener{
//     public void actionPerformed(ActionEvent e) {        
//         //loop through buttons in array
//         for(int i = 0; i < 10; i++) {
//                         for(int j=0; j< 10; j++) {
//                             if(view.targetGridButtonArr[i][j].getModel().isPressed())
//                             {
//                                 System.out.println(i + " " + j);
//                                 //view.targetGridButtonArr[i][j];
//                             }
//                         }
//                 }
//         //If the button is not the one that was most recently pressed, deactivate
//     }
// }


}

