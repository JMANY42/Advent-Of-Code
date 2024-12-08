import java.io.*;
import java.util.*;

public class Day1Part1 {
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
                if(Character.isDigit(str.charAt(i)))
                {
                    first = (int)str.charAt(i)-48;
                    break;
                }
            }

            //starting from the back of the string, look for the last digit
            for(int i=str.length()-1;i>=0;i--)
            {
                if(Character.isDigit(str.charAt(i)))
                {
                    last = (int)str.charAt(i)-48;
                    break;
                }
            }
            
            //add the digits to the sum
            sum += (first*10+last);
        }
        System.out.println("sum: " + sum);
    }
}
