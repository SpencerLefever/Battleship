/**
 * Spencer Lefever
 * COSC330 Project 1
 * 
 * Class to handle the dragging on 
 * an image in java
 */

import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class dndPractice extends JFrame {
   public dndPractice() {
    initUI();
   }

   private void initUI() {

    ImageIcon icon = new ImageIcon("Tux.png");

    JLabel label = new JLabel(icon, JLabel.LEFT);

    DragMouseAdapter listener = new DragMouseAdapter();
    label.addMouseListener(listener);

    JButton button = new JButton();
    button.setFocusable(false);

    label.setTransferHandler(new TransferHandler("icon"));
    button.setTransferHandler(new TransferHandler("icon"));

    createLayout(label, button);

    setTitle("Drag and Drop Practice");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
   }

   private class DragMouseAdapter extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
        JComponent c = (JComponent) e.getSource();
        TransferHandler handler = c.getTransferHandler();
        handler.exportAsDrag(c, e, TransferHandler.COPY);
    }
   }

   private void createLayout(JLabel l , JButton b) {
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(l)
                        .addGap(30)
                        .addComponent(b)
                        .addGap(30))
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(l)
                        .addComponent(b)
                .addGap(30))
        );
        pack();
   }
   
}
