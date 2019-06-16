
/**
 * this is a question object that holds the question and answer
 */

import java.util.*;
import java.io.*;

public class Question {
    private String question;
    private String answer;
    private ArrayList<String> wordArray;

    public Question(String q, String a)
    {
        question = q;
        answer = a;
        wordArray = makeWordArray(question);
    }

    public void setQuestion(String q)
    {
        question = q;
    }

    public void setAnswer(String a)
    {
        answer = a;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getAnswer()
    {
        return answer;
    }
    
    public List<String> getWordArray()
    {
        return wordArray;
    }
    
    // converts the question into an array list of its words for printing
    public ArrayList<String> makeWordArray(String s)
    {
        List<Integer> index = new ArrayList<Integer>();

        String findStr = " ";
        int lastIndex = 0;
        int count = 0;
        while(lastIndex != -1){
            lastIndex = s.indexOf(findStr,lastIndex);
            if(lastIndex != -1){
                index.add(lastIndex+1);
                lastIndex += findStr.length();
            }
        }
        index.add(0,0);
        index.add(s.length());

        ArrayList<String> singlequestionList = new ArrayList<String>();
        for (int i = 0; i < index.size()-1; i++)
        {
            singlequestionList.add(s.substring(index.get(i), index.get(i+1)));
        }
        return singlequestionList;
    }
}
