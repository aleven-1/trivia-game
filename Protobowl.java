/**
 * plays the Protobowl game
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Protobowl 
{
    private static ProtobowlDisplay display;
    private static JTextArea questionDisplay;
    private static JTextArea scoreDisplay;
    private static Database database;
    private static JTextArea answerDisplay;
    private static JButton first;
    private static JButton second;
    private static JButton third;

    // perhaps i should make an instance variable that triggers the next question when n is pressed
    // pauses the print function when space bar is pressed
    private boolean pause;
    // keeps track of the player's score
    private static int score;
    // keeps track of the answer to the current question
    private static String answer;
    // triggers the next question
    private static int nextQuestion;
    // determines whether to break out of printing for loop
    private boolean questionDone;
    // determines how fast the question prints
    private static int displaySpeed;

    public static void main(String[] args) throws IOException
    {
        display = new ProtobowlDisplay();
        Protobowl protobowl = new Protobowl(display);
        database = new Database();
        //display.setArrowListener(protobowl);
        displaySpeed = 100;

        boolean pause = false;
        // boolean nextQuestion = false;
        boolean questionDone = false;
        nextQuestion = 0;
        score = 0;
        protobowl.updateScore(score);

        ActionListener firstEvent = new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    if(answer != null)
                    {
                        answerDisplay.setText("Last Question's Answer: " + answer);
                    }
                    nextQuestion += 10;

                }
            };

        first.addActionListener(firstEvent);

        ActionListener secondEvent = new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    displaySpeed += 50;
                }
            };

        second.addActionListener(secondEvent);

        ActionListener thirdEvent = new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    displaySpeed -= 50;
                }
            };

        third.addActionListener(thirdEvent);

        // temporary
        // List<String> questionToPrint = protobowl.chooseQuestion(database).getWordArray();
        // protobowl.print(questionToPrint);

        //for buttons

        /**
         * i have found the issue. as it stands, the right press is not working properly
         * why doesn't changing the instance variable do what it's supposed. the mechanism has
         * worked in all the other ways
         */

        /**
         * also, add a function that displays the correct answer if the user doesn't get it
         * however, put a sleep function to make it so the user has some time before the questions end to 
         * answer
         * 
         * if you can get the pause in between function right, then you can put the answer in the 
         * score display box before it disappear before the next question
         * 
         * maybe instead of doing true false, you should use a number, >0 then do some kind of function
         * instead of changing some variable
         */
        while(true)
        {
            try {Thread.sleep(500); } catch(Exception e) {}
            if(nextQuestion > 0)
            {
                protobowl.play();
                questionDisplay.append("\n");
                questionDisplay.append("\n");
                nextQuestion -= 10;
            }
        }
        /*
        while(true)
        {
        try {Thread.sleep(200); } catch(Exception e) {}
        if(nextQuestion == true)
        {
        protobowl.play();
        nextQuestion = false;
        }
        }
         */

    }

    public Protobowl(ProtobowlDisplay display)
    {
        this.display = display;
        this.questionDisplay = display.getJTextArea();
        this.scoreDisplay = display.getJTextArea2();
        this.answerDisplay = display.getJTextArea3();
        this.first = display.getButton1();
        this.second = display.getButton2();
        this.third = display.getButton3();

    }

    // pulls a random question from the database of questions
    public Question chooseQuestion(Database data)
    {
        int number = (int) (Math.random() * data.getNumQuestions());
        answer = data.getDatabaseList().get(number).getAnswer();
        return data.getDatabaseList().get(number);
    }

    // creates a popup window with an answer prompt when space bar is pressed
    public void spacePressed()
    {
        pause = true;
        String inputStr = JOptionPane.showInputDialog("Answer:");
        // replace with the three answer checking methods

        // temporary so null pointer exception is not thrown
        if(inputStr == null)
        {
            inputStr = "hello";
        }

        if(perfect(inputStr,answer) || anagram(inputStr,answer) || oneletterwrong(inputStr,answer))
        {
            questionDone = true;
            score += 10;
            updateScore(score);
        }

    }

    // checking methods
    public static boolean perfect(String guess, String answer)
    {
        if (guess.compareTo(answer) == 0) return true;
        else return false;
    }

    public boolean anagram(String guess, String answer)
    {
        int[] charCnt = new int[256];
        for(int i = 0; i < guess.length(); i++){
            charCnt[guess.charAt(i)]++;
        }
        for(int i = 0; i< answer.length(); i++){
            charCnt[answer.charAt(i)]--;
        }
        for(int i = 0; i<charCnt.length; i++){
            if(charCnt[i] != 0) return false;
        }
        return true;
    }

    public boolean oneletterwrong(String guess, String answer)
    {
        int mistakesAllowed = 2;
        if(guess.equals(answer)) 
            return true;

        if(guess.length() == answer.length()) { 
            for(int i = 0; i < guess.length(); i++) { 
                if(guess.charAt(i) != answer.charAt(i)) { 
                    mistakesAllowed--; 
                    if(mistakesAllowed < 0) { 
                        return false; 
                    }
                }
            }
        }
        return true;
    }

    
    // makes the next question run the right arrow key is pressed
    public void rightPressed()
    {
        //temporary test
        //questionDisplay.append("t");

        // nextQuestion++;

    }

    public void print(List<String> s)
    {
        int count = 0;
        for(int i = 0; i < s.size(); i++)
        {
            // how do you make it pause for the amount of time that the window is up

            // so this part of the method does succeed in breaking the question printing
            if(questionDone)
            {
                questionDone = false;
                break;
            }

            if(pause)
            {
                try {Thread.sleep(5000); } catch(Exception e) {}
                pause = false;
            }

            try {Thread.sleep(displaySpeed); } catch(Exception e) {}
            String word = s.get(i);
            questionDisplay.append(word);
            count += word.length();
            // to avoid that first word from being printed
            if(count > 80)
            {
                questionDisplay.append("\n");
                count = 0;
            }
        }
    }

    // play method that can be called again and again for each question. this way you can stop the play
    // maybe we can put a break statement inside the print method, so if you hit 
    // one of the keys then it will change a variable, causing the printing to pause or break if you 
    // get the answer right

    // so this method play works based on the test above
    public void play()
    {
        List<String> questionToPrint = chooseQuestion(database).getWordArray();
        print(questionToPrint);
    }

    // changes the score in the display window
    public void updateScore(int num)
    {
        scoreDisplay.setText(String.valueOf(num));
    }
}

/**
 * still wanna add functionality of determining how fast the question should read.
 */
