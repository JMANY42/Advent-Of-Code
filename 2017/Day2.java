import java.util.*;
import java.io.*;

public class Day2
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day2.txt"));

        int n = 0;
        int sum = 0;
        while(in.hasNextLine())
        {
            String line = in.nextLine();
            String[] arr = line.split(" ");

            int smallest = Integer.MAX_VALUE;
            int largest = Integer.MIN_VALUE;

            for(String q:arr)
            {
                int qq = Integer.valueOf(q);

                if(qq<smallest)
                {
                    smallest = qq;
                }
                if(qq>largest)
                {
                    largest = qq;
                }
            }
            sum+=largest-smallest;
        }
        System.out.println(sum);
    }

    public static void partTwo() throws IOException
    {
        Scanner in = new Scanner(new File("day2.txt"));

        int n = 0;
        int sum = 0;
        l: while(in.hasNextLine())
        {
            String line = in.nextLine();
            String[] arr = line.split(" ");

            int smallest = Integer.MAX_VALUE;
            int largest = Integer.MIN_VALUE;

            for(int i=0;i<arr.length;i++)
            {
                for(int j=0;j<arr.length;j++)
                {
                    if(i==j) continue;

                    int x = Integer.valueOf(arr[i]);
                    int y = Integer.valueOf(arr[j]);

                    if(x%y==0)
                    {
                        sum+=x/y;
                        continue l;
                    }
                }
            }
        }
        System.out.println(sum);
    }
}