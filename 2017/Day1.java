import java.util.*;
import java.io.*;

public class Day1
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day1.txt"));
        String number = in.nextLine();

        int sum = 0;
        for(int i=0;i<number.length()-2;i++)
        {
            if(number.charAt(i)==number.charAt(i+1))
            {
                sum+=(number.charAt(i)-'0');
            }
        }
        if(number.charAt(number.length()-1)==number.charAt(0))
        {
            sum+=(number.charAt(0)-'0');
        }
        System.out.println(sum);
    }

    public static void partTwo() throws IOException
    {
        Scanner in = new Scanner(new File("day1.txt"));
        String number = in.nextLine();

        int sum = 0;
        for(int i=0;i<number.length()-2;i++)
        {
            if(number.charAt(i)==number.charAt((i+number.length()/2)%number.length()))
            {
                sum+=(number.charAt(i)-'0');
            }
        }
        System.out.println(sum);
    }
}