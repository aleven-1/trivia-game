
/**
 * experiments with printing to a screen
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class PrintExperiment
{
    public static void main(String[] args)
    {
        String tester = "hello my name is archibald";
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea shower = new JTextArea(6,30);
        JScrollPane scrollPane = new JScrollPane(shower);
        shower.setEditable(false);
        frame.add(scrollPane);
        frame.setSize(500,500);
        frame.setVisible(true);

        /*
        for(int i = 0; i < 6; i++)
        {
        shower.append("p");
        }
         */
        /*
        for(int i = 0; i < 25; i++)
        {
        try {Thread.sleep(500); } catch(Exception e) {}
        shower.append("p");
        }
         */
        
        String otherTester = "hello my name is Grant Kim.";
        char[] otherTesterArray = new char[otherTester.length()];
        otherTesterArray = otherTester.toCharArray();
        for(int i = 0; i < otherTesterArray.length; i++)
        {
            try {Thread.sleep(500); } catch(Exception e) {}
            shower.append(String.valueOf(otherTesterArray[i]));
        }
        
      
    }

}
