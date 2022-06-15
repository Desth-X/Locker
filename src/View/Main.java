/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MainController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JWindow;

/**
 *
 * @author Taavet
 */
public class Main extends JWindow {

    MainController mainController;

    public Main() {

        mainController = new MainController(this);
        CreateGUI();
    }

    private void CreateGUI() {
        setBackground(new Color(0, 0, 0, 0));
        getContentPane().setLayout(null);
        getContentPane().setBackground(getBackground());
        for (int i = 1;
                i <= 11; i++) {
            getContentPane().add(CreateButton(String.valueOf(i)));
        }
        setBounds(0, 0, 375, 350);
    }

    public JButton CreateButton(String strActionCommand) {
        if(strActionCommand.equals("10")){
            strActionCommand="C";
        }
        if(strActionCommand.equals("11")){
            strActionCommand="0";
        }
        JButton jbtButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 175));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jbtButton.setOpaque(false);
        jbtButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtButton.setBorderPainted(false);
        jbtButton.setActionCommand(strActionCommand);
        jbtButton.addActionListener(mainController.actionListener);
        return jbtButton;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, y, w, h);
        int intSide = 2 * h / 13;
        Component[] aryComponents = getContentPane().getComponents();
        for (int i = 0; i < aryComponents.length; i += 3) {
            for (int j = 0; j < 3 && j + i < aryComponents.length; j++) {
                aryComponents[j + i].setBounds(
                        ((intSide / 2) * j) + (intSide * j) + intSide / 2,
                        (((intSide / 2) * i) / 3) + (i * intSide / 3) + intSide / 2,
                        intSide,
                        intSide);
            }
        }
    }

    public void setVisible(boolean flag){
        super.setVisible(flag);
        toBack();
    }
    
    public static void main(String[] artgs) {
        Main main = new Main();
        main.setVisible(true);
    }
}
