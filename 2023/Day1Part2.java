import java.io.*;
import java.util.*;

public class Day1Part2 {
    public static void main(String[] args) throws IOException
    {
        //get the data from the file day1.txt
        Scanner in = new Scanner(new File("day1.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while(in.hasNextLine())
        {
            list.add(in.nextLine());
        }

        //initialize a sum variable to 0
        int sum = 0;

        //loop through every string in the array list
        for(String str : list)
        {
            //initialize a fist and last variable to hold the answers
            int first = -1;
            int last = -1;

            //starting from the front of the string, look for the first digit
            for(int i=0;i<str.length();i++)
            {
                //check if character is a digit
                if(Character.isDigit(str.charAt(i)))
                {
                    first = (int)str.charAt(i)-48;
                    break;
                }

                //check if substring starting at character is a digit
                if(isSpelledDigit(str.substring(i))!=-1)
                {
                    first = isSpelledDigit(str.substring(i));
                    break;
                }
            }

            //starting from the back of the string, look for the last digit
            for(int i=str.length()-1;i>=0;i--)
            {
                //check if character is a digit
                if(Character.isDigit(str.charAt(i)))
                {
                    last = (int)str.charAt(i)-48;
                    break;
                }

                //check if substring starting at character is a digit
                if(isSpelledDigit(str.substring(i))!=-1)
                {
                    last = isSpelledDigit(str.substring(i));
                    break;
                }
            }
            
            //add the digits to the sum
            sum += (first*10+last);
        }
        System.out.println("sum: " + sum);
    }


    public static int isSpelledDigit(String str)
    {
        if(str.length()>=3&&str.substring(0,3).equals("one")){
            return 1;
        }
        else if(str.length()>=3&&str.substring(0,3).equals("two")){
            return 2;
        }
        else if(str.length()>=5&&str.substring(0,5).equals("three")){
            return 3;
        }
        else if(str.length()>=4&&str.substring(0,4).equals("four")){
            return 4;
        }
        else if(str.length()>=4&&str.substring(0,4).equals("five")){
            return 5;
        }
        else if(str.length()>=3&&str.substring(0,3).equals("six")){
            return  6;
        }
        else if(str.length()>=5&&str.substring(0,5).equals("seven")){
            return  7;
        }
        else if(str.length()>=5&&str.substring(0,5).equals("eight")){
            return  8;
        }
        else if(str.length()>=4&&str.substring(0,4).equals("nine")){
            return  9;
        }
        else if(str.length()>=4&&str.substring(0,4).equals("zero")){
            return  0;
        }
        return -1;
    }
}
