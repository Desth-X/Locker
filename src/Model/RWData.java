/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Taavet
 */
public class RWData {

    public String[][] ReadData() {
        System.out.println("Reading data:RWData.Java - ReadData()");
        String[][] aryData = new String[0][2];
        try {
            FileInputStream fileInputStream = new FileInputStream("data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String strCommandData = "";
            boolean bolRead = bufferedReader.read() == 65279;
            while (bolRead) {
                int intCharValue = bufferedReader.read();
                if (intCharValue != '\r') {
                    if (intCharValue == '\n' || intCharValue == -1) {
                        String[][] aryDataBackUp = aryData;
                        aryData = new String[aryData.length + 1][2];
                        System.arraycopy(aryDataBackUp, 0, aryData, 0, aryDataBackUp.length);
                        aryData[aryData.length - 1] = strCommandData.split(";");
                        strCommandData = "";
                        if (intCharValue == -1) {
                            bolRead = false;
                        }
                    } else {
                        strCommandData += String.valueOf((char) intCharValue);
                    }
                }
            }
            for (String[] arySingleData : aryData) {
                System.out.println(Arrays.toString(arySingleData));
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RWData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RWData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RWData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aryData;
    }

    public void WriteData(String strData) {
        try {
            OutputStream out;
            out = new BufferedOutputStream(new FileOutputStream("data.txt"));
            out.write(("13;C:\\Program Files\\Google\\Chrome\\Application\\Chrome.exe" + "\r\n" + "2222;Example2").getBytes());
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(RWData.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
