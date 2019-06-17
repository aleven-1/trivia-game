
/**
 * Protobowl window
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ProtobowlDisplay implements KeyListener
{
    private JFrame protobowl;
    //private static ArrowListener listener;
    private Database database;
    // text area that display the question
    private JTextArea questionDisplay;
    // text area that displays the score
    private JTextArea scoreDisplay;
    // text area that displays answer after the end
    private JTextArea answerDisplay;
    private JButton next;
    private JButton buttonSlow;
    private JButton buttonFast;

    public ProtobowlDisplay()
    {
        protobowl = new JFrame();
        protobowl.setTitle("Protobowl");
        protobowl.setSize(1200,500);
        protobowl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = protobowl.getContentPane();

        // trying something to better format the different components of the window
        GridLayout protobowlLayout = new GridLayout(3,2);
        pane.setLayout(protobowlLayout);

        questionDisplay = new JTextArea(200,300);
        scoreDisplay = new JTextArea(100,100);
        answerDisplay = new JTextArea(100,100);
        questionDisplay.setEditable(false);
        scoreDisplay.setEditable(false);
        answerDisplay.setEditable(false);
        questionDisplay.addKeyListener(this);

        // changes the font to make the score bigger
        scoreDisplay.setFont(new Font("Serif",Font.PLAIN,200));

        // the last two parameters in the constructor make the scrolling only vertical
        JScrollPane scrollPane = new JScrollPane(questionDisplay,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        next = new JButton("Next Question");
        buttonSlow = new JButton("<<");
        buttonFast = new JButton(">>");

        

        pane.add(scrollPane);
        pane.add(scoreDisplay);
        pane.add(answerDisplay);
        pane.add(next);
        pane.add(buttonSlow);
        pane.add(buttonFast);
        protobowl.setVisible(true);

    }


    public JFrame getJFrame()
    {
        return protobowl;
    }

    public JTextArea getJTextArea()
    {
        return questionDisplay;
    }

    public JTextArea getJTextArea2()
    {
        return scoreDisplay;
    }

    public JTextArea getJTextArea3()
    {
        return answerDisplay;
    }

    public JButton getButton1()
    {
        return next;
    }

    public JButton getButton2()
    {
        return buttonSlow;
    }

    public JButton getButton3()
    {
        return buttonFast;
    }
    // methods involved with keyboard interaction
    public void keyTyped(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
        if (listener == null)
            return;

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE)
            listener.spacePressed();
        else if (code == KeyEvent.VK_RIGHT)
            listener.rightPressed();
    }

    public void setArrowListener(ArrowListener listener)
    {
        this.listener = listener;
    }
}
