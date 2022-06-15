/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.RWData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Main;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Taavet
 */
public class MainController extends Thread {

    String strCommand = "";
    public ActionListener actionListener;

    Main main;
    int intCounter = 3;
    RWData rwData;
    String[][] aryData;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "CallToThreadStartDuringObjectConstruction"})
    public MainController(Main main) {
        rwData = new RWData();
        aryData = rwData.ReadData();
        this.main = main;
        actionListener = (ActionEvent e) -> {
            validate(e);
        };
        start();
    }

    private void validate(ActionEvent e) {
        if (e.getActionCommand().equals("C")) {
            aryData = rwData.ReadData();
            strCommand = "";
            intCounter = -1;
        } else {
            strCommand += e.getActionCommand();
            System.out.println(strCommand);
            intCounter = 3;
        }
        for (String[] arySingleData : aryData) {
            if (arySingleData[0].equals(strCommand)) {
                try {
                    Desktop.getDesktop().open(new File(arySingleData[1]));
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
        if (strCommand.equals("1112")) {
            System.out.println("trying to create file");
            new RWData().WriteData("It worked");
        }
        if (strCommand.equals("1111")) {
            System.exit(0);
        }
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        try {
            while (true) {
                sleep(1000);
                intCounter--;
                if (intCounter == 0) {
                    strCommand = "";
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

}
