
import java.util.*;
import java.io.*;

public class FileReader
{
    private int countquestions() throws IOException
    {
        File file = new File("Protobowl.txt");
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
        return count;
    }

    public List<Integer> questionindex() throws IOException
    {
        File file = new File("Protobowl.txt");
        Scanner fileIn = new Scanner(file);
        String textfile = "";
        List<Integer> index = new ArrayList<Integer>();
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
                index.add(lastIndex+4);
                lastIndex += findStr.length();
            }
        }
        return index;
    }

    public List<String> questions() throws IOException
    {
        File file = new File("Protobowl.txt");
        Scanner fileIn = new Scanner(file);
        String textfile = "";
        while (fileIn.hasNext())
        {
            textfile += fileIn.next() + " ";
        }
        List<Integer> indices = questionindex();
        List<String> questions = new ArrayList<String>();
        List<Integer> ansindices = answerindex();
        int numquestions = countquestions();
        for (int i = 0; i < indices.size()-1; i++)
        {
            questions.add(textfile.substring(indices.get(i), ansindices.get(i)));
        }
        return questions;

    }

    public List<Integer> singlequestionindex(int questionnum) throws IOException
    {
        File file = new File("Protobowl.txt");
        Scanner fileIn = new Scanner(file);
        String textfile = "";
        while (fileIn.hasNext())
        {
            textfile += fileIn.next() + " ";
        }
        List<String> questions = questions();
        List<Integer> index = new ArrayList<Integer>();
        int numquestions = countquestions();
        
        String findStr = " ";
        int lastIndex = 0;
        int count = 0;
        while(lastIndex != -1){
            lastIndex = questions.get(questionnum).indexOf(findStr,lastIndex);
            if(lastIndex != -1){
                index.add(lastIndex+1);
                lastIndex += findStr.length();
            }
        }
        index.add(0,0);
        return index;
    }
    
    public List<String> singlequestionList(int questionnum) throws IOException
    {
        File file = new File("Protobowl.txt");
        Scanner fileIn = new Scanner(file);
        String textfile = "";
        while (fileIn.hasNext())
        {
            textfile += fileIn.next() + " ";
        }
        List<String> questions = questions();
        List<Integer> indices = singlequestionindex(questionnum);
        List<String> singlequestionList = new ArrayList<String>();
        for (int i = 0; i < indices.size()-1; i++)
        {
            singlequestionList.add(questions.get(questionnum).substring(indices.get(i), indices.get(i+1)));
        }
        return singlequestionList;
    }

    public List<Integer> answerindex() throws IOException
    {
        File file = new File("Protobowl.txt");
        Scanner fileIn = new Scanner(file);
        String textfile = "";
        List<Integer> index = new ArrayList<Integer>();
        while (fileIn.hasNext())
        {
            textfile += fileIn.next() + " ";
        }

        String findStr = "&&&";
        int lastIndex = 0;
        int count = 0;
        while(lastIndex != -1){
            lastIndex = textfile.indexOf(findStr,lastIndex);
            if(lastIndex != -1){
                index.add(lastIndex+3);
                lastIndex += findStr.length();
            }
        }
        return index;
    }

    public List<String> answers() throws IOException
    {
        File file = new File("Protobowl.txt");
        Scanner fileIn = new Scanner(file);
        String textfile = "";
        while (fileIn.hasNext())
        {
            textfile += fileIn.next() + " ";
        }
        List<Integer> ansindices = answerindex();
        List<Integer> quesindices = questionindex();
        List<String> answers = new ArrayList<String>();
        for (int i = 0; i < ansindices.size()-1; i++)
        {
            answers.add(textfile.substring(ansindices.get(i), quesindices.get(i+1)-5));
        }
        return answers;
    }

}