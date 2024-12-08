import java.util.*;
import java.io.*;

public class Day15
{
    public static void main(String[]args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        Scanner in = new Scanner(new File("day15.txt"));

        String a_str = in.nextLine();
        String[] a_arr = a_str.split(" ");
        long a = Integer.valueOf(a_arr[a_arr.length-1]);

        String b_str = in.nextLine();
        String[] b_arr = b_str.split(" ");
        long b = Integer.valueOf(b_arr[b_arr.length-1]);

        int a_factor = 16807;
        int b_factor = 48271;

        int num_matching = 0;
        for(int i=0;i<40_000_000;i++)
        {
            a = (a * a_factor)%Integer.MAX_VALUE;
            b = (b * b_factor)%Integer.MAX_VALUE;

            if((a&65535) == (b&65535))
            {
                num_matching++;
            }
        }

        System.out.println(num_matching);
    }

    public static void partTwo() throws IOException
    {
        Scanner in = new Scanner(new File("day15.txt"));

        String a_str = in.nextLine();
        String[] a_arr = a_str.split(" ");
        long a = Integer.valueOf(a_arr[a_arr.length-1]);

        String b_str = in.nextLine();
        String[] b_arr = b_str.split(" ");
        long b = Integer.valueOf(b_arr[b_arr.length-1]);

        int a_factor = 16807;
        int b_factor = 48271;

        Queue<Long> a_queue = new LinkedList<Long>();
        Queue<Long> b_queue = new LinkedList<Long>();
        int num_matching = 0;

        while(a_queue.size()!=5_000_000)
        {
            a = (a * a_factor)%Integer.MAX_VALUE;

            if(a % 4 == 0)
            {
                a_queue.add(a);
            }

        }
        while(b_queue.size()!=5_000_000)
        {
            b = (b * b_factor)%Integer.MAX_VALUE;

            if(b % 8 == 0)
            {
                b_queue.add(b);
            }

        }
        for(int i=0;i<5_000_000;i++)
        {
            a = a_queue.poll();
            b = b_queue.poll();


            if((a&65535) == (b&65535))
            {
                num_matching++;
            }
        }

        System.out.println(num_matching);
    }
}