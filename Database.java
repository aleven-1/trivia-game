
/**
 * holds a database of different questions
 * 
 */

import java.util.*;
import java.io.*;

public class Database 
{
    private ArrayList<Question> databaseList = new ArrayList<Question>();
    private ArrayList<String> questionList = new ArrayList<String>();
    private ArrayList<String> answerList = new ArrayList<String>();
    private int numQuestions;

    // first constructor is used when user does not specify a question file
    public Database() throws IOException
    {
        questionList = questions("Protobowl.txt");
        answerList = answers("Protobowl.txt");
        numQuestions = countQuestions("Protobowl.txt");
      
        for(int i = 0; i < numQuestions; i++)
        {
            databaseList.add(new Question(questionList.get(i), answerList.get(i)));
        }
    }

    public Database(String s) throws IOException
    {
        questionList = questions(s);
        answerList = answers(s);
        numQuestions = countQuestions(s);
        for(int i = 0; i < numQuestions; i++)
        {
            databaseList.add(new Question(questionList.get(i), answerList.get(i)));
        }
    }

    public int getNumQuestions()
    {
        return numQuestions;
    }

    public ArrayList<Question> getDatabaseList()
    {
        return databaseList;
    }

    public void addToDatabase(Question q)
    {
        databaseList.add(q);
    }

    // counts number of questions in file
    private int countQuestions(String s) throws IOException
    {
        File file = new File(s);
        Scanner fileIn = new Scanner(file);
        String textfile = "";
        while (fileIn.hasNext())
        {
            textfile += fileIn.next() + " ";
        }

        String findStr = "***";
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){
            lastIndex = textfile.indexOf(findStr,lastIndex);
            if(lastIndex != -1){
                count++;
                lastIndex += findStr.length();
            }
        }
        return count - 1;
    }

    // creates an array list of questions
    public ArrayList<String> questions(String s) throws IOException
    {
        File file = new File(s);
        Scanner fileIn = new Scanner(file);
        String textfile = "";
        while (fileIn.hasNext())
        {
            textfile += fileIn.next() + " ";
        }

        List<Integer> quesindex = new ArrayList<Integer>();
        String findStr = "***";
        int lastIndex = 0;
        int count = 0;
        while(lastIndex != -1){
            lastIndex = textfile.indexOf(findStr,lastIndex);
            if(lastIndex != -1){
                quesindex.add(lastIndex+4);
                lastIndex += findStr.length();
            }
        }

        List<Integer> ansindex = new ArrayList<Integer>();
        String findStr2 = "&&&";
        int lastIndex2 = 0;
        int count2 = 0;
        while(lastIndex2 != -1){
            lastIndex2 = textfile.indexOf(findStr2,lastIndex2);
            if(lastIndex2 != -1){
                ansindex.add(lastIndex2+3);
                lastIndex2 += findStr2.length();
            }
        }

        ArrayList<String> questions = new ArrayList<String>();
        for (int i = 0; i < quesindex.size()-1; i++)
        {
            questions.add(textfile.substring(quesindex.get(i), ansindex.get(i)-4));
        }
        return questions;

    }

    // creates an array list of answers
    public ArrayList<String> answers(String s) throws IOException
    {
        File file = new File(s);
        Scanner fileIn = new Scanner(file);
        String textfile = "";
        while (fileIn.hasNext())
        {
            textfile += fileIn.next() + " ";
        }

        List<Integer> quesindex = new ArrayList<Integer>();
        String findStr = "***";
        int lastIndex = 0;
        int count = 0;
        while(lastIndex != -1){
            lastIndex = textfile.indexOf(findStr,lastIndex);
            if(lastIndex != -1){
                quesindex.add(lastIndex+4);
                lastIndex += findStr.length();
            }
        }

        List<Integer> ansindex = new ArrayList<Integer>();
        String findStr2 = "&&&";
        int lastIndex2 = 0;
        int count2 = 0;
        while(lastIndex2 != -1){
            lastIndex2 = textfile.indexOf(findStr2,lastIndex2);
            if(lastIndex2 != -1){
                ansindex.add(lastIndex2+3);
                lastIndex2 += findStr2.length();
            }
        }

        ArrayList<String> answers = new ArrayList<String>();
        for (int i = 0; i < quesindex.size()-1; i++)
        {
            answers.add(textfile.substring(ansindex.get(i), quesindex.get(i+1)-5));
        }
        return answers;
    }
}
